import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class AttorneyAnalysis {

    ArrayList<Case> cases = new ArrayList<Case>();
    
    HashMap<String, Integer> attorneys = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedAttorneys;
    
    HashMap<String, Integer> attorneyCities = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedAttorneyCities;
    
    HashMap<String, Integer> attorneyStates = new HashMap<String, Integer>();
    List<Entry<String, Integer>> organizedAttorneyStates;
    
    public AttorneyAnalysis(ArrayList<Case> inCases) {
        cases = inCases;
        runAnalysis();
    }
    
    public void runAnalysis() {

        attorneyNameAnalyzer();
        attorneyCityAnalyzer();
        attorneyStateAnalyzer();

    }
    
    public void attorneyNameAnalyzer() {        
        for (int i = 0; i < cases.size(); i++) {            
            String attorney = cases.get(i).attorneyName;
            
            if (attorney.length() > 5) {
                if (attorneys.containsKey(attorney)) {
                    int count = attorneys.get(attorney) + 1;
                    attorneys.put(attorney, count);
                } else {
                    attorneys.put(attorney, 1);
                }
            }
        }
        
        Collection<Entry<String, Integer>> attorneySet = attorneys.entrySet();
        organizedAttorneys =
                new ArrayList<Entry<String, Integer>>(attorneySet);
        Collections.sort(organizedAttorneys, highToLowComparator);
    }
    
    //fix this
    public void attorneyCityAnalyzer() {        
        for (int i = 0; i < cases.size(); i++) {

            String attorneyCity = cases.get(i).attorneyCity + ", " + cases.get(i).attorneyState;
            if (attorneyCities.containsKey(attorneyCity)) {
                int count = attorneyCities.get(attorneyCity) + 1;
                attorneyCities.put(attorneyCity, count);
            } else {
                attorneyCities.put(attorneyCity, 1);
            } 
        }
        
        Collection<Entry<String, Integer>> attorneyCitySet = attorneyCities.entrySet();
        organizedAttorneyCities =
                new ArrayList<Entry<String, Integer>>(attorneyCitySet);
        Collections.sort(organizedAttorneyCities, highToLowComparator);
    }
    
    public void attorneyStateAnalyzer() {        
        for (int i = 0; i < cases.size(); i++) {

            String attorneyState = cases.get(i).attorneyState;
            if (attorneyStates.containsKey(attorneyState)) {
                int count = attorneyStates.get(attorneyState) + 1;
                attorneyStates.put(attorneyState, count);
            } else {
                attorneyStates.put(attorneyState, 1);
            } 
        }

        Collection<Entry<String, Integer>> attorneyStateSet = attorneyStates.entrySet();
        organizedAttorneyStates =
                new ArrayList<Entry<String, Integer>>(attorneyStateSet);
        Collections.sort(organizedAttorneyStates, highToLowComparator);
    }
    
    Comparator<Entry<String, Integer>> highToLowComparator = new Comparator<Entry<String, Integer>>() {
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            Integer count1 = entry1.getValue();
            Integer count2 = entry2.getValue();
            return count2.compareTo(count1);
        }
    };
    
    //top 10 attorneys
    public String getTopTenBusyAttorneys() {

        StringBuilder sa = new StringBuilder();
        sa.append("\n");
        sa.append("The top 10 attorneys and their respective number of applications are...\n");
        sa.append("\n");
        for (int i = 0; i < 10; i++) { 
            sa.append((i + 1) + ". " + organizedAttorneys.get(i).getKey() + "\n");
            sa.append("Number of Applications: " + organizedAttorneys.get(i).getValue() + "\n");
            sa.append("\n");
        }
        sa.append("\n");
        return sa.toString();
    }
    
    //most popular attorney city
    public String getTopTenAttorneyCities() {
        StringBuilder sa = new StringBuilder();
        sa.append("\n");
        sa.append("The top 10 attorney cities and their respective number of applications are...\n");
        sa.append("\n");
        for (int i = 1; i < 11; i++) {
            
            sa.append((i) + ". " + organizedAttorneyCities.get(i).getKey() + "\n");
            sa.append("Number of Applications: " + organizedAttorneyCities.get(i).getValue() + "\n");
            sa.append("\n");
            
        }
        sa.append("\n");
        return sa.toString();
    }
    
    //top 10 attorney states
    public String getTopTenAttorneyStates() {
        StringBuilder sa = new StringBuilder();
        sa.append("\n");
        sa.append("The top 10 attorney states and their respective number of applications are...");
        sa.append("\n");
        sa.append("\n");
        for (int i = 1; i < 11; i++) {

            sa.append((i) + ". " + organizedAttorneyStates.get(i).getKey() + "\n");
            sa.append("Number of Applications: " + organizedAttorneyStates.get(i).getValue() + "\n");
            sa.append("\n");
            
        }
        sa.append("\n");
        return sa.toString();
    }
    
}
