import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class ReaderTester {

    ArrayList<Case> cases = new ArrayList<>();
    HashMap<String, Integer> populationData = new HashMap<String, Integer>();
    HashMap<String, Integer> incomeData = new HashMap<String, Integer>();

    @Before
    public void setup() throws IOException {
        CaseReader testCaseReader = new CaseReader("test-data/2015.csv");
        cases = testCaseReader.getCases();
    }
    
    @Test
    public void testCaseReader() {
        assertNotNull("Cases should be read in and not null.", cases);
    }
    
    @Test
    public void testCensusDataReader() throws IOException {
        CensusDataReader cdr = new CensusDataReader("test-data/population-data.xlsx");
        populationData = cdr.getPopulationData();
        incomeData = cdr.getIncomeData();
        assertNotNull("Population data should be read in and not null.", populationData);
        assertNotNull("Income data should be read in and not null.", incomeData);
    }

}
