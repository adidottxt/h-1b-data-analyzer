import java.io.IOException;

public class CaseTester {
    
    public static void main(String[] args) throws IOException {
        
        CaseReader ca = new CaseReader("2016.csv");
        
        System.out.println(ca.getNumberOfCases());        

        GeographyAnalysis ga = new GeographyAnalysis(ca.getCases());
        ga.getTopTenCitiesForWageDif();

    }
}
