import java.io.IOException;
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
    
    HashMap<String, Double> cityWageDifference = new HashMap<String, Double>();
    List<Entry<String, Double>> organizedCityWageDifference;
    
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
        System.out.println("Looking at city data...");
        for (int i = 0; i < cases.size(); i++) {            
            String city = cases.get(i).employerCity;
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
        System.out.println("Looking at state data...");
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
        System.out.println("Looking at city data...");
        for (int i = 0; i < cases.size(); i++) {            
            String city = cases.get(i).employerCity + ", " + cases.get(i).employerState;
                if (cityWageDifference.containsKey(city)) {
                    double average = (cityWageDifference.get(city) + cases.get(i).wageDifference) / 2;
                    cityWageDifference.put(city, average);
                } else {
                    cityWageDifference.put(city, cases.get(i).wageDifference);
                }
            }
        
        Collection<Entry<String, Double>> cityDifSet = cityWageDifference.entrySet();
        organizedCityWageDifference =
                new ArrayList<Entry<String, Double>>(cityDifSet);
        Collections.sort(organizedCityWageDifference, highToLowDoubleComparator);
    }
    

    //top 10 cities
    public void getTopTenCities() {

        System.out.println("The top 10 cities and their respective counts are...");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("City: \"" + organizedCities.get(i).getKey() +
                    "\" ––> Count: " + organizedCities.get(i).getValue());
        }
        System.out.println();
        System.out.println();
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
    
    //top 10 states
    public void getTopTenStates() {

        System.out.println("The top 10 states and their respective counts are...");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("State: \"" + organizedStates.get(i).getKey() +
                    "\" ––> Count: " + organizedStates.get(i).getValue());
        }
        System.out.println();
        System.out.println();
    }
    
    //top 10 cities with highest dif in prev wage + submitted pay
    public void getTopTenCitiesForWageDif() {

        System.out.println("The top 10 cities and their respective counts are...");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("City: \"" + organizedCityWageDifference.get(i).getKey() +
                    "\" ––> Count: " + organizedCityWageDifference.get(i).getValue());
        }
        System.out.println();
        System.out.println();
    }
    
    //average wage of state vs average wage of H1B in state
    public void getAverageWagevsAverageH1BWage(String state) {
        
        int count = 0;
        int total = 0;
        int averageH1BWage = 0;
        
        for (int i = 0; i < cases.size(); i++) {     
            if (cases.get(i).employerState.equals(state)) {
                total = total + (int) cases.get(i).wageRate;
                count++;
            }
        }
        
        averageH1BWage = total / count;
        
        int averageWage = incomeData.get(state);
        
        System.out.println("The average H1B wage for " + state + " is " + averageH1BWage);
        System.out.println("The average household income for " + state + " is " + averageWage);
        
    }
    
    //% of H-1B apps in state vs % of population in state
    public void getH1BPopulationPercentageVsStatePopulation(String state) {
        double statePopulationPercentage = ca.getPercentageOfTotalPopulation(state);
        
        int count = 0;
        int total = cases.size();
        
        for (int i = 0; i < cases.size(); i++) {     
            if (cases.get(i).employerState.equals(state)) {
                count++;
            }
        }
        
        double H1BPopulationPercentage = (count * 100) / total;
        
        System.out.println("The % of the overall population in " + state + " is " + statePopulationPercentage + ".");
        System.out.println("The % of H1B applicants from " + state + " is " + H1BPopulationPercentage + ".");
  
    }
    
}
