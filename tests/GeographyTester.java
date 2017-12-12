import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class GeographyTester {

    ArrayList<Case> cases = new ArrayList<>();
    HashMap<String, Integer> populationData = new HashMap<String, Integer>();
    HashMap<String, Integer> incomeData = new HashMap<String, Integer>();

    @Before
    public void setup() throws IOException {
        CaseReader testCaseReader = new CaseReader("test-data/2015.csv");
        cases = testCaseReader.getCases();
    }
    
    @Test
    public void testTopTenCities() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(cases);
        String topTenCities = ga.getTopTenCities();
        assertTrue("Top 10 cities list should not be null.", topTenCities != null);
    }
    
    @Test
    public void testOrganizedCitiesList() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(cases);
        assertTrue("organizedCities comparator works.", 
                ga.organizedCities.get(1).getValue() > ga.organizedCities.get(2).getValue());
    }
    
    @Test
    public void testTopTenStates() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(cases);
        String topTenStates = ga.getTopTenStates();
        assertTrue("Top ten states list should not be null", topTenStates != null);
    }
    
    @Test
    public void testOrganizedStatesList() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(cases);
        assertTrue("organizedStates comparator works.", 
                ga.organizedStates.get(1).getValue() > ga.organizedStates.get(2).getValue());
    }
    
    @Test
    public void testTopTenWageDifCities() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(cases);
        String topTenWageDifCities = ga.getTopTenCitiesForWageDif();
        assertTrue("Top 10 cities for wage difference list should not be null.", 
                topTenWageDifCities != null);
    }
    
    @Test
    public void testOrganizedWageDifCities() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(cases);
        assertTrue("organizedCityWageDifference comparator works.", 
                ga.organizedCityWageDifference.get(1).getValue() > 
                    ga.organizedCityWageDifference.get(2).getValue());
    }
    
    @Test
    public void testAvgVsH1BWage() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(cases);
        String avgVsH1BWage = ga.getAverageWagevsAverageH1BWage("CA");
        assertTrue("The average wage vs H1B wage answer should not be null", 
                avgVsH1BWage != null);
    }
    
    @Test
    public void testStatePopVSH1BPop() throws IOException {
        GeographyAnalysis ga = new GeographyAnalysis(cases);
        String statePopVsH1BPop = ga.getH1BPopulationPercentageVsStatePopulation("CA");
        assertTrue("The state population vs H1B population answer should not be null", 
                statePopVsH1BPop != null);
    }

}
