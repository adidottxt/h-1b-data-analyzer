import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * This method consists of all methods that analyze H-1B cases on 
 * questions that pertain to jobs. This includes the top 10 H-1B 
 * jobs, top 10 H-1B jobs within a city, the average difference between 
 * wage offered and prevailing wage for a given job, etc. It also contains 
 * methods that are specific to C-Suite H-1B applications.
 * @author adi
 *
 */
public class JobAnalysis {
    
    ArrayList<Case> cases = new ArrayList<Case>();
    
    HashMap<String, Integer> jobCount = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedJobCount;
    
    HashMap<String, Integer> jobTitles = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedJobTitles;
    
    HashMap<String, Integer> cSuiteCities = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedCSuiteCities;
    
    /**
     * This is the constructor. It reads in an ArrayList of cases created from 
     * the CaseReader class, and then uses runAnalysis() to analyze job title
     * information and C-Suite city information from those cases. 
     * @param inCases
     */
    public JobAnalysis(ArrayList<Case> inCases) {
        cases = inCases;
        runAnalysis();
    }
    
    /**
     * This method runs two other methods that analyze job title information
     * and C-Suite city information to be used in other methods within this class.
     */
    public void runAnalysis() {
        jobTitleAnalyzer();
        cSuiteCityAnalyzer();
    }
    
    /**
     * This method is where job title information is analyzed and sorted.
     */
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
    
    /**
     * This method is where C-Suite city information is analyzed and sorted.
     */
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
    
    /**
     * This is the comparator used in the analyzer methods to 
     * sort the jobs/ciites and their respective counts.
     */
    Comparator<Entry<String, Integer>> highToLowComparator = new Comparator<Entry<String, Integer>>() {
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            Integer count1 = entry1.getValue();
            Integer count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    /**
     * This method takes a city as its input and returns the top 10 
     * H-1B job titles for that city based on the year's worth of 
     * H-1B data provided.
     * @param city: the city used as an input
     * @return the top 10 H-1B job titles in the given city.
     */
    public String getTopTenJobTitles(String city) {
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
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
        return sa.toString();
    }
    
    /**
     * This method takes in a job as an input and returns the average difference 
     * between the wage offered for these positions in H-1B applications and the 
     * average prevailing wage submitted for those jobs.
     * @param job: the job input
     * @return the average wage difference
     */
    public String getAverageWageDifferenceByJob(String job) {
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
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
        
        if (finalAverageWage != 0) {
            sa.append("The average wage for the given job for H-1B candidates is $" + 
                    finalAverageWage + ".\n");
            sa.append("The average \"prevailing wage\" based on H-1B applications is $" + 
                    finalAveragePrevWage + ".\n");
            sa.append("The average wage difference for the given job (" + job + ") , "
                    + "therefore, is $" + finalWageDifferenceAverage + ".\n");
        } else {
           sa.append("There were no H-1B applications for your selected job in your selected year.\n"); 
        }
        
        return sa.toString();
    }
    
    /**
     * This method returns the top 10 H-1B jobs based on the 
     * year's worth of H-1B data provided.
     * @return the top 10 H-1B jobs.
     */
    public String getTopTenJobTitles() {
        
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        sa.append("The top 10 job titles and their numbers are...");
        sa.append("\n");
        sa.append("\n");
        
        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedJobTitles.get(i).getKey() + "\n");
            sa.append("Count: " + organizedJobTitles.get(i).getValue() + "\n");
            sa.append("\n");
        }
        return sa.toString();
    }
    
    /**
     * This method returns the top 10 cities that C-Suite H-1B
     * applications come from.
     * @return top 10 C-Suite cities
     */
    public String getTopTenCSuiteCities() {
        
        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        sa.append("The top 10 cities for C-Suite workers and their numbers are...");
        sa.append("\n");
        sa.append("\n");
        
        for (int i = 0; i < 10; i++) {
            sa.append((i + 1) + ". " + organizedCSuiteCities.get(i).getKey() + "\n");
            sa.append("Count: " + organizedCSuiteCities.get(i).getValue() + "\n");
            sa.append("\n");
        }
        return sa.toString();
    }
    
    /**
     * This method calls a number of methods to get the average pay 
     * based on H-1B applications for CEOs, CFOs, COOs, and CTOs.
     * @return the average C-Suite pay in H-1B applications.
     */
    public String getAverageCSuitePay() {
        
        String answer = "\n\n" + "--------------------------------------------------------------------------" +
                "\n\n" + getAverageCEOPay() + getAverageCFOPay() + getAverageCOOPay() + getAverageCTOPay();
        return answer;
    }
    
    /**
     * This method returns the average pay for CEOs in H-1B applications
     * @return average CEO wage
     */
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
    
    /**
     * This method returns the average pay for CFOs in H-1B applications
     * @return average CFO wage
     */
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
    
    /**
     * This method returns the average pay for COOs in H-1B applications
     * @return average COO wage
     */
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
    
    /**
     * This method returns the average pay for CTOs in H-1B applications
     * @return average CTO wage
     */
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
