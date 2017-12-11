import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class WageBasedSimulation {

    ArrayList<Case> cases = new ArrayList<Case>();
    HashMap<Case, Double> casesAndWages = new HashMap<>();
    List<Entry<Case, Double>> organizedCases;
    ArrayList<Case> winningCases = new ArrayList<Case>();
    
    HashMap<String, Integer> cities = new HashMap<>();
    List<Entry<String, Integer>> organizedCities;
   
    HashMap<String, Integer> states = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedStates;

    HashMap<String, Integer> jobTitles = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedJobTitles;
    
    public WageBasedSimulation(ArrayList<Case> inCases) throws IOException {
        cases = inCases;
        caseWageAnalyzer();
    }
    
    public void caseWageAnalyzer() {
        for (int i = 0; i < cases.size(); i++) {            
            if (cases.get(i).employerCity.length() > 2) {
                double wage = cases.get(i).wageRate;
                casesAndWages.put(cases.get(i), wage);
            }
        }
        
        Collection<Entry<Case, Double>> casesAndWagesCollection = casesAndWages.entrySet();
        organizedCases =
                new ArrayList<Entry<Case, Double>>(casesAndWagesCollection);
        Collections.sort(organizedCases, highToLowDoubleComparator);
        
        for (int i = 0; i < 85000; i++) {
            winningCases.add(organizedCases.get(i).getKey());
        }
    }
    
    public String runSimulation() throws IOException {
        String line = "\n\n--------------------------------------------------------------------------\n\n";
        return line + "Here are the results of the simulation where the top 85,000 candidates \n"
                + "by wage are awarded H-1B visas for the given year.\n\n" + getTop25Cases() + 
                getNumbers() + getTopTenJobTitles() + getTopTenCities() + getTopTenStates() + 
                getUniversityReport();
    }
    
    //top 10 cities
    public String getTopTenCities() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(winningCases);
        return ga.getTopTenCities();
    }
    
    //top 10 states
    public String getTopTenStates() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(winningCases);
        return ga.getTopTenStates();

    }
    
    //top 10 popular job titles
    public String getTopTenJobTitles() {
        JobAnalysis ja = new JobAnalysis(winningCases);
        return ja.getTopTenJobTitles();
    }
    
    //top 25 cases
    public String getTop25Cases() {
        StringBuilder sa = new StringBuilder();
        sa.append("The top 10 job cases based on wage offered are...");
        sa.append("\n");
        sa.append("\n");
        
        for (int i = 0; i < 25; i++) {
            sa.append((i + 1) + ". " + winningCases.get(i).jobTitle + ", " + 
        winningCases.get(i).employerName + "\n" + winningCases.get(i).employerCity + 
        ", " + winningCases.get(i).employerState + "\n");
            sa.append("Wage: " + (int) winningCases.get(i).wageRate  + "\n");
            sa.append("\n");
        }
        
        sa.append("\n");
        return sa.toString();
    }
    
    //upper + lower bound + average wage
    public String getNumbers() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        
        double averageWage = 0;
        int count = 0;
        double highestWage = 0;
        double lowestWage = 1000000;
        
        for (int i = 0; i < winningCases.size(); i++) {
            
            averageWage = winningCases.get(i).wageRate + averageWage;
            count++;
            
            if (highestWage < winningCases.get(i).wageRate) {
                highestWage = winningCases.get(i).wageRate;
            }

            if (lowestWage > winningCases.get(i).wageRate) {
                lowestWage = winningCases.get(i).wageRate;
            }
        }
        
        averageWage = averageWage / count;
        
        String averageAnswer = "The average wage of the simulated H-1B recipients is $" + df.format(averageWage) + ".\n";
        String lowestAnswer = "The lowest wage of the simulated H-1B recipients is $" + df.format(lowestWage) + ".\n";
        String highestAnswer = "The highest wage of the simulated H-1B recipients is $" + df.format(highestWage) + ".\n\n";
        String line = "\n\n--------------------------------------------------------------------------\n\n";
        return line + averageAnswer + lowestAnswer + highestAnswer;
    }
    
    //how many university applications + top 10 + average wage + top 10 jobs
    public String getUniversityReport() {
        UniversityAnalysis ua = new UniversityAnalysis(winningCases);
        return ua.getTopTenUniversityApplicants() + ua.getTopTenUniversityJobs();
    }
    
    Comparator<Entry<Case, Double>> highToLowDoubleComparator = new Comparator<Entry<Case, Double>>() {
        public int compare(Entry<Case, Double> entry1, Entry<Case, Double> entry2) {
            Double count1 = entry1.getValue();
            Double count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    Comparator<Entry<String, Integer>> highToLowComparator = new Comparator<Entry<String, Integer>>() {
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            Integer count1 = entry1.getValue();
            Integer count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
}
