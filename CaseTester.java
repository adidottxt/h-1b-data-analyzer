import java.io.IOException;

public class CaseTester {
    
    public static void main(String[] args) throws IOException {
        
        
        CaseReader ca = new CaseReader("2017.csv");
        ca.createCases();
        
        System.out.println(ca.getNumberOfCases());
        
//        CensusDataReader cr = new CensusDataReader("pop-data.xlsx");
        

//        GeographyAnalysis ga = new GeographyAnalysis(ca.getCases());
//        ga.getH1BPopulationPercentageVsStatePopulation("CA");

    }
}
