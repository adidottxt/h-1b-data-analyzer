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
        for (int i = 0; i < cases.size(); i++) {            
            
            String job = "CHIEF EXECUTIVE OFFICER";
            String job1 = "CHIEF TECHNOLOGY OFFICER";
            String job2 = "CHIEF OPERATING OFFICER";
            String job3 = "CHIEF FINANCIAL OFFICER";
            String job4 = "CHIEF MARKETING OFFICER";
            String job5 = "CHIEF INFORMATION OFFICER";
            String job6 = "CHIEF HOSPITALITY OFFICER";
            String job7 = "CHIEF DIGITAL OFFICER";
            String job8 = "CHIEF CREATIVE OFFICER";
            String job9 = "CHIEF PRODUCT OFFICER";
            String job10 = "CHIEF MEDICAL OFFICER";
            String job11 = "CHIEF INVESTMENT OFFICER";
            String job12 = "CHIEF EXPERIENCE OFFICER";
            
            String newJob = cases.get(i).jobTitle;

            if (newJob.contains(job) || newJob.contains(job1) || newJob.contains(job2) 
                    || newJob.contains(job3) || newJob.contains(job4) || newJob.contains(job5)
                     || newJob.contains(job6) || newJob.contains(job7) || newJob.contains(job8)
                     || newJob.contains(job9) || newJob.contains(job10) || newJob.contains(job11)
                     || newJob.contains(job12)) {
                
                String city = cases.get(i).worksiteCity.toUpperCase() + ", " + cases.get(i).worksiteState;
                
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
    public String getTopTenJobTitles(String city) {
        StringBuilder sa = new StringBuilder();
        sa.append("\n");
        sa.append("\n");
        
        for (int i = 0; i < cases.size(); i++) { 
            String newCity = cases.get(i).employerCity.toUpperCase() + ", " + cases.get(i).employerState;
            
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
        
        sa.append("The top 10 jobs and their respective counts in " + city.toUpperCase() + " are...");
        sa.append("\n");
        sa.append("\n");
        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedJobCount.get(i).getKey() + "\n");
            sa.append("Count: " + organizedJobCount.get(i).getValue() + "\n");
            sa.append("\n");
        }
        sa.append("\n");

        return sa.toString();
    }
    
  //avg dif b/w submitted pay and prev wage by job
    public String getAverageWageDifferenceByJob(String job) {
        StringBuilder sa = new StringBuilder();
        
        double averageWageDifference = 0.0;
        double averagePrevailingWage = 0.0;
        double averageWage = 0.0;
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob)) {
                if (averageWageDifference == 0) {
                    averageWageDifference = cases.get(i).wageDifference;
                    averageWage = cases.get(i).wageRate;
                    averagePrevailingWage = cases.get(i).prevailingWage;
                } else {
                    averageWageDifference = (averageWageDifference + cases.get(i).wageDifference) / 2; 
                    averageWage = (averageWage + cases.get(i).wageRate) / 2;
                    averagePrevailingWage = (averagePrevailingWage + cases.get(i).prevailingWage) / 2;
                }      
            }   
        }
        
        int finalWageDifferenceAverage = (int) averageWageDifference;
        int finalAverageWage = (int) averageWage;
        int finalAveragePrevWage = (int) averagePrevailingWage;
        
        sa.append("The average wage difference for the given job (" + job + ") is $" + finalWageDifferenceAverage + ".\n");
        sa.append("The average wage for the given job for H1B candidates is $" + finalAverageWage + ".\n");
        sa.append("The average \"prevailing wage\" based on H1B applications is $" + finalAveragePrevWage + ".");
        sa.append("\n");
        sa.append("\n");
        sa.append("\n");
        
        return sa.toString();
    }
    
    //top 10 popular job titles
    public String getTopTenJobTitles() {
        
        StringBuilder sa = new StringBuilder();
        
        sa.append("The top 10 job titles and their numbers are...");
        sa.append("\n");
        sa.append("\n");
        
        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedJobTitles.get(i).getKey() + "\n");
            sa.append("Count: " + organizedJobTitles.get(i).getValue() + "\n");
            sa.append("\n");
        }
        
        sa.append("\n");
        return sa.toString();
        
    }
    
    //top 10 popular cities for ceos
    public String getTopTenCSuiteCities() {
        
        StringBuilder sa = new StringBuilder();
        
        sa.append("The top 10 cities for C-Suite workers and their numbers are...");
        sa.append("\n");
        sa.append("\n");
        
        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedCSuiteCities.get(i).getKey() + "\n");
            sa.append("Count: " + organizedCSuiteCities.get(i).getValue() + "\n");
            sa.append("\n");
        }
        sa.append("\n");
        return sa.toString();
    }
    
    //c-suite avg pay
    public String getAverageCSuitePay() {
        String answer = getAverageCEOPay() + getAverageCFOPay() + getAverageCOOPay() + getAverageCTOPay();
        return answer;
    }
    
    //ceo average pay
    public String getAverageCEOPay() {
        StringBuilder sa = new StringBuilder();
        
        int count = 0;
        double average = 0.0;
        String job = "chief executive officer";
        String job1 = "ceo";
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob) || job1.equalsIgnoreCase(newJob)) {
                if (average == 0) {
                    average = cases.get(i).wageRate;
                    count++;
                } else {
                    average = (average + cases.get(i).wageRate) / 2;
                    count++;
                }      
            }   
        } 
        int finalAverage = (int) average;
        
        sa.append("The average wage for CEOs is $" + finalAverage + ".\n");
        sa.append("There were " + count + " CEO H-1B applications in your selected year.");
        sa.append("\n");
        
        return sa.toString();
    }
    
    public String getAverageCFOPay() {

        StringBuilder sa = new StringBuilder();
        sa.append("\n");
        
        int count = 0;
        double average = 0.0;
        String job = "chief financial officer";
        String job1 = "cfo";
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob) || job1.equalsIgnoreCase(newJob)) {
                if (average == 0) {
                    average = cases.get(i).wageRate;
                    count++;
                } else {
                    average = (average + cases.get(i).wageRate) / 2;  
                    count++;
                }      
            }   
        } 
        int finalAverage = (int) average;
        
        sa.append("The average wage for CFOs is $" + finalAverage + ".\n");
        sa.append("There were " + count + " CFO H-1B applications in your selected year.");
        sa.append("\n");
        
        return sa.toString();
    }
    
    public String getAverageCOOPay() {
        
        StringBuilder sa = new StringBuilder();
        sa.append("\n");
        
        int count = 0;
        double average = 0.0;
        String job = "chief operating officer";
        String job1 = "coo";
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob) || job1.equalsIgnoreCase(newJob)) {
                if (average == 0) {
                    average = cases.get(i).wageRate;
                    count++;
                } else {
                    average = (average + cases.get(i).wageRate) / 2;  
                    count++;
                }      
            }   
        } 
        int finalAverage = (int) average;
        
        sa.append("The average wage for COOs is $" + finalAverage + ".\n");
        sa.append("There were " + count + " COO H-1B applications in your selected year.");
        sa.append("\n");
        
        return sa.toString();
        
    }
    
    public String getAverageCTOPay() {
        
        StringBuilder sa = new StringBuilder();
        sa.append("\n");
        
        int count = 0;
        double average = 0.0;
        String job = "chief technology officer";
        String job1 = "cto";
        
        for (int i = 0; i < cases.size(); i++) { 
            
            String newJob = cases.get(i).jobTitle;

            if (job.equalsIgnoreCase(newJob) || job1.equalsIgnoreCase(newJob)) {
                if (average == 0) {
                    average = cases.get(i).wageRate;
                    count++;
                } else {
                    average = (average + cases.get(i).wageRate) / 2; 
                    count++;
                }      
            }   
        } 
        int finalAverage = (int) average;
        
        sa.append("The average wage for CTOs is $" + finalAverage + ".\n");
        sa.append("There were " + count + " CTO H-1B applications in your selected year.");
        sa.append("\n");
        sa.append("\n");
        sa.append("\n");
        
        return sa.toString();
    }
}
