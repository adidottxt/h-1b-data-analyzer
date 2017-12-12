import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class UniversityTester {
    
    ArrayList<Case> cases = new ArrayList<>();

    @Before
    public void setup() throws IOException {
        CaseReader testCaseReader = new CaseReader("test-data/2015.csv");
        cases = testCaseReader.getCases();
    }
    
    @Test
    public void testUniversityJobs() {
        UniversityAnalysis ua = new UniversityAnalysis(cases);
        String topTenUniversityJobs = ua.getTopTenUniversityJobs();
        assertTrue("Top 10 university jobs list should not be null.", 
                topTenUniversityJobs != null);
    }
    
    @Test
    public void testOrganizedUniJobs() {
        UniversityAnalysis ua = new UniversityAnalysis(cases);
        assertTrue("organizedUniJobs comparator works.", 
                ua.organizedUniJobs.get(1).getValue() > 
                    ua.organizedUniJobs.get(2).getValue());
    }
    
    @Test
    public void testUniversityApplicants() {
        UniversityAnalysis ua = new UniversityAnalysis(cases);
        String topTenUniversityApplicants = ua.getTopTenUniversityApplicants();
        assertTrue("Top ten university applicants list should not be null", 
                topTenUniversityApplicants != null);
    }
    
    @Test
    public void testOrganizedUniApplications() {
        UniversityAnalysis ua = new UniversityAnalysis(cases);
        assertTrue("organizedUniApplications comparator works.", 
                ua.organizedUniApplications.get(1).getValue() > 
                    ua.organizedUniApplications.get(2).getValue());
    }
    
    @Test
    public void testUniversitiesOnly() {
        UniversityAnalysis ua = new UniversityAnalysis(cases);
        String topTenUniversityApplicants = ua.getTopTenUniversityApplicants();
        assertTrue("The list contains, uh, universities.", 
                topTenUniversityApplicants.contains("UNIVERSITY"));
    }

}
