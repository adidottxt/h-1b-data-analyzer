import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * This method consists of all methods that analyze H-1B cases on 
 * questions that pertain to unversities. This includes the top 10 
 * universities that put in H-1B applications for the given year, and 
 * the top 10 H-1B jobs at universities.
 * @author adi
 *
 */
public class UniversityAnalysis {
    
    ArrayList<Case> cases = new ArrayList<Case>();
    
    HashMap<String, Integer> uniApplications = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedUniApplications;
    
    HashMap<String, Integer> uniJobs = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedUniJobs;
    
    /**
     * This is the constructor.
     * It reads in all H-1B cases from CaseReader, and analyzes job and 
     * university data using the runAnalysis() method.
     * @param inCases: the ArrayList of cases that is used for this class
     * @throws IOException
     */
    public UniversityAnalysis(ArrayList<Case> inCases) {
        cases = inCases;
        runAnalysis();
    }
    
    /**
     * This method calls two methods to analyze university and job data.
     */
    public void runAnalysis() {
        uniAppAnalyzer();
        uniJobAnalyzer();
    }
    
    /**
     * This method is where university information is analyzed and sorted.
     */
    public void uniAppAnalyzer() {
        
        for (int i = 0; i < cases.size(); i++) {            
            
            String test = "UNIVERSITY";
            
            String employer = cases.get(i).employerName.toUpperCase();

            if (employer.contains(test)) {
                
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
    
    /**
     * This method is where university job information is analyzed and sorted.
     */
    public void uniJobAnalyzer() {
        
        for (int i = 0; i < cases.size(); i++) {            
            
            String test = "UNIVERSITY";
            
            String employer = cases.get(i).employerName;

            if (employer.contains(test)) {
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
    
    /**
     * This is the comparator used in the analyzer methods to 
     * sort the universities/jobs and their counts.
     */
    Comparator<Entry<String, Integer>> highToLowComparator = new Comparator<Entry<String, Integer>>() {
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            Integer count1 = entry1.getValue();
            Integer count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    /**
     * This method returns the top ten universities based on the number of 
     * H-1B applications submitted from this universities in the given year's 
     * worth of data.
     * @return the top ten universities
     */
    public String getTopTenUniversityApplicants() {
        
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        sa.append("The top 10 universities and their number of H-1B applications are...");
        sa.append("\n");
        sa.append("\n");
        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedUniApplications.get(i).getKey() + "\n");
            sa.append("Count: " + organizedUniApplications.get(i).getValue() + "\n");
            sa.append("\n");
        }
        return sa.toString();
    }
    
    /**
     * This method returns the top ten university H-1B jobs based on H-1B 
     * applications submitted from universities.
     * @return the top ten university H-1B jobs
     */
    public String getTopTenUniversityJobs() {
        
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        sa.append("The top 10 university H-1B jobs and their number of applications are...");
        sa.append("\n");
        sa.append("\n");
        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedUniJobs.get(i).getKey() + "\n");
            sa.append("Count: " + organizedUniJobs.get(i).getValue() + "\n");
            sa.append("\n");
        }
        
        return sa.toString();
    }
}
