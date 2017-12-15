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


/**
 * This class runs a simulation of what would happen if H-1B 
 * visas were not handed out by a lottery as they are 
 * today, but instead went to the highest bidder – 
 * or the 85,000 highest bidders, to be exact.
 * @author adi
 *
 */
public class WageBasedSimulation {

    ArrayList<Case> cases = new ArrayList<Case>();
    HashMap<Case, Double> casesAndWages = new HashMap<>();
    List<Entry<Case, Double>> organizedCases;
    ArrayList<Case> winningCases = new ArrayList<Case>();
    ArrayList<Case> losingCases = new ArrayList<Case>();
    
    HashMap<String, Integer> cities = new HashMap<>();
    List<Entry<String, Integer>> organizedCities;
   
    HashMap<String, Integer> states = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedStates;

    HashMap<String, Integer> jobTitles = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedJobTitles;
    
    /**
     * This is the constructor.
     * It takes in an ArrayList of Cases as an input, and then runs
     * caseWageAnalyzer() to sort the cases wage offered, and add 
     * the top 85,000 cases to an ArrayList of Cases named winningCases.
     * @param inCases the ArrayList of cases used as an input.
     * @throws IOException
     */
    public WageBasedSimulation(ArrayList<Case> inCases) throws IOException {
        cases = inCases;
        caseWageAnalyzer();
    }
    
    /**
     * This method sorts all H-1B cases for a given year by the wage offered, 
     * and then adds the top 85,000 cases to winningCases.
     */
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
        
        for (int i = 85000; i < organizedCases.size(); i++) {
            losingCases.add(organizedCases.get(i).getKey());
        }
        
    }
    
    /**
     * This method "runs the simulation", and calls various methods 
     * from other analysis classes for the given set of 85,000 cases.
     * @return all results for the winning cases.
     * @throws IOException
     */
    public String runSimulation() throws IOException {
        String line = "\n\n--------------------------------------------------------------------------\n\n";
        return line + "Here are the results of the simulation where the top 85,000 candidates \n"
                + "by wage are awarded H-1B visas for the given year.\n\n" + getTop25Cases() + 
                getTopTwentyFiveWinningCompanies() + getNumbers() + getTopTenJobTitles() + 
                getTopTenCities() + getTopTenStates() + getUniversityReport();
    }
    
    /**
     * This method "runs the simulation", and calls various methods 
     * from other analysis classes for the cases that don't make the cut.
     * @return all results for the losing cases.
     * @throws IOException
     */
    public String runLosingSimulation() throws IOException {
        String line = "\n\n--------------------------------------------------------------------------\n\n";
        return line + "Here are the results of the simulation for the unfortunate cases who did not "
                + "make the top 85,000 candidates.\n\n" + 
                getTop25LosingCases() + getTopTwentyFiveLosingCompanies() + getLosingNumbers() + 
                getTopTenLosingJobTitles() + getTopTenLosingCities() + getTopTenLosingStates() + 
                getLosingUniversityReport();
    }
    
    /**
     * This method returns the top 10 cities 
     * for the ArrayList of winningCases.
     * @return the top 10 cities
     * @throws IOException
     */
    public String getTopTenCities() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(winningCases);
        return ga.getTopTenCities();
    }
    
    /**
     * This method returns the top 10 cities 
     * for the ArrayList of losingCases.
     * @return the top 10 cities
     * @throws IOException
     */
    public String getTopTenLosingCities() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(losingCases);
        return ga.getTopTenCities();
    }
    
    /**
     * This method returns the top 10 states 
     * for the ArrayList of winningCases.
     * @return the top 10 states
     * @throws IOException
     */
    public String getTopTenStates() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(winningCases);
        return ga.getTopTenStates();
    }
    
    /**
     * This method returns the top 10 states 
     * for the ArrayList of losingCases.
     * @return the top 10 states
     * @throws IOException
     */
    public String getTopTenLosingStates() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(losingCases);
        return ga.getTopTenStates();
    }
    
    /**
     * This method returns the top 10 job titles 
     * for the ArrayList of winningCases.
     * @return the top 10 job titles
     * @throws IOException
     */
    public String getTopTenJobTitles() {
        JobAnalysis ja = new JobAnalysis(winningCases);
        return ja.getTopTenJobTitles();
    }
    
    public String getTopTwentyFiveWinningCompanies() {
        CompanyAnalysis cAn = new CompanyAnalysis(winningCases);
        return cAn.getTopTwentyFiveCompanies();
    }
    
    public String getTopTwentyFiveLosingCompanies() {
        CompanyAnalysis cAn = new CompanyAnalysis(losingCases);
        return cAn.getTopTwentyFiveCompanies();
    }
    
    /**
     * This method returns the top 10 job titles 
     * for the ArrayList of losingCases.
     * @return the top 10 job titles
     * @throws IOException
     */
    public String getTopTenLosingJobTitles() {
        JobAnalysis ja = new JobAnalysis(losingCases);
        return ja.getTopTenJobTitles();
    }
    
    /**
     * This method returns the top 25 cases 
     * for the ArrayList of winningCases based
     * on wage offered.
     * @return the top 25 cases
     * @throws IOException
     */
    public String getTop25Cases() {
        StringBuilder sa = new StringBuilder();
        sa.append("The top 25 job cases based on wage offered are...");
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
    
    /**
     * This method returns the top 25 cases 
     * for the ArrayList of winningCases based
     * on wage offered.
     * @return the top 25 cases
     * @throws IOException
     */
    public String getTop25LosingCases() {
        StringBuilder sa = new StringBuilder();
        sa.append("The top 25 job cases that missed out on their H-1Bs based on wage offered are...");
        sa.append("\n");
        sa.append("\n");
        
        for (int i = 0; i < 25; i++) {
            sa.append((i + 1) + ". " + losingCases.get(i).jobTitle + ", " + 
                    losingCases.get(i).employerName + "\n" + losingCases.get(i).employerCity + 
        ", " + losingCases.get(i).employerState + "\n");
            sa.append("Wage: " + (int) losingCases.get(i).wageRate  + "\n");
            sa.append("\n");
        }
        
        sa.append("\n");
        return sa.toString();
    }
    
    /**
     * This method returns the upper and lower bound of the 
     * 85000 cases (in terms of wage) and the average wage 
     * for the ArrayList of winningCases based on wage offered.
     * @return the upper + lower bound + average wage
     * @throws IOException
     */
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
    
    public String getLosingNumbers() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        
        double averageWage = 0;
        int count = 0;
        double highestWage = 0;
        double lowestWage = 1000000;
        
        for (int i = 0; i < losingCases.size(); i++) {
            
            averageWage = losingCases.get(i).wageRate + averageWage;
            count++;
            
            if (highestWage < losingCases.get(i).wageRate) {
                highestWage = losingCases.get(i).wageRate;
            }

            if (lowestWage > losingCases.get(i).wageRate && losingCases.get(i).wageRate != 0) {
                lowestWage = losingCases.get(i).wageRate;
            }
        }
        
        averageWage = averageWage / count;
        
        String averageAnswer = "The average wage of the simulated H-1B losers is $" + df.format(averageWage) + ".\n";
        String lowestAnswer = "The lowest wage of the simulated H-1B losers is $" + df.format(lowestWage) + ".\n";
        String highestAnswer = "The highest wage of the simulated H-1B losers is $" + df.format(highestWage) + ".\n\n";
        String line = "\n\n--------------------------------------------------------------------------\n\n";
        return line + averageAnswer + lowestAnswer + highestAnswer;
    }
    
    /**
     * This method calls upon two methods relevant to university jobs, 
     * from the UniversityAnalysis class for the winningCases.
     * @return results from the university analysis class.
     */
    public String getUniversityReport() {
        UniversityAnalysis ua = new UniversityAnalysis(winningCases);
        return ua.getTopTenUniversityApplicants() + ua.getTopTenUniversityJobs();
    }
    
    /**
     * This method calls upon two methods relevant to university jobs, 
     * from the UniversityAnalysis class for the losingCases.
     * @return results from the university analysis class.
     */
    public String getLosingUniversityReport() {
        UniversityAnalysis ua = new UniversityAnalysis(losingCases);
        return ua.getTopTenUniversityApplicants() + ua.getTopTenUniversityJobs();
    }
    
    /**
     * This is the comparator used in the analyzer methods to 
     * sort the cities/states and their wages.
     */
    Comparator<Entry<Case, Double>> highToLowDoubleComparator = new Comparator<Entry<Case, Double>>() {
        public int compare(Entry<Case, Double> entry1, Entry<Case, Double> entry2) {
            Double count1 = entry1.getValue();
            Double count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    /**
     * This is the comparator used in the analyzer methods to 
     * sort the cities/states and their counts.
     */
    Comparator<Entry<String, Integer>> highToLowComparator = new Comparator<Entry<String, Integer>>() {
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            Integer count1 = entry1.getValue();
            Integer count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };

    /**
     * @return the winningCases
     */
    public ArrayList<Case> getWinningCases() {
        return winningCases;
    }

    /**
     * @return the losingCases
     */
    public ArrayList<Case> getLosingCases() {
        return losingCases;
    }
    
}
