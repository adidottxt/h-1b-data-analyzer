import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SimulationTester {
    
    ArrayList<Case> cases = new ArrayList<>();

    @Before
    public void setup() throws IOException {
        CaseReader testCaseReader = new CaseReader("test-data/2015.csv");
        cases = testCaseReader.getCases();
    }
    
    @Test
    public void testWageBasedSimulation() throws IOException {
        WageBasedSimulation testSim = new WageBasedSimulation(cases);
        
        String simWinningResults = testSim.runSimulation();
        String simLosingResults = testSim.runLosingSimulation();
        
        assertTrue("Winning cases list should not be null.", simWinningResults != null);
        assertTrue("Losing cases list should not be null.", simLosingResults != null);
    }
    
    @Test
    public void testWinningResults() throws IOException {
        WageBasedSimulation testSim = new WageBasedSimulation(cases);
        assertTrue("testSim runSimulation() comparator works.", 
                testSim.winningCases.get(1).wageRate >= testSim.winningCases.get(2).wageRate);
    }
    
    @Test
    public void testLosingResults() throws IOException {
        WageBasedSimulation testSim = new WageBasedSimulation(cases);
        assertTrue("testSim runLosingSimulation() comparator works.", 
                testSim.losingCases.get(1).wageRate >= testSim.losingCases.get(2).wageRate);
    }
        
    @Test
    public void testWageBasedSimulationWinningCases() throws IOException {
        WageBasedSimulation testSim = new WageBasedSimulation(cases);
        
        assertTrue("The number of winning cases must be equal to 85,000", 
                testSim.getWinningCases().size() == 85000);
        
    }

}
