import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class CompanyAnalysis {

    ArrayList<Case> cases = new ArrayList<Case>();
    
    HashMap<String, Integer> companies = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedCompanies;
    
    
    public CompanyAnalysis(ArrayList<Case> inCases) {
        cases = inCases;
        companyAnalyzer();
    }
    
    public void companyAnalyzer() {        
        for (int i = 0; i < cases.size(); i++) {            
            String company = cases.get(i).employerName;
            
            if (company.length() > 5) {
                if (companies.containsKey(company)) {
                    int count = companies.get(company) + 1;
                    companies.put(company, count);
                } else {
                    companies.put(company, 1);
                }
            }
        }
        
        Collection<Entry<String, Integer>> attorneySet = companies.entrySet();
        organizedCompanies =
                new ArrayList<Entry<String, Integer>>(attorneySet);
        Collections.sort(organizedCompanies, highToLowComparator);
    }
    
    Comparator<Entry<String, Integer>> highToLowComparator = new Comparator<Entry<String, Integer>>() {
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            Integer count1 = entry1.getValue();
            Integer count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    
    public String getTopTenCompanies() {

        StringBuilder sa = new StringBuilder();
        
        sa.append("\n");
        sa.append("\n");
        sa.append("--------------------------------------------------------------------------");
        sa.append("\n");
        sa.append("\n");
        
        sa.append("The top 10 companies and their respective number of applications are...\n");
        sa.append("\n");
        for (int i = 0; i < 10; i++) { 
            sa.append((i + 1) + ". " + organizedCompanies.get(i).getKey() + "\n");
            sa.append("Number of Applications: " + organizedCompanies.get(i).getValue() + "\n");
            sa.append("\n");
        }
        return sa.toString();
    }
}
