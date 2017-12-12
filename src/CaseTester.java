import java.io.IOException;

public class CaseTester {
    
    public static void main(String[] args) throws IOException {
        
        CaseReader ca = new CaseReader("test-data/2017.csv");
//        CensusDataReader cdr = new CensusDataReader("popdatatest.xlsx");
//        cdr.getPercentageOfTotalPopulation("IN");
//        
        
        WageBasedSimulation wa = new WageBasedSimulation(ca.getCases());
        System.out.println(wa.runSimulation());
        System.out.println(wa.runLosingSimulation());
        
//        AttorneyAnalysis aa = new AttorneyAnalysis(ca.getCases());
//        GeographyAnalysis ga = new GeographyAnalysis(ca.getCases());
//        JobAnalysis ja = new JobAnalysis(ca.getCases());
//        UniversityAnalysis ua = new UniversityAnalysis(ca.getCases());
        
//        System.out.println(aa.getTopTenAttorneyCities());
//        System.out.println(aa.getTopTenAttorneyStates());
//        System.out.println(aa.getTopTenBusyAttorneys());
        
//        System.out.print(ga.getTopTenCitiesForWageDif());
//        System.out.print(ga.getTopTenStates());
//        System.out.print(ga.getTopTenCities());
//        System.out.print(ga.getAverageWagevsAverageH1BWage("CA"));
//        System.out.print(ga.getH1BPopulationPercentageVsStatePopulation("CA"));

//        System.out.println(ja.getTopTenCSuiteCities());
//        System.out.println(ja.getAverageCEOPay());
//        System.out.println(ja.getTopTenJobTitles());
//        System.out.println(ja.getTopTenJobTitles("new york"));
//        System.out.println(ja.getAverageWageDifferenceByJob("software developer"));
//        
//        System.out.println(ua.getTopTenUniversityApplicants());
//        System.out.println(ua.getTopTenUniversityJobs());

    }
}
