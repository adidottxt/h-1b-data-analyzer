import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class JobTester {

    
    ArrayList<Case> cases = new ArrayList<>();
    HashMap<String, Integer> populationData = new HashMap<String, Integer>();
    HashMap<String, Integer> incomeData = new HashMap<String, Integer>();

    @Before
    public void setup() throws IOException {
        CaseReader testCaseReader = new CaseReader("test-data/2015.csv");
        cases = testCaseReader.getCases();
    }
    
    @Test
    public void testTopTenJobTitles() {
        JobAnalysis ja = new JobAnalysis(cases);     
        String topTenJobTitles = ja.getTopTenJobTitles();
        assertTrue("Top 10 job titles list should not be null.", topTenJobTitles != null);
    }
    
    @Test
    public void testOrganizedJobTitles() throws IOException {
        JobAnalysis ja = new JobAnalysis(cases);     
        assertTrue("organizedJobTitles comparator works.", 
                ja.organizedJobTitles.get(1).getValue() > ja.organizedJobTitles.get(2).getValue());
    }
    
    @Test
    public void testTopTenJobTitlesByCity() {
        JobAnalysis ja = new JobAnalysis(cases);
        String topTenJobTitlesInCity = ja.getTopTenJobTitles("SUNNYVALE, CA");
        assertTrue("Top 10 job titles for a given city should not be null", 
                topTenJobTitlesInCity != null);
    }
    
    @Test
    public void testTopTenCSuiteCities() {
        JobAnalysis ja = new JobAnalysis(cases);
        String topTenCSuiteCities = ja.getTopTenCSuiteCities();
        assertTrue("The top 10 C-Suite cities list should not be null", 
                topTenCSuiteCities != null);
    }
    
    @Test
    public void testOrganizedCSuiteCities() throws IOException {
        JobAnalysis ja = new JobAnalysis(cases);     
        assertTrue("organizedCSuiteCities comparator works.", 
                ja.organizedCSuiteCities.get(1).getValue() > ja.organizedCSuiteCities.get(2).getValue());
    }

    @Test
    public void testAverageWageDifByJob() {
        JobAnalysis ja = new JobAnalysis(cases);
        String averageWageDifByJob = ja.getAverageWageDifferenceByJob("SOFTWARE DEVELOPER");
        assertTrue("The answer to the average wage difference for a given job should not be null", 
                averageWageDifByJob != null);
    }
    
    @Test
    public void testCSuitePay() {
        JobAnalysis ja = new JobAnalysis(cases);
        String cSuitePay = ja.getAverageCSuitePay();
        assertTrue("The C-Suite Wage analyzer answer should not be null", 
                cSuitePay != null);
        
    }
}
