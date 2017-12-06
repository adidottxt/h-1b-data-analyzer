
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import java.io.PrintStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class Main extends Application {

    Stage window;
    Scene startScene, yearScene, someQuestionsScene, allQuestionsScene, someAnswersScene, allAnswersScene;
    CaseReader ca;
    CensusDataReader cr;
    ArrayList<Case> cases = new ArrayList<Case>();
    String questionFourState, questionFiveState, questionSixState, questionSevenState;

    public static void main(String[] args) {
        launch(args);
    }
    
    public static class Console extends OutputStream {

        private TextArea output;

        public Console(TextArea ta) {
            this.output = ta;
        }

        @Override
        public void write(int i) throws IOException {
            output.appendText(String.valueOf((char) i));
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;

        TextArea textOutput = TextAreaBuilder.create()
                .maxWidth(900)
                .minHeight(450)
                .wrapText(true)
                .build();
        textOutput.setEditable(false);
        
        TextArea textOutput1 = TextAreaBuilder.create()
                .maxWidth(900)
                .minHeight(450)
                .wrapText(true)
                .build();
        textOutput1.setEditable(false);

        Console console = new Console(textOutput);
        Console console1 = new Console(textOutput1);
        
        PrintStream printOutput = new PrintStream(console, true);
        PrintStream printOutput1 = new PrintStream(console, true);
        
        System.setOut(printOutput);
        System.setErr(printOutput);
        
        System.setOut(printOutput1);
        System.setErr(printOutput1);

        //Labels
        
        Label label0 = new Label ("H1-B Data Analyzer");
        Label label1 = new Label("Hi there.");
        Label label1a = new Label("Click the start button to get started.");
        Label label1b = new Label("(obviously.)");
        label0.setId("label0");
        label1b.setId("label1b");
        Label label2 = new Label("Pick the year you want to analyze.    ");
        label2.setId("labelMedium");
        Label label3 = new Label("Pick your questions.      ");
        label3.setId("labelMedium");
        Label label4 = new Label("Here are your answers...                    ");
        label4.setId("labelMedium");
        Label label8 = new Label("Here are your answers...                    ");
        label8.setId("labelMedium");
        Label label5 = new Label("Pick your questions.      ");
        label5.setId("labelMedium");
        
        Label label5a = new Label("We've put together a few to make your life easier.");
        label5a.setId("labelSmall");
        Label label5b = new Label("Some may require you to select a city/state.");
        label5b.setId("labelSmall");
        
        Label label6 = new Label("Some years do not feature any attorney-related data.");
        label6.setId("labelSmall");
        Label label7 = new Label("Your selected year, unfortunately, is one of them.");
        label7.setId("labelSmall");
        Label label7a = new Label("Ergo, we've removed all attorney-specific questions.");
        label7a.setId("labelSmall");

        //Question CheckBoxes
        CheckBox question1 = new CheckBox("Top 10 Cities (by # of applications)");
        CheckBox question1Copy = new CheckBox("Top 10 Cities (by # of applications)");
        
        CheckBox question2 = new CheckBox("Top 10 States (by # of applications)");
        CheckBox question2Copy = new CheckBox("Top 10 States (by # of applications)");
        
        CheckBox question3 = new CheckBox("Top 10 Cities With Highest Wage Difference");
        CheckBox question3Copy = new CheckBox("Top 10 Cities With Highest Wage Difference");
        
        CheckBox question4 = new CheckBox("Household Income vs H1-B Income (by state)");
        CheckBox question4Copy = new CheckBox("Household Income vs H1-B Income (by state)");

        question4.selectedProperty().addListener((v, oldValue, newValue) -> {
            if (newValue == true) {
                questionFourState = StateAlertBox.display();
            }
        });
        question4Copy.selectedProperty().addListener((v, oldValue, newValue) -> {
            if (newValue == true) {
                questionFourState = StateAlertBox.display();
            }
        });

        CheckBox question5 = new CheckBox("State Population % vs H1-B Population %");
        CheckBox question5Copy = new CheckBox("State Population % vs H1-B Population %");
        
        question5.selectedProperty().addListener((v, oldValue, newValue) -> {
            if (newValue == true) {
                questionFiveState = StateAlertBox.display();
            }
        });
        question5Copy.selectedProperty().addListener((v, oldValue, newValue) -> {
            if (newValue == true) {
                questionFiveState = StateAlertBox.display();
            }
        });
        
        CheckBox question6 = new CheckBox("Top 10 H-1B Jobs (by city)");
        CheckBox question6Copy = new CheckBox("Top 10 H-1B Jobs (by city)");
        
        question6.selectedProperty().addListener((v, oldValue, newValue) -> {
            if (newValue == true) {    
                questionSixState = CityAlertBox.display();
            }
        });
        question6Copy.selectedProperty().addListener((v, oldValue, newValue) -> {
            if (newValue == true) {    
                questionSixState = CityAlertBox.display();
            }
        });
        
        CheckBox question7 = new CheckBox("Average Wage Difference (by job)");
        CheckBox question7Copy = new CheckBox("Average Wage Difference (by job)");
        
        question7.selectedProperty().addListener((v, oldValue, newValue) -> {
            if (newValue == true) {    
                questionSevenState = JobAlertBox.display();
            }
        });
        question7Copy.selectedProperty().addListener((v, oldValue, newValue) -> {
            if (newValue == true) {  
                questionSevenState = JobAlertBox.display();
            }
        });
        
        CheckBox question8 = new CheckBox("Top 10 H-1B Jobs For Given Year");
        CheckBox question8Copy = new CheckBox("Top 10 H-1B Jobs For Given Year");
        
        CheckBox question9 = new CheckBox("Top 10 C-Suite Workplace Cities");
        CheckBox question9Copy = new CheckBox("Top 10 C-Suite Workplace Cities");
        
        CheckBox question10 = new CheckBox("Average C-Suite Pay For Given Year");
        CheckBox question10Copy = new CheckBox("Average C-Suite Pay For Given Year");
        
        CheckBox question11 = new CheckBox("Top 10 Attorneys (by # of applications)");
        CheckBox question12 = new CheckBox("Top 10 Attorney Cities (by # of applications)");
        CheckBox question13 = new CheckBox("Top 10 Attorney States (by # of applications)");
        
        CheckBox question14 = new CheckBox("Top 10 Universities (by # of applications)");
        CheckBox question14Copy = new CheckBox("Top 10 Universities (by # of applications)");
        
        CheckBox question15 = new CheckBox("Top 10 University H-1B Jobs");
        CheckBox question15Copy = new CheckBox("Top 10 University H-1B Jobs");


        //Button 0
        Button button0 = new Button("Exit");
        button0.setOnAction(e -> window.close());
        
        Button button0a = new Button("Exit");
        button0a.setOnAction(e -> window.close());
        
        //Button 1
        Button button1 = new Button("Start --->");
        button1.setOnAction(e -> {
            window.setScene(yearScene);
        });

        //Button 2
        Button button2 = new Button("<--- Go back");
        button2.setOnAction(e -> window.setScene(startScene));
        
        //Button 3
        Button button3 = new Button("Confirm Year --->");        
        
        //Button 4
        Button button4 = new Button("<--- Go back");
        button4.setOnAction(e -> window.setScene(yearScene));
        
        //Button 4 Copy
        Button button4Copy = new Button("<--- Go back");
        button4Copy.setOnAction(e -> window.setScene(yearScene));
        
        //Button 5
        Button button5 = new Button("Confirm Questions --->");
        button5.setOnAction(e -> {
            try {
                
                if (!question1Copy.isSelected() && !question2Copy.isSelected() && !question3Copy.isSelected() && 
                        !question4Copy.isSelected() && !question5Copy.isSelected() && !question6Copy.isSelected() &&
                        !question7Copy.isSelected() && !question8Copy.isSelected() && !question9Copy.isSelected() && 
                        !question10Copy.isSelected() && !question14Copy.isSelected() && !question15Copy.isSelected()) {
                        NoQuestionsSelectedAlertBox.display();
                        window.setScene(someQuestionsScene);
                        
                } else {
                    String answer = handleSomeOptions(question1Copy, question2Copy, question3Copy, question4Copy, question5Copy, question6Copy, 
                            question7Copy, question8Copy, question9Copy, question10Copy, question14Copy, question15Copy);
                    textOutput.clear();
                    for (char c : answer.toCharArray()) {
                        console.write(c);
                    }
                    window.setScene(someAnswersScene);
                    textOutput.setScrollTop(0.0);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        
        //Button 6
        Button button6 = new Button("Confirm Questions --->");
        button6.setOnAction(e -> {
            try {
                if (!question1.isSelected() && !question2.isSelected() && !question3.isSelected() && 
                        !question4.isSelected() && !question5.isSelected() && !question6.isSelected() &&
                        !question7.isSelected() && !question8.isSelected() && !question9.isSelected() && 
                        !question10.isSelected() && !question11.isSelected() && !question12.isSelected() && 
                        !question13.isSelected() && !question14.isSelected() && !question15.isSelected()) {
                       
                    NoQuestionsSelectedAlertBox.display();
                    window.setScene(allQuestionsScene);
                        
                } else {
                
                    String answer = handleOptions(question1, question2, question3, question4, question5, question6, 
                            question7, question8, question9, question10, question11, question12,
                            question13, question14, question15);
                    textOutput1.clear();
                    for (char c : answer.toCharArray()) {
                        console1.write(c);
                    }
                    window.setScene(allAnswersScene);
                    textOutput1.setScrollTop(0.0);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        
        //Button 7
        Button button7 = new Button("<--- Go back");
        button7.setOnAction(e -> {
            question1Copy.setSelected(false);
            question2Copy.setSelected(false);
            question3Copy.setSelected(false);
            question4Copy.setSelected(false);
            question5Copy.setSelected(false);
            question6Copy.setSelected(false);
            question7Copy.setSelected(false);
            question8Copy.setSelected(false);
            question9Copy.setSelected(false);
            question10Copy.setSelected(false);
            question14Copy.setSelected(false);
            question15Copy.setSelected(false);
            window.setScene(someQuestionsScene);
        });
        
        
      //Button 8
        Button button8 = new Button("<--- Go back");
        button8.setOnAction(e -> {
            question1.setSelected(false);
            question2.setSelected(false);
            question3.setSelected(false);
            question4.setSelected(false);
            question5.setSelected(false);
            question6.setSelected(false);
            question7.setSelected(false);
            question8.setSelected(false);
            question9.setSelected(false);
            question10.setSelected(false);
            question11.setSelected(false);
            question12.setSelected(false);
            question13.setSelected(false);
            question14.setSelected(false);
            question15.setSelected(false);
            window.setScene(allQuestionsScene);  
        });
        
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
            
            if (allYears.getValue().contains("2008") || allYears.getValue().contains("2009") 
                    || allYears.getValue().contains("2010") || allYears.getValue().contains("2011") 
                    || allYears.getValue().contains("2012") || allYears.getValue().contains("2013") 
                    || allYears.getValue().contains("2014")) {
                window.setScene(someQuestionsScene);
            }
            
            else {
                window.setScene(allQuestionsScene);
            }
            
        });


        //Grid 1        
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(350, 100, 10, 150));
        grid1.setVgap(10);
        grid1.setHgap(1);
        
        GridPane.setConstraints(label0, 0, 0);
        GridPane.setConstraints(label1, 0, 4);
        GridPane.setConstraints(label1a, 0, 5);
        GridPane.setConstraints(label1b, 0, 6);
        GridPane.setConstraints(button1, 200, 27);
        
        grid1.getChildren().addAll(label0, label1, label1a, label1b, button1);
        startScene = new Scene(grid1, 1100, 850);

        //Grid 2
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(100, 50, 50, 100));
        grid2.setVgap(10);
        grid2.setHgap(1);
        
        GridPane.setConstraints(label2, 0, 0);
        GridPane.setConstraints(allYears, 0, 4);
        GridPane.setConstraints(button3, 1, 56);
        GridPane.setConstraints(button2, 0, 56);
        
        grid2.getChildren().addAll(label2, allYears, button3, button2);
        yearScene = new Scene(grid2, 1100, 850);
        
        //Grid 3
        GridPane grid3 = new GridPane();
        
        grid3.setPadding(new Insets(75, 50, 50, 100));
        grid3.setVgap(10);
        grid3.setHgap(10);
        
        GridPane.setConstraints(label5, 0, 0);
        GridPane.setConstraints(label5a, 0, 3);
        GridPane.setConstraints(label5b, 0, 4);
        GridPane.setConstraints(label6, 0, 5);
        GridPane.setConstraints(label7, 0, 6);
        GridPane.setConstraints(label7a, 0, 7);
        GridPane.setConstraints(question1Copy, 0, 11);
        GridPane.setConstraints(question2Copy, 0, 12);
        GridPane.setConstraints(question3Copy, 0, 13);
        GridPane.setConstraints(question4Copy, 0, 14);
        GridPane.setConstraints(question5Copy, 0, 15);
        GridPane.setConstraints(question6Copy, 0, 16);
        GridPane.setConstraints(question7Copy, 0, 17);
        GridPane.setConstraints(question8Copy, 0, 18);
        GridPane.setConstraints(question9Copy, 0, 19);
        GridPane.setConstraints(question10Copy, 0, 20);
        GridPane.setConstraints(question14Copy, 0, 21);
        GridPane.setConstraints(question15Copy, 0, 22);
        GridPane.setConstraints(button5, 18, 30);
        GridPane.setConstraints(button4Copy, 0, 30);
        
        grid3.getChildren().addAll(label5, label5a, label5b, label6, label7, label7a, question1Copy, question2Copy, question3Copy,
                 question4Copy, question5Copy, question6Copy, question7Copy, question8Copy,
                  question9Copy, question10Copy, question14Copy, question15Copy, button5, button4Copy);
        someQuestionsScene = new Scene(grid3, 1100, 850);
        
        //Grid 4
        GridPane grid4 = new GridPane();
        grid4.setPadding(new Insets(75, 50, 50, 100));
        grid4.setVgap(10);
        grid4.setHgap(10);
        
        GridPane.setConstraints(label3, 0, 0);
        GridPane.setConstraints(label5a, 0, 3);
        GridPane.setConstraints(label5b, 0, 4);
        GridPane.setConstraints(question1, 0, 7);
        GridPane.setConstraints(question2, 0, 8);
        GridPane.setConstraints(question3, 0, 9);
        GridPane.setConstraints(question4, 0, 10);
        GridPane.setConstraints(question5, 0, 11);
        GridPane.setConstraints(question6, 0, 12);
        GridPane.setConstraints(question7, 0, 13);
        GridPane.setConstraints(question8, 0, 14);
        GridPane.setConstraints(question9, 0, 15);
        GridPane.setConstraints(question10, 0, 16);
        GridPane.setConstraints(question11, 0, 17);
        GridPane.setConstraints(question12, 0, 18);
        GridPane.setConstraints(question13, 0, 19);
        GridPane.setConstraints(question14, 0, 20);
        GridPane.setConstraints(question15, 0, 21);
        GridPane.setConstraints(button6, 18, 25);
        GridPane.setConstraints(button4, 0, 25);

        
        grid4.getChildren().addAll(label3, label5a, label5b, question1, question2, question3, question4,
                question5, question6, question7, question8, question9, question10,
                question11, question12, question13, question14, question15, button6, button4);
        allQuestionsScene = new Scene(grid4, 1100, 850);
        
        //Grid 5
        GridPane grid5 = new GridPane();
        grid5.setPadding(new Insets(50, 50, 50, 100));
        grid5.setVgap(10);
        grid5.setHgap(0);
        
        GridPane.setConstraints(label8, 0, 0);
        GridPane.setConstraints(textOutput, 0, 2);
        GridPane.setConstraints(button7, 0, 19);
        GridPane.setConstraints(button0a, 1, 19);
       
        grid5.getChildren().addAll(label8, button7, button0a, textOutput);
        someAnswersScene = new Scene(grid5, 1100, 850);
        
        //Grid 6
        GridPane grid6 = new GridPane();
        grid6.setPadding(new Insets(50, 50, 50, 100));
        grid6.setVgap(10);
        grid6.setHgap(0);
        
        GridPane.setConstraints(label4, 0, 0);
        GridPane.setConstraints(textOutput1, 0, 2);
        GridPane.setConstraints(button8, 0, 19);
        GridPane.setConstraints(button0, 1, 19);

        grid6.getChildren().addAll(label4, textOutput1, button8, button0);
        allAnswersScene = new Scene(grid6, 1100, 850);
        
        //css import
        String css = "style.css";
        
        startScene.getStylesheets().add(css);
        yearScene.getStylesheets().add(css);
        someQuestionsScene.getStylesheets().add(css);
        allQuestionsScene.getStylesheets().add(css);
        someAnswersScene.getStylesheets().add(css);
        allAnswersScene.getStylesheets().add(css);

        //Display scene 1 at first
        window.setScene(startScene);
        window.setTitle("H1-B Data Analyzer");
        window.show();
        
        console.close();
        console1.close();
        printOutput.close();
        printOutput1.close();
        
    }
    
    private String handleOptions(CheckBox question1, CheckBox question2,
            CheckBox question3, CheckBox question4, CheckBox question5, CheckBox question6, 
            CheckBox question7, CheckBox question8, CheckBox question9, CheckBox question10, 
            CheckBox question11, CheckBox question12, CheckBox question13, CheckBox question14, 
            CheckBox question15) throws IOException {
        
        StringBuilder sb = new StringBuilder();
        
        if(question1.isSelected() || question2.isSelected() || question3.isSelected() || 
                question4.isSelected() || question5.isSelected()) {
            
            GeographyAnalysis ga = new GeographyAnalysis(cases);
            
            if(question1.isSelected()) {
                
                sb.append(ga.getTopTenCities());
                sb.append("\n");
                
            } if(question2.isSelected()) {
              
                sb.append(ga.getTopTenStates());
                sb.append("\n");
                
            } if(question3.isSelected()) {
                
                sb.append(ga.getTopTenCitiesForWageDif());
                sb.append("\n");
                
            } if(question4.isSelected()) {
                
                sb.append(ga.getAverageWagevsAverageH1BWage(questionFourState));
                sb.append("\n");
                
            } if(question5.isSelected()) {
                
                sb.append(ga.getH1BPopulationPercentageVsStatePopulation(questionFiveState));
                sb.append("\n");
            } 
        }
        
        if(question6.isSelected() || question7.isSelected() || question8.isSelected() || 
                question9.isSelected() || question10.isSelected()) {
            
            JobAnalysis ja = new JobAnalysis(cases);
            
            if(question6.isSelected()) {
                
                sb.append(ja.getTopTenJobTitles(questionSixState));
                sb.append("\n");
                
            } if(question7.isSelected()) {
                
                sb.append(ja.getAverageWageDifferenceByJob(questionSevenState));
                sb.append("\n");
                
            } if(question8.isSelected()) {
                
                sb.append(ja.getTopTenJobTitles());
                sb.append("\n");
                
            } if(question9.isSelected()) {
                
                sb.append(ja.getTopTenCSuiteCities());
                sb.append("\n");
                
            } if(question10.isSelected()) {
                
                sb.append(ja.getAverageCSuitePay());
                sb.append("\n");
                
            }   
        }
        
        if(question11.isSelected() || question12.isSelected() || question13.isSelected()) {
            
            AttorneyAnalysis aa = new AttorneyAnalysis(cases);
            
            if(question11.isSelected()) {
                
                sb.append(aa.getTopTenBusyAttorneys());
                sb.append("\n");
                
            } if(question12.isSelected()) {
                
                sb.append(aa.getTopTenAttorneyCities());
                sb.append("\n");
                
            } if(question13.isSelected()) {
                
                sb.append(aa.getTopTenAttorneyStates());
                sb.append("\n");
                
            } 
        }
        
        if(question14.isSelected() || question15.isSelected()) {
           
            UniversityAnalysis ua = new UniversityAnalysis(cases);
            
            if(question14.isSelected()) {
                
                sb.append(ua.getTopTenUniversityApplicants());
                sb.append("\n");
                
            } if(question15.isSelected()) {
                
                sb.append(ua.getTopTenUniversityJobs());
                sb.append("\n");
                
            }
        }
        return sb.toString();
    }
    
    private String handleSomeOptions(CheckBox question1, CheckBox question2,
            CheckBox question3, CheckBox question4, CheckBox question5, CheckBox question6, 
            CheckBox question7, CheckBox question8, CheckBox question9, CheckBox question10, 
            CheckBox question14, CheckBox question15) throws IOException {  
        
        StringBuilder sb = new StringBuilder();
            
        if(question1.isSelected() || question2.isSelected() || question3.isSelected() || 
                question4.isSelected() || question5.isSelected()) {
            
            GeographyAnalysis ga = new GeographyAnalysis(cases);
            
            if(question1.isSelected()) {
                
                sb.append(ga.getTopTenCities());
                sb.append("\n");
                
            } if(question2.isSelected()) {
              
                sb.append(ga.getTopTenStates());
                sb.append("\n");
                
            } if(question3.isSelected()) {
                
                sb.append(ga.getTopTenCitiesForWageDif());
                sb.append("\n");
                
            } if(question4.isSelected()) {
                
                sb.append(ga.getAverageWagevsAverageH1BWage(questionFourState));
                sb.append("\n");
                
            } if(question5.isSelected()) {
                
                sb.append(ga.getH1BPopulationPercentageVsStatePopulation(questionFiveState));
                sb.append("\n");
                
            }  
        }
        
        if(question6.isSelected() || question7.isSelected() || question8.isSelected() || 
                question9.isSelected() || question10.isSelected()) {
            
            JobAnalysis ja = new JobAnalysis(cases);
            
            if(question6.isSelected()) {
                
                sb.append(ja.getTopTenJobTitles(questionSixState));
                sb.append("\n");
                
            } if(question7.isSelected()) {
                
                sb.append(ja.getAverageWageDifferenceByJob(questionSevenState));
                sb.append("\n");
                
            } if(question8.isSelected()) {
                
                sb.append(ja.getTopTenJobTitles());
                sb.append("\n");
                
            } if(question9.isSelected()) {
                
                sb.append(ja.getTopTenCSuiteCities());
                sb.append("\n");
                
            } if(question10.isSelected()) {
                
                sb.append(ja.getAverageCSuitePay());
                sb.append("\n");
                
            } 
        }
        
        if(question14.isSelected() || question15.isSelected()) {
            
            UniversityAnalysis ua = new UniversityAnalysis(cases);
            
            if(question14.isSelected()) {
                
                sb.append(ua.getTopTenUniversityApplicants());
                sb.append("\n");
                    
            } if(question15.isSelected()) {
                
                sb.append(ua.getTopTenUniversityJobs());
                sb.append("\n");
                
            }
        }

        return sb.toString();
    }
    
    private void getYearChoice(ChoiceBox<String> allYears) throws IOException {
        
        String year = allYears.getValue();
        
        if (year.contains("2008")) {
            ca = new CaseReader("2008.csv");
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