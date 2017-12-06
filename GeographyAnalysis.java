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
    
    CensusDataReader ca = new CensusDataReader("popdatatest.xlsx");
    
    public GeographyAnalysis(ArrayList<Case> inCases) throws IOException {
        cases = inCases;
        runAnalysis();
        getCensusData();
    }
    
    public void getCensusData() throws IOException {
        populationData = ca.getPopulationData();
        incomeData = ca.getIncomeData();
    }
    
    public void runAnalysis() {
        cityAnalyzer();
        stateAnalyzer();
        cityWageDifferenceAnalyzer();
    }
    
    public void cityAnalyzer() {
        
        for (int i = 0; i < cases.size(); i++) {            
            String city = cases.get(i).employerCity.toUpperCase() + ", " + cases.get(i).employerState.toUpperCase();
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
    
    public void cityWageDifferenceAnalyzer() {

        for (int i = 0; i < cases.size(); i++) {            
            
            String city = cases.get(i).employerCity.toUpperCase() + ", " + cases.get(i).employerState;
            
            System.out.println(city);
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
    
    Comparator<Entry<String, Integer>> highToLowComparator = new Comparator<Entry<String, Integer>>() {
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            Integer count1 = entry1.getValue();
            Integer count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    Comparator<Entry<String, Double>> highToLowDoubleComparator = new Comparator<Entry<String, Double>>() {
        public int compare(Entry<String, Double> entry1, Entry<String, Double> entry2) {
            Double count1 = entry1.getValue();
            Double count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    //top 10 cities
    public String getTopTenCities() {
        StringBuilder sa = new StringBuilder();
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
    
    //top 10 states
    public String getTopTenStates() { 
        StringBuilder sa = new StringBuilder();
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
    
    //top 10 cities with highest dif in prev wage + submitted pay
    public String getTopTenCitiesForWageDif() {
        StringBuilder sa = new StringBuilder();
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
        sa.append("\n");
        return sa.toString();
    }
    
    //average wage of state vs average wage of H1B in state
    public String getAverageWagevsAverageH1BWage(String state) {
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        
        StringBuilder sa = new StringBuilder();
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
        
        sa.append("The average H1B wage for " + state + " is $" + df.format(averageH1BWage) + ".");
        sa.append("\n");
        sa.append("The average household income for " + state + " is $" + df.format(averageWage) + ".");
        sa.append("\n");
        sa.append("\n");
        
        return sa.toString();
    }
    
    //% of H-1B apps in state vs % of population in state
    public String getH1BPopulationPercentageVsStatePopulation(String state) {
        
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        
        StringBuilder sa = new StringBuilder();
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
        sa.append("The percentage of H1B applicants from " + state + " is " + df.format(H1BPopulationPercentage) + "%.");
        sa.append("\n");
        sa.append("\n");
        
        return sa.toString();
    }
    
}
