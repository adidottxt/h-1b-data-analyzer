import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CaseTester {

    ArrayList<Case> cases = new ArrayList<>();

    @Before
    public void setup() throws IOException {
        CaseReader testCaseReader = new CaseReader("test-data/2015.csv");
        cases = testCaseReader.getCases();
    }
    
    @Test
    public void testEmployerState() {
        Case testCase = cases.get(100);
        assertTrue("Length of employer state should not exceed 2", testCase.employerState.length() <= 2);
    }
    
    @Test
    public void testWorksiteState() {
        Case testCase = cases.get(100);
        assertTrue("Length of worksite state should not exceed 2", testCase.worksiteState.length() <= 2);
    }
    
    @Test
    public void testWage() {
        Case testCase = cases.get(100);
        assertTrue("Wage should not be less than 5000", testCase.wageRate >= 5000);
    }
    
    @Test
    public void testEmployerCity() {
        Case testCase = cases.get(100);
        assertFalse("Employer city should contain no commas", testCase.employerCity.contains(","));
    }   
}
