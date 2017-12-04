import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene1, scene2, scene3, scene4;
    CaseReader ca;
    CensusDataReader cr;
    ArrayList<Case> cases = new ArrayList<Case>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //Labels
        Label label1 = new Label("Welcome!");
        Label label2 = new Label("Pick the year you want to analyze.");
        Label label3 = new Label("Pick the questions you'd like the answer to.");
        Label label4 = new Label("Here are your answers...");

        //Question CheckBoxes
        CheckBox question1 = new CheckBox("Top 10 Cities");
        CheckBox question2 = new CheckBox("Top 10 States");
        CheckBox question3 = new CheckBox("Top 10 Cities With Highest Wage Dif");
        CheckBox question4 = new CheckBox("Average Wage vs H1B Wage in State");
        CheckBox question5 = new CheckBox("State Pop vs H1B Pop");
        CheckBox question6 = new CheckBox("Top 10 Job Titles By City");
        CheckBox question7 = new CheckBox("Avg Wage Dif By Job");
        CheckBox question8 = new CheckBox("Top 10 Job Titles");
        CheckBox question9 = new CheckBox("Top 10 C-Suite Cities");
        CheckBox question10 = new CheckBox("Average C-Suite Pay for Given Year");
        CheckBox question11 = new CheckBox("Top 10 Attorneys");
        CheckBox question12 = new CheckBox("Top 10 Attorney Cities");
        CheckBox question13 = new CheckBox("Top 10 Attorney States");
        CheckBox question14 = new CheckBox("Top 10 Universities in terms of Applications");
        CheckBox question15 = new CheckBox("Top 10 University Jobs");

        //Button 0
        Button button0 = new Button("Exit");
        button0.setOnAction(e -> window.close());
        
        //Button 1
        Button button1 = new Button("Start");
        button1.setOnAction(e -> {
            window.setScene(scene2);
        });

        //Button 2
        Button button2 = new Button("Go back");
        button2.setOnAction(e -> window.setScene(scene1));
        
        //Button 3
        Button button3 = new Button("Confirm Year");        
        
        //Button 4
        Button button4 = new Button("Go back");
        button4.setOnAction(e -> window.setScene(scene2));
        
        //Button 5
        Button button5 = new Button("Confirm Questions");
        button5.setOnAction(e -> {
            try {
                handleOptions(question1, question2, question3, question4, question5, question6, 
                        question7, question8, question9, question10, question11, question12,
                        question13, question14, question15);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            window.setScene(scene4);
        });
        
        //Button 6
        Button button6 = new Button("Go back");
        button6.setOnAction(e -> window.setScene(scene3));
        
        //Years Drop Down
        ChoiceBox<String> allYears = new ChoiceBox<>();
        allYears.getItems().addAll("2008", "2009", "2010", "2011", "2012", 
                "2013", "2014", "2015", "2016", "2017");
        allYears.setValue("2008");
        button3.setOnAction(e -> {
            try {
                getYearChoice(allYears);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            window.setScene(scene3);
        });
        
        //State Drop Down
        ChoiceBox<String> allStates = new ChoiceBox<>();
        allStates.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL",
                 "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA",
                  "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
                   "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
                    "VA", "WA", "WV", "WI", "WY");
        allStates.setValue("AL");

        //Layout 1        
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(350, 10, 10, 475));
        grid1.setVgap(10);
        grid1.setHgap(1);
        
        GridPane.setConstraints(label1, 0, 0);
        GridPane.setConstraints(button1, 0, 4);
        grid1.getChildren().addAll(label1, button1);
        scene1 = new Scene(grid1, 1100, 850);

        //Layout 2
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(50, 50, 50, 100));
        grid2.setVgap(10);
        grid2.setHgap(0);
        
        GridPane.setConstraints(label2, 0, 0);
        GridPane.setConstraints(allYears, 0, 4);
        GridPane.setConstraints(button3, 0, 53);
        GridPane.setConstraints(button2, 1, 53);
        
        grid2.getChildren().addAll(label2, allYears, button3, button2);
        scene2 = new Scene(grid2, 1100, 850);
        
        //Layout 3
        
        GridPane grid3 = new GridPane();
        grid3.setPadding(new Insets(50, 50, 50, 100));
        grid3.setVgap(10);
        grid3.setHgap(0);
        
        GridPane.setConstraints(label3, 0, 0);
        GridPane.setConstraints(question1, 0, 2);
        GridPane.setConstraints(question2, 0, 3);
        GridPane.setConstraints(question3, 0, 4);
        GridPane.setConstraints(question4, 0, 5);
        GridPane.setConstraints(question5, 0, 6);
        GridPane.setConstraints(question6, 0, 7);
        GridPane.setConstraints(question7, 0, 8);
        GridPane.setConstraints(question8, 0, 9);
        GridPane.setConstraints(question9, 0, 10);
        GridPane.setConstraints(question10, 0, 11);
        GridPane.setConstraints(question11, 0, 12);
        GridPane.setConstraints(question12, 0, 13);
        GridPane.setConstraints(question13, 0, 14);
        GridPane.setConstraints(question14, 0, 15);
        GridPane.setConstraints(question15, 0, 16);
        GridPane.setConstraints(button5, 0, 23);
        GridPane.setConstraints(button4, 1, 23);

        
        grid3.getChildren().addAll(label3, question1, question2, question3, question4,
                question5, question6, question7, question8, question9, question10,
                question11, question12, question13, question14, question15, button5, button4);
        scene3 = new Scene(grid3, 1100, 850);
        
        //Layout 4
        GridPane grid4 = new GridPane();
        grid4.setPadding(new Insets(50, 50, 50, 100));
        grid4.setVgap(10);
        grid4.setHgap(0);
        
        GridPane.setConstraints(label4, 0, 0);
        GridPane.setConstraints(button6, 0, 56);
        GridPane.setConstraints(button0, 1, 56);
       
        grid4.getChildren().addAll(label4, button6, button0);
        scene4 = new Scene(grid4, 1100, 850);
        
        //css import
        String css = "style.css";
        
        scene1.getStylesheets().add(css);
        scene2.getStylesheets().add(css);
        scene3.getStylesheets().add(css);
        scene4.getStylesheets().add(css);

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("H1B Data Analyzer");
        window.show();
    }
    
    private void handleOptions(CheckBox question1, CheckBox question2,
            CheckBox question3, CheckBox question4, CheckBox question5, CheckBox question6, 
            CheckBox question7, CheckBox question8, CheckBox question9, CheckBox question10, 
            CheckBox question11, CheckBox question12, CheckBox question13, CheckBox question14, 
            CheckBox question15) throws IOException {
        
        GeographyAnalysis ga = new GeographyAnalysis(cases);
        JobAnalysis ja = new JobAnalysis(cases);
        AttorneyAnalysis aa = new AttorneyAnalysis(cases);
        UniversityAnalysis ua = new UniversityAnalysis(cases);
        
        if(question1.isSelected()) {
            
            ga.getTopTenCities();
            
        } if(question2.isSelected()) {
          
            ga.getTopTenStates();
            
        } if(question3.isSelected()) {
            
            ga.getTopTenCitiesForWageDif();
            
        } if(question4.isSelected()) {
            
            //ga.getAverageWagevsAverageH1BWage(insert string here);
            
        } if(question5.isSelected()) {
            
            //ga.getH1BPopulationPercentageVsStatePopulation(insert string here);
            
        } if(question6.isSelected()) {
            
            //ja.getTopTenJobTitles(insert string here);
            
        } if(question7.isSelected()) {
            
            //ja.getAverageWageDifferenceByJob(String job);
            
        } if(question8.isSelected()) {
            
            ja.getTopTenJobTitles();
            
        } if(question9.isSelected()) {
            
            ja.getTopTenCSuiteCities();
            
        } if(question10.isSelected()) {
            
            ja.getAverageCSuitePay();
            
        } if(question11.isSelected()) {
            
            aa.getTopTenBusyAttorneys();
            
        } if(question12.isSelected()) {
            
            aa.getTopTenAttorneyCities();
            
        } if(question13.isSelected()) {
            
            aa.getTopTenAttorneyStates();
            
        } if(question14.isSelected()) {
            
            ua.getTopTenUniversityApplicants();
            
        } if(question15.isSelected()) {
            
            ua.getTopTenUniversityJobs();
            
        }
    }
    
    private void getYearChoice(ChoiceBox<String> allYears) throws IOException {
        String year = allYears.getValue();
        
        if (year.contains("2008")) {
            CaseReader ca = new CaseReader("2008.csv");
            cases = ca.getCases();
            
        } else if (year.contains("2009")) {
            ca = new CaseReader("2009.csv");
            cases = ca.getCases();
            
        } else if (year.contains("2010")) {
            ca = new CaseReader("2010.csv");
            cases = ca.getCases();
            
        } else if (year.contains("2011")) {
            ca = new CaseReader("2011.csv");
            cases = ca.getCases();
            
        } else if (year.contains("2012")) {
            ca = new CaseReader("2012.csv");
            cases = ca.getCases();
            
        } else if (year.contains("2013")) {
            ca = new CaseReader("2013.csv");
            cases = ca.getCases();
            
        } else if (year.contains("2014")) {
            ca = new CaseReader("2014.csv");
            cases = ca.getCases();
            
        } else if (year.contains("2015")) {
            ca = new CaseReader("2015.csv");
            cases = ca.getCases();
            
        } else if (year.contains("2016")) {
            ca = new CaseReader("2016.csv");
            cases = ca.getCases();
            
        } else if (year.contains("2017")) {
            ca = new CaseReader("2017.csv");
            cases = ca.getCases();
            
        } 
    }

}