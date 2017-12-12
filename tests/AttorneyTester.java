import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class AttorneyTester {

    ArrayList<Case> cases = new ArrayList<>();
    HashMap<String, Integer> populationData = new HashMap<String, Integer>();
    HashMap<String, Integer> incomeData = new HashMap<String, Integer>();

    @Before
    public void setup() throws IOException {
        CaseReader testCaseReader = new CaseReader("test-data/2015.csv");
        cases = testCaseReader.getCases();
    }
    
    @Test
    public void testTopTenAttorneys() {
        AttorneyAnalysis aa = new AttorneyAnalysis(cases);          
        String topTenBusyAttorneys = aa.getTopTenBusyAttorneys();         
        assertTrue("Attorney list should not be null.", topTenBusyAttorneys != null);
    }
    
    @Test
    public void testOrganizedAttorneysList() {
        AttorneyAnalysis aa = new AttorneyAnalysis(cases);
        assertTrue("organizedAttorneys comparator works.", 
                aa.organizedAttorneys.get(1).getValue() > aa.organizedAttorneys.get(2).getValue());
    }
    
    @Test
    public void testOrganizedCitiesList() {
        AttorneyAnalysis aa = new AttorneyAnalysis(cases);
        assertTrue("organizedAttorneyCities comparator works.", 
                aa.organizedAttorneyCities.get(1).getValue() > aa.organizedAttorneyCities.get(2).getValue());
    }
    
    @Test
    public void testOrganizedStatesList() {
        AttorneyAnalysis aa = new AttorneyAnalysis(cases);
        assertTrue("organizedAttorneyStates comparator works.", 
                aa.organizedAttorneyStates.get(1).getValue() > aa.organizedAttorneyStates.get(2).getValue());
    }

    @Test
    public void testTopAttorneyCities() {
        AttorneyAnalysis aa = new AttorneyAnalysis(cases);
        String topTenAttorneyCities = aa.getTopTenAttorneyCities();
        assertTrue("Top ten attorney cities should not be null", topTenAttorneyCities != null);
    }
    
    @Test
    public void testTopAttorneyStates() {
        AttorneyAnalysis aa = new AttorneyAnalysis(cases);
        String topTenAttorneyStates = aa.getTopTenAttorneyStates();
        assertTrue("Top ten attorney states should not be null", topTenAttorneyStates != null);
    }
}
