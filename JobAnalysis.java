import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class JobAnalysis {
    
    ArrayList<Case> cases = new ArrayList<Case>();
    
    HashMap<String, Integer> jobCount = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedJobCount;
    
    HashMap<String, Integer> jobTitles = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedJobTitles;
    
    HashMap<String, Integer> cSuiteCities = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedCSuiteCities;
    
    public JobAnalysis(ArrayList<Case> inCases) {
        cases = inCases;
        runAnalysis();
    }
    
    public void runAnalysis() {
        jobTitleAnalyzer();
        cSuiteCityAnalyzer();
    }
    
    public void jobTitleAnalyzer() {
        System.out.println("Looking at job title data...");
        for (int i = 0; i < cases.size(); i++) {            
            
            String jobTitle = cases.get(i).jobTitle;
            
            if (jobTitles.containsKey(jobTitle)) {
                int count = jobTitles.get(jobTitle) + 1;
                jobTitles.put(jobTitle, count);
            } else {
                jobTitles.put(jobTitle, 1);
            }
        }      
        
        Collection<Entry<String, Integer>> jobTitleSet = jobTitles.entrySet();
        organizedJobTitles =
                new ArrayList<Entry<String, Integer>>(jobTitleSet);
        Collections.sort(organizedJobTitles, highToLowComparator);
    }
    
    public void cSuiteCityAnalyzer() {
        System.out.println("Looking at CEO data...");
        for (int i = 0; i < cases.size(); i++) {            
            
            String job = "chief executive officer";
            String job1 = "chief technology officer";
            String job2 = "chief operating officer";
            String job3 = "chief financial officer";
            String job4 = "chief marketing officer";
            String job5 = "chief information officer";
            String job6 = "chief hospitality officer";
            String job7 = "chief digital officer";
            String job8 = "chief creative officer";
            String job9 = "chief product officer";
            String job10 = "chief medical officer";
            String job11 = "chief investment officer";
            String job12 = "chief experience officer";
            
            String newJob = cases.get(i).jobTitle.toLowerCase();

            if (newJob.contains(job) || newJob.contains(job1) || newJob.contains(job2) 
                    || newJob.contains(job3) || newJob.contains(job4) || newJob.contains(job5)
                     || newJob.contains(job6) || newJob.contains(job7) || newJob.contains(job8)
                     || newJob.contains(job9) || newJob.contains(job10) || newJob.contains(job11)
                     || newJob.contains(job12)) {
                
                String city = cases.get(i).worksiteCity + ", " + cases.get(i).worksiteState;
                
                if (cSuiteCities.containsKey(city)) {
                    int count = cSuiteCities.get(city) + 1;
                    cSuiteCities.put(city, count);
                } else {
                    cSuiteCities.put(city, 1);
                }
            }
        }      
        
        Collection<Entry<String, Integer>> cSuiteCitySet = cSuiteCities.entrySet();
        organizedCSuiteCities =
                new ArrayList<Entry<String, Integer>>(cSuiteCitySet);
        Collections.sort(organizedCSuiteCities, highToLowComparator);
        
    }
    
    Comparator<Entry<String, Integer>> highToLowComparator = new Comparator<Entry<String, Integer>>() {
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            Integer count1 = entry1.getValue();
            Integer count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
  //10 most popular titles by city
    public void getTopTenJobTitles(String city) {
        System.out.println("Looking at city job data...");
        
        for (int i = 0; i < cases.size(); i++) { 
            String newCity = cases.get(i).employerCity;
            
            if (city.equalsIgnoreCase(newCity)) {
                
                String job = cases.get(i).jobTitle;
                
                if (jobCount.containsKey(job)) {
                    int count = jobCount.get(job) + 1;
                    jobCount.put(job, count);
                } else {
                    jobCount.put(job, 1);
                }    
            }   
        }
        
        Collection<Entry<String, Integer>> jobSet = jobCount.entrySet();
        organizedJobCount =
                new ArrayList<Entry<String, Integer>>(jobSet);
        Collections.sort(organizedJobCount, highToLowComparator);
        
        System.out.println("The top 10 jobs and their respective counts in " + city + " are...");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("Job: \"" + organizedJobCount.get(i).getKey() +
                    "\" ––> Count: " + organizedJobCount.get(i).getValue());
        }
        System.out.println();
        System.out.println();
    }
    
  //avg dif b/w submitted pay and prev wage by job
    public void getAverageWageDifferenceByJob(String job) {
        System.out.println();
        System.out.println("Looking at wage difference data...");
        double average = 0.0;
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob)) {
                if (average == 0) {
                    average = cases.get(i).wageDifference;
                } else {
                    average = (average + cases.get(i).wageDifference) / 2;  
                }      
            }   
        }
        
        int finalAverage = (int) average;
        System.out.println("The average wage difference for the given job: " + job + ", is $" + finalAverage + ".");
    }
    
    //top 10 popular job titles
    public void getTopTenJobTitles() {

        System.out.println("The top 10 job titles and their numbers are...");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("Job Title: \"" + organizedJobTitles.get(i).getKey() +
                    "\" ––> Count: " + organizedJobTitles.get(i).getValue());
        }
        System.out.println();
        System.out.println();
    }
    
    //top 10 popular cities for ceos
    public void getTopTenCSuiteCities() {
        System.out.println("The top 10 cities for C-Suite workers and their numbers are...");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("City: \"" + organizedCSuiteCities.get(i).getKey() +
                    "\" ––> Count: " + organizedCSuiteCities.get(i).getValue());
        }
        System.out.println();
        System.out.println();
    }
    
    //c-suite avg pay
    public void getAverageCSuitePay() {
        getAverageCEOPay();
        getAverageCFOPay();
        getAverageCOOPay();
        getAverageCTOPay();
        
    }
    
    //ceo average pay
    public void getAverageCEOPay() {
        System.out.println();
        System.out.println("Looking at CEO wage data...");
        
        double average = 0.0;
        String job = "chief executive officer";
        String job1 = "ceo";
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob) || job1.equalsIgnoreCase(newJob)) {
                if (average == 0) {
                    average = cases.get(i).wageRate;
                } else {
                    average = (average + cases.get(i).wageRate) / 2;  
                }      
            }   
        } 
        int finalAverage = (int) average;
        
        System.out.println("The average wage for CEOs is $" + finalAverage + ".");
        
    }
    
    public void getAverageCFOPay() {
        System.out.println();
        System.out.println("Looking at CFO wage data...");
        
        double average = 0.0;
        String job = "chief financial officer";
        String job1 = "cfo";
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob) || job1.equalsIgnoreCase(newJob)) {
                if (average == 0) {
                    average = cases.get(i).wageRate;
                } else {
                    average = (average + cases.get(i).wageRate) / 2;  
                }      
            }   
        } 
        int finalAverage = (int) average;
        
        System.out.println("The average wage for CFOs is $" + finalAverage + ".");
        
    }
    
    public void getAverageCOOPay() {
        System.out.println();
        System.out.println("Looking at COO wage data...");
        
        double average = 0.0;
        String job = "chief operating officer";
        String job1 = "coo";
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob) || job1.equalsIgnoreCase(newJob)) {
                if (average == 0) {
                    average = cases.get(i).wageRate;
                } else {
                    average = (average + cases.get(i).wageRate) / 2;  
                }      
            }   
        } 
        int finalAverage = (int) average;
        
        System.out.println("The average wage for COOs is $" + finalAverage + ".");
        
    }
    
    public void getAverageCTOPay() {
        System.out.println();
        System.out.println("Looking at CTO wage data...");
        
        double average = 0.0;
        String job = "chief technology officer";
        String job1 = "cto";
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob) || job1.equalsIgnoreCase(newJob)) {
                if (average == 0) {
                    average = cases.get(i).wageRate;
                } else {
                    average = (average + cases.get(i).wageRate) / 2;  
                }      
            }   
        } 
        int finalAverage = (int) average;
        
        System.out.println("The average wage for CTOs is $" + finalAverage + ".");
        
    }
}
