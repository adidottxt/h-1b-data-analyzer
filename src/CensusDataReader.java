import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class reads in data from a 2011 census spreadsheet and stores 
 * population and income data for each state in the United States in two 
 * hash maps that are referenced in other classes.
 * @author adi
 *
 */
public class CensusDataReader {

    XSSFSheet testSheet1;
    String filename1;
    
    HashMap<String, Integer> populationData = new HashMap<String, Integer>();
    HashMap<String, Integer> incomeData = new HashMap<String, Integer>();
    
    int totalPopulation = 0;
    int averageIncome = 0;

    /**
     * @return the populationData HashMap.
     */
    public HashMap<String, Integer> getPopulationData() {
        return populationData;
    }

    /**
     * @return the incomeData HashMap.
     */
    public HashMap<String, Integer> getIncomeData() {
        return incomeData;
    }

    int count = 0;
    
    /**
     * This is the constructor. It reads in the .xlsx file that contains
     * population and income data for each state in the U.S., and then calls
     * the parseData() function to store the information into the two HashMaps.
     * @param file1 the file to be read in.
     * @throws IOException
     */
    public CensusDataReader(String file1) throws IOException {
        filename1 = file1;
        FileInputStream test1 = new FileInputStream(new File(file1));
        
        System.out.println("Reading in census data...");
        
        XSSFWorkbook testBook1 = new XSSFWorkbook(test1);

        testSheet1 = testBook1.getSheetAt(0);
        test1.close();
        testBook1.close();
        parseData();
    }
    
    /**
     * This method calls two other parse methods that deal with population and income.
     */
    public void parseData() {
        parsePopulationData();
        parseIncomeData();
    }
    
    /**
     * This method parses through the given .xlsx file and adds each state + its 
     * respective population into a HashMap that is called upon in other analysis
     * classes.
     */
    public void parsePopulationData() {
        Row states = (Row) testSheet1.getRow(1);
        Row population = (Row) testSheet1.getRow(4);
        
        for (int i = 0; i < 200; i++) {
            if (i % 2 == 1) {
                if (states.getCell(i) != null) {
                    String state = convertFullStateToCode(states.getCell(i).toString().toLowerCase());
                    if (!populationData.containsKey(state)) {
                        populationData.put(state, (int) Double.parseDouble(population.getCell(i).toString()));
                        totalPopulation = totalPopulation + (int) Double.parseDouble(population.getCell(i).toString());
                    }
                }
            }
        } 
    }
    
    /**
     * This method parses through the given .xlsx file and adds each state + its 
     * respective average household income into a HashMap that is called upon 
     * in other analysis classes.
     */
    public void parseIncomeData() {
        Row states = (Row) testSheet1.getRow(1);
        Row income = (Row) testSheet1.getRow(58);
        
        for (int i = 0; i < 200; i++) {
            if (i % 2 == 1) {
                if (states.getCell(i) != null) {
                    String state = convertFullStateToCode(states.getCell(i).toString().toLowerCase());
                    if (!incomeData.containsKey(state)) {
                        String income1 = income.getCell(i).toString().replaceAll("[^\\d.]", "");
                        averageIncome = (averageIncome + (int) Double.parseDouble(income1));
                        count++;
                        incomeData.put(state, (int) Double.parseDouble(income1));
                    }
                } 
            }  
        }
        averageIncome = averageIncome - 57321;
        count--;
        averageIncome = averageIncome / count;
    }
    
    /**
     * This method converts the full states presented in the .xlsx file to their 
     * respective two-letter codes, for ease of use in the other analysis classes 
     * (given the H-1B data uses the two-letter state codes.)
     * @param fullState the full state name
     * @return their respective two-letter state code
     */
    public String convertFullStateToCode(String fullState) {
        if (fullState.equals("alabama")) {
            return "AL";
        } else if (fullState.equals("alaska")) {
            return "AK";
        } else if (fullState.equals("arizona")) {
            return "AZ";
        } else if (fullState.equals("arkansas")) {
            return "AR";
        } else if (fullState.equals("california")) {
            return "CA";
        } else if (fullState.equals("colorado")) {
            return "CO";
        } else if (fullState.equals("connecticut")) {
            return "CT";
        } else if (fullState.equals("delaware")) {
            return "DE";
        } else if (fullState.equals("florida")) {
            return "FL";
        } else if (fullState.equals("georgia")) {
            return "GA";
        } else if (fullState.equals("hawaii")) {
            return "HI";
        } else if (fullState.equals("idaho")) {
            return "ID";
        } else if (fullState.equals("illinois")) {
            return "IL";
        }  else if (fullState.equals("indiana")) {
            return "IN";
        } else if (fullState.equals("iowa")) {
            return "IA";
        } else if (fullState.equals("kansas")) {
            return "KS";
        } else if (fullState.equals("kentucky")) {
            return "KY";
        } else if (fullState.equals("louisiana")) {
            return "LA";
        } else if (fullState.equals("maine")) {
            return "ME";
        } else if (fullState.equals("maryland")) {
            return "MD";
        } else if (fullState.equals("massachusetts")) {
            return "MA";
        } else if (fullState.equals("michigan")) {
            return "MI";
        } else if (fullState.equals("minnesota")) {
            return "MN";
        } else if (fullState.equals("mississippi")) {
            return "MS";
        } else if (fullState.equals("missouri")) {
            return "MO";
        } else if (fullState.equals("montana")) {
            return "MT";
        } else if (fullState.equals("nebraska")) {
            return "NE";
        } else if (fullState.equals("new hampshire")) {
            return "NH";
        } else if (fullState.equals("nevada")) {
            return "NV";
        } else if (fullState.equals("new jersey")) {
            return "NJ";
        } else if (fullState.equals("new mexico")) {
            return "NM";
        } else if (fullState.equals("new york")) {
            return "NY";
        } else if (fullState.equals("north carolina")) {
            return "NC";
        } else if (fullState.equals("north dakota")) {
            return "ND";
        } else if (fullState.equals("ohio")) {
            return "OH";
        } else if (fullState.equals("oklahoma")) {
            return "OK";
        } else if (fullState.equals("oregon")) {
            return "OR";
        } else if (fullState.equals("pennsylvania")) {
            return "PA";
        } else if (fullState.equals("rhode island")) {
            return "RI";
        } else if (fullState.equals("south carolina")) {
            return "SC";
        } else if (fullState.equals("south dakota")) {
            return "SD";
        } else if (fullState.equals("tennessee")) {
            return "TN";
        } else if (fullState.equals("texas")) {
            return "TX";
        } else if (fullState.equals("utah")) {
            return "UT";
        } else if (fullState.equals("vermont")) {
            return "VT";
        } else if (fullState.equals("virginia")) {
            return "VA";
        } else if (fullState.equals("washington")) {
            return "WA";
        } else if (fullState.equals("west virginia")) {
            return "WV";
        } else if (fullState.equals("wisconsin")) {
            return "WI";
        } else if (fullState.equals("wyoming")) {
            return "WY";
        } else if (fullState.equals("puerto rico")) {
            return "PR";
        } else {
            return ""; 
        }
    }
    
    /**
     * This method returns the percent of the overall population 
     * that a given state's population makes up. This is used in a 
     * specific method in the GeographyAnalysis class.
     * @param state: the state we're checking the % of population for.
     * @return the % of the state's population compared to the national population
     */
    public double getPercentageOfTotalPopulation(String state) {

        if (populationData.containsKey(state)) {
            int statePopulation = populationData.get(state);
            double percentage = (double) statePopulation * 100 / totalPopulation;
            return percentage;
        } else {
            return 0;
        }
    }
    
    /**
     * This method returns the difference between the average household income in 
     * a given state and the average H-1B income based on a year's worth of applications
     * in the same state.
     * @param state: the state for which we are checking the difference
     * @return the dif b/w avg household income and the avg H-1B income
     */
    public double getDifferenceBetweenIncomeInGivenStateAndAverage(String state) {
        
        if (incomeData.containsKey(state)) {
            int stateIncomeAverage = incomeData.get(state);
            double difference = stateIncomeAverage - averageIncome;
            return difference;
        } else {
            return 0;
        }
    }
}
