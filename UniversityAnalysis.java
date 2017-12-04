import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class UniversityAnalysis {
    
    ArrayList<Case> cases = new ArrayList<Case>();
    
    HashMap<String, Integer> uniApplications = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedUniApplications;
    
    HashMap<String, Integer> uniJobs = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedUniJobs;
    
    public UniversityAnalysis(ArrayList<Case> inCases) {
        cases = inCases;
        runAnalysis();
    }
    
    public void runAnalysis() {
        uniAppAnalyzer();
        uniJobAnalyzer();
    }
    
    public void uniAppAnalyzer() {
        
        System.out.println("Looking at university application data...");
        for (int i = 0; i < cases.size(); i++) {            
            
            String test = "UNIVERSITY";
            String test1 = "UNI";
            
            String employer = cases.get(i).employerName;

            if (employer.contains(test) || employer.contains(test1)) {
                
                if (uniApplications.containsKey(employer)) {
                    int count = uniApplications.get(employer) + 1;
                    uniApplications.put(employer, count);
                } else {
                    uniApplications.put(employer, 1);
                }
            }
        }      
        
        Collection<Entry<String, Integer>> uniApplicationsSet = uniApplications.entrySet();
        organizedUniApplications =
                new ArrayList<Entry<String, Integer>>(uniApplicationsSet);
        Collections.sort(organizedUniApplications, highToLowComparator);
    }
    
    public void uniJobAnalyzer() {
        
        System.out.println("Looking at university job data...");
        for (int i = 0; i < cases.size(); i++) {            
            
            String test = "UNIVERSITY";
            String test1 = "UNI";
            
            String employer = cases.get(i).employerName;

            if (employer.contains(test) || employer.contains(test1)) {
                String job = cases.get(i).jobTitle;
                if (uniJobs.containsKey(job)) {
                    int count = uniJobs.get(job) + 1;
                    uniJobs.put(job, count);
                } else {
                    uniJobs.put(job, 1);
                }
            }
        }      
        
        Collection<Entry<String, Integer>> uniJobsSet = uniJobs.entrySet();
        organizedUniJobs =
                new ArrayList<Entry<String, Integer>>(uniJobsSet);
        Collections.sort(organizedUniJobs, highToLowComparator);
    }
    
    Comparator<Entry<String, Integer>> highToLowComparator = new Comparator<Entry<String, Integer>>() {
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            Integer count1 = entry1.getValue();
            Integer count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    //most apps by university
    public void getTopTenUniversityApplicants() {
        System.out.println("The top 10 universities and their number of applications are...");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("University: \"" + organizedUniApplications.get(i).getKey() +
                    "\" ––> Count: " + organizedUniApplications.get(i).getValue());
        }
        System.out.println();
        System.out.println();
    }
    
    //top 10 job titles for uni apps
    public void getTopTenUniversityJobs() {
        System.out.println("The top 10 university jobs and their number of applications are...");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("Job: \"" + organizedUniJobs.get(i).getKey() +
                    "\" ––> Count: " + organizedUniJobs.get(i).getValue());
        }
        System.out.println();
        System.out.println();
    }
}
