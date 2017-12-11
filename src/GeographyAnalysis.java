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
 * This method consists of all methods that analyze H-1B cases on 
 * questions that pertain to location. This includes the top 10 
 * cities/states, top 10 cities for wage differences b/w the prevailing
 * wage and the wage offered, etc. It also uses the CensusDataReader class
 * to compare population and income data for a state overall and a state's 
 * H-1B applicants.
 * @author adi
 *
 */
public class GeographyAnalysis {
    
    ArrayList<Case> cases = new ArrayList<Case>();
    
    HashMap<String, Integer> cities = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedCities;
    
    HashMap<String, Integer> cityWageDifference = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedCityWageDifference;
    
    HashMap<String, Integer> states = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedStates;

    HashMap<String, Integer> populationData = new HashMap<String, Integer>();
    HashMap<String, Integer> incomeData = new HashMap<String, Integer>();
    
    CensusDataReader ca = new CensusDataReader("test-data/population-data.xlsx");
    
    /**
     * This is the constructor.
     * It reads in all H-1B cases from CaseReader, and analyzes city, state, and wage
     * difference data using the runAnalysis() method. It also gets census data from 
     * the CensusDataReader class.
     * @param inCases: the ArrayList of cases that is used for this class
     * @throws IOException
     */
    public GeographyAnalysis(ArrayList<Case> inCases) throws IOException {
        cases = inCases;
        runAnalysis();
        getCensusData();
    }
    
    /**
     * This method runs the two CensusDataReader methods and adds the information
     * into two hash maps to be referenced in other methods within this class.
     * @throws IOException
     */
    public void getCensusData() throws IOException {
        populationData = ca.getPopulationData();
        incomeData = ca.getIncomeData();
    }
    
    /**
     * This method calls three methods to analyze city, 
     * state and city wage difference data.
     */
    public void runAnalysis() {
        cityAnalyzer();
        stateAnalyzer();
        cityWageDifferenceAnalyzer();
    }
    
    /**
     * This method is where city information is analyzed and sorted.
     */
    public void cityAnalyzer() {
        
        for (int i = 0; i < cases.size(); i++) {            
            String city = cases.get(i).employerCity.toUpperCase() + ", " + 
                    cases.get(i).employerState.toUpperCase();
                if (cities.containsKey(city)) {
                    int count = cities.get(city) + 1;
                    cities.put(city, count);
                } else {
                    cities.put(city, 1);
                }
            }
        
        Collection<Entry<String, Integer>> citySet = cities.entrySet();
        organizedCities =
                new ArrayList<Entry<String, Integer>>(citySet);
        Collections.sort(organizedCities, highToLowComparator);
    }
    
    /**
     * This method is where state information is analyzed and sorted.
     */
    public void stateAnalyzer() {

        for (int i = 0; i < cases.size(); i++) {            
            
            String state = cases.get(i).employerState;
            
            if (states.containsKey(state)) {
                int count = states.get(state) + 1;
                states.put(state, count);
            } else {
                states.put(state, 1);
            }
        }      
        
        Collection<Entry<String, Integer>> stateSet = states.entrySet();
        organizedStates =
                new ArrayList<Entry<String, Integer>>(stateSet);
        Collections.sort(organizedStates, highToLowComparator);
    }
    
    
    /**
     * This method is where city wage difference information is analyzed and sorted.
     */
    public void cityWageDifferenceAnalyzer() {

        for (int i = 0; i < cases.size(); i++) {            
            
            String city = cases.get(i).employerCity.toUpperCase() + ", " 
                    + cases.get(i).employerState;
            
                if (cityWageDifference.containsKey(city)) {
                    int average = (cityWageDifference.get(city) + cases.get(i).wageDifference) / 2;
                    cityWageDifference.put(city, average);
                } else {
                    cityWageDifference.put(city, cases.get(i).wageDifference);
                }
            }
        
        Collection<Entry<String, Integer>> cityDifSet = cityWageDifference.entrySet();
        organizedCityWageDifference =
                new ArrayList<Entry<String, Integer>>(cityDifSet);
        Collections.sort(organizedCityWageDifference, highToLowComparator);
    }
    
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
     * This is the comparator used in the analyzer methods to 
     * sort the cities/states and their wages.
     */
    Comparator<Entry<String, Double>> highToLowDoubleComparator = new Comparator<Entry<String, Double>>() {
        public int compare(Entry<String, Double> entry1, Entry<String, Double> entry2) {
            Double count1 = entry1.getValue();
            Double count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    /**
     * This method returns the top ten cities based on the number of 
     * H-1B applications submitted from this city in the given year's 
     * worth of data.
     * @return the top ten cities
     */
    public String getTopTenCities() {
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        sa.append("The top 10 cities and their respective counts are...");
        sa.append("\n");
        sa.append("\n");
        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedCities.get(i).getKey() + "\n");
            sa.append("Count: " + organizedCities.get(i).getValue() + "\n");
            sa.append("\n");
        }
        return sa.toString();
    }
    
    /**
     * This method returns the top ten states based on the number of 
     * H-1B applications submitted from this city in the given year's 
     * worth of data.
     * @return the top ten states
     */
    public String getTopTenStates() { 
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        sa.append("The top 10 states and their respective counts are...");
        sa.append("\n");
        sa.append("\n");

        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedStates.get(i).getKey() + "\n");
            sa.append("Count: " + organizedStates.get(i).getValue() + "\n");
            sa.append("\n");
        }
        return sa.toString();
    }
    
    /**
     * This method returns the top ten cities based on the average difference  
     * between the wage offered and the submitted prevailing wage according 
     * to H-1B applications in the given year's worth of data.
     * @return the top ten cities based on wage difference
     */
    public String getTopTenCitiesForWageDif() {
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        sa.append("The top 10 cities and their respective average differences"
                + " between wage offered and prevailing wage are...");
        sa.append("\n");
        sa.append("\n");
        
        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedCityWageDifference.get(i).getKey() + "\n");
            sa.append("Average Difference: $" + organizedCityWageDifference.get(i).getValue() + "\n");
            sa.append("\n");
        }
        return sa.toString();
    }
    
    /**
     * This method returns the difference between the average wage in 
     * a state and the average H-1B wage for applications coming out of 
     * the same state in the given year's H-1B dataset.
     * @return the average wage vs average H-1B wage
     */
    public String getAverageWagevsAverageH1BWage(String state) {
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        double count = 0;
        double total = 0;
        double averageH1BWage = 0;
        
        for (int i = 0; i < cases.size(); i++) {     
            if (cases.get(i).employerState.equals(state)) {
                total = total + cases.get(i).wageRate;
                count++;
            }
        }
        averageH1BWage = total / count;
        double averageWage = incomeData.get(state);
        
        if (averageH1BWage != 0) {
            sa.append("The average H-1B wage for " + state + " is $" + df.format(averageH1BWage) + ".");
            sa.append("\n");
            sa.append("The average household income for " + state + " is $" + df.format(averageWage) + ".");
        } else {
            sa.append("There were no H-1Bs submitted from your given state in the given year.");
            sa.append("\n");
            sa.append("The average household income for " + state + " is $" + df.format(averageWage) + ".");
        }

        
        return sa.toString();
    }
    
    /**
     * This method returns the difference between the % of the national population that a given
     * state's population makes up, and the % of the total H-1B applicants that a given state's
     * H-1B applicants make up. This sheds light on which states overindex/underindex on H-1B
     * applications in the given year's H-1B dataset.
     * @return the % of a given state's population vs the national population + 
     * the % of that state's H-1B applicants vs the total set of H-1B applicants.
     */
    public String getH1BPopulationPercentageVsStatePopulation(String state) {
        
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        double statePopulationPercentage = ca.getPercentageOfTotalPopulation(state);
        
        double count = 0;
        double total = cases.size();
        
        for (int i = 0; i < cases.size(); i++) {     
            if (cases.get(i).employerState.equals(state)) {
                count++;
            }
        }

        double H1BPopulationPercentage = count * 100 / total;
        
        sa.append("The percentage of the overall population in " + state + " is " + df.format(statePopulationPercentage) + "%.");
        sa.append("\n");
        sa.append("The percentage of H-1B applicants from " + state + " is " + df.format(H1BPopulationPercentage) + "%.");
        return sa.toString();
    }
    
}
