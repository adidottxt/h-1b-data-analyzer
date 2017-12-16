
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.io.PrintStream;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This method runs the front-end of the H-1B Data Analyzer.
 * @author adi
 *
 */
@SuppressWarnings("deprecation")
public class H1BDataAnalyzer extends Application {

    Stage window;
    Scene startScene, yearScene, someQuestionsScene, allQuestionsScene, someAnswersScene, allAnswersScene;
    CaseReader thisCaseReader;
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

        /**
         * This is where the output of the answers to 
         * a user's questions is written into the text area
         * that displays the answers in the final scenes. 
         * @param ta
         */
        @Override
        public void write(int i) throws IOException {
            output.appendText(String.valueOf((char) i));
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
         
        window = primaryStage;
        
        /**
         * This is where the two textOutputs are set up.
         * This is done primarily for the two sections that 
         * display the final answers.
         */
        TextArea textOutput = TextAreaBuilder.create()
                .minWidth(875)
                .minHeight(450)
                .wrapText(true)
                .build();
        textOutput.setEditable(false);
        
        TextArea textOutput1 = TextAreaBuilder.create()
                .minWidth(875)
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

        /**
         * This is where the images that are used in lieu of labels are loaded.
         */
        Image image0 = new Image("file:resources/label.jpg");
        Image image1 = new Image("file:resources/label1.jpg");
        Image image2 = new Image("file:resources/label2.jpg");
        Image image3 = new Image("file:resources/label3.jpg");
        Image image3a = new Image("file:resources/label3.jpg");
        Image image4 = new Image("file:resources/label4.jpg");
        Image image5 = new Image("file:resources/label5.jpg");
        Image image6 = new Image("file:resources/label6.jpg");
        Image image6a = new Image("file:resources/label6.jpg");

        /**
         * This is where the various checkboxes are set up for each question.
         * There are different checkboxes for the two scenes, given some questions 
         * do not pertain to specific years (the attorney questions do not show up 
         * in the years where there is no attorney data provided, for example.) 
         * Each question also features a tooltip that explains the question. The
         * tooltip can be accessed in the front end by hovering over the question.
         */
        CheckBox question1 = new CheckBox("Top Cities (by # of applications)");
        question1.setTooltip( new Tooltip("A list of the top 10 cities sorted "
                + "by the number of applications \n submitted from each city."));
        
        CheckBox question1Copy = new CheckBox("Top Cities (by # of applications)");
        question1Copy.setTooltip( new Tooltip("A list of the top 10 cities sorted by the number of \n"
                + "applications submitted from each city."));
        
        CheckBox question2 = new CheckBox("Top States (by # of applications)");
        question2.setTooltip( new Tooltip("A list of the top 10 states sorted by the number of \n"
                + "applications submitted from each state."));
        
        CheckBox question2Copy = new CheckBox("Top States (by # of applications)");
        question2Copy.setTooltip( new Tooltip("A list of the top 10 states sorted by the number of \n"
                + "applications submitted from each state."));
        
        CheckBox question3 = new CheckBox("Top Wage Difference Cities");
        question3.setTooltip( new Tooltip("A list of the top 10 cities where the difference "
                + "between the prevailing wage and the wage offered \n to H-1B candidates "
                + "are the largest. This potentially gives insight into how much more \n certain "
                + "companies / businesses need to pay to get top talent based on where they're based."));

        CheckBox question3Copy = new CheckBox("Top Wage Difference Cities");
        question3Copy.setTooltip( new Tooltip("A list of the top 10 cities where the difference "
                + "between the prevailing wage and the wage offered \n to H-1B candidates "
                + "are the largest. This potentially gives insight into how much more \n certain "
                + "companies / businesses need to pay to get top talent based on where they're based."));
        
        CheckBox question4 = new CheckBox("Household vs H-1B Income (by state)");
        question4.setTooltip( new Tooltip("Compare the average household income of a particular state, \n"
                + "and the average income for an H-1B candidate from the same state."));

        CheckBox question4Copy = new CheckBox("Household vs H-1B Income (by state)");
        question4Copy.setTooltip( new Tooltip("Compare the average household income of a particular state, \n"
                + "and the average income for an H-1B candidate from the same state."));
        
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

        CheckBox question5 = new CheckBox("State vs H-1B Population %");
        question5.setTooltip( new Tooltip("Compare the population % of a given state (compared to the "
                + "national population), to the H-1B \n application percentage by location. "
                + "This potentially gives insight into how much location factors \n in where "
                + "a majority of H-1B jobs are."));
        
        CheckBox question5Copy = new CheckBox("State vs H-1B Population %");
        question5Copy.setTooltip( new Tooltip("Compare the population % of a given state (compared to the "
                + "national population), to the H-1B \n application percentage by location. "
                + "This potentially gives insight into how much location factors \n in where "
                + "a majority of H-1B jobs are."));
        
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
        
        CheckBox question6 = new CheckBox("Top H-1B Jobs (by city)");
        question6.setTooltip(new Tooltip("Pick a city, and get the top 10 H-1B jobs in that city. This "
                + "potentially \n gives insight into what cities are hubs for what industries."));
        CheckBox question6Copy = new CheckBox("Top H-1B Jobs (by city)");
        question6Copy.setTooltip(new Tooltip("Pick a city, and get the top 10 H-1B jobs in that city. This "
                + "potentially \n gives insight into what cities are hubs for what industries."));
        
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
        
        CheckBox question7 = new CheckBox("Avg. Wage Difference (by job)");
        question7.setTooltip(new Tooltip("Pick a job, and get the average difference between the wage "
                + "offered to H-1B candidates \n for that job, and the submitted \"prevailing wage\" for "
                + "that job."));
        
        CheckBox question7Copy = new CheckBox("Avg. Wage Difference (by job)");
        question7Copy.setTooltip(new Tooltip("Pick a job, and get the average difference between the wage "
                + "offered to H-1B candidates \n for that job, and the submitted \"prevailing wage\" for "
                + "that job."));
        
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
        
        CheckBox question8 = new CheckBox("Top H-1B Jobs");
        question8.setTooltip(new Tooltip("The top 10 jobs for your year's cohort of H-1B candidates."));
        
        CheckBox question8Copy = new CheckBox("Top H-1B Jobs");
        question8Copy.setTooltip(new Tooltip("The top 10 jobs for your year's cohort of H-1B candidates."));
        
        CheckBox question9 = new CheckBox("Top C-Suite Cities");
        question9.setTooltip(new Tooltip("The top 10 cities for all \"C-Suite\" jobs – CFO, CEO, CMO, COO, "
                + "CTO, you name it."));
        
        CheckBox question9Copy = new CheckBox("Top C-Suite Cities");
        question9Copy.setTooltip(new Tooltip("The top 10 cities for all \"C-Suite\" jobs – CFO, CEO, CMO, COO, "
                + "CTO, you name it."));
        
        CheckBox question10 = new CheckBox("Average C-Suite Pay");
        question10.setTooltip(new Tooltip("The average wage for all \"C-Suite\" jobs."));
        
        CheckBox question10Copy = new CheckBox("Average C-Suite Pay");
        question10Copy.setTooltip(new Tooltip("The average wage for all \"C-Suite\" jobs."));
        
        CheckBox question11 = new CheckBox("Top Attorneys (by # of applications)");
        question11.setTooltip(new Tooltip("The top 10 attorneys in terms of the number of H-1B applications "
                + "they submitted in your given year."));
        
        CheckBox question12 = new CheckBox("Top Attorney Cities (by # of applications)");
        question12.setTooltip(new Tooltip("The top 10 cities where attorneys are based in terms of the "
                + "number of H-1B applications submitted in your given year."));

        CheckBox question13 = new CheckBox("Top Attorney States (by # of applications)");
        question13.setTooltip(new Tooltip("The top 10 states where attorneys are based in terms of the "
                + "number of H-1B applications submitted in your given year."));
        
        CheckBox question14 = new CheckBox("Top Universities (by # of applications)");
        question14.setTooltip(new Tooltip("The top 10 universities sorted by the number of submitted \n"
                + "H-1B applications for jobs offered at said universities."));
        
        CheckBox question14Copy = new CheckBox("Top Universities (by # of applications)");
        question14Copy.setTooltip(new Tooltip("The top 10 universities sorted by the number of submitted \n"
                + "H-1B applications for jobs offered at said universities."));
        
        CheckBox question15 = new CheckBox("Top University H-1B Jobs");
        question15.setTooltip(new Tooltip("The top 10 jobs for H-1B applications submitted by universities."));
        
        CheckBox question15Copy = new CheckBox("Top University H-1B Jobs");
        question15Copy.setTooltip(new Tooltip("The top 10 jobs for H-1B applications submitted by universities."));

        CheckBox question16 = new CheckBox("Top Companies (by # of applications)");
        question16.setTooltip(new Tooltip("The top 10 companies that submitted applications for your "
                + "year's cohort of H-1B candidates."));
        
        CheckBox question16Copy = new CheckBox("Top Companies (by # of applications)");
        question16Copy.setTooltip(new Tooltip("The top 10 companies that submitted applications for "
                + "your year's cohort of H-1B candidates."));
        
        CheckBox question17 = new CheckBox("Run H-1B Allocation Simulation");
        question17.setTooltip(new Tooltip("This simulation gives you the answer to various questions \n"
                + "pertinent to what would happen if the yearly quota of \n85,000 visas was not "
                + "allocated via lottery, but instead to the 85,000 \nhighest bidders. This analyzes "
                + "information about the 85,000 \nthat get their visas, and information about the "
                + "remaining who do not.")); 
        
        CheckBox question17Copy = new CheckBox("Run H-1B Allocation Simulation");
        question17Copy.setTooltip(new Tooltip("This simulation gives you the answer to various questions \n"
                + "pertinent to what would happen if the yearly quota of \n85,000 visas was not "
                + "allocated via lottery, but instead to the 85,000 \nhighest bidders. This analyzes "
                + "information about the 85,000 \nthat get their visas, and information about the "
                + "remaining who do not.")); 
        
        //Button 0
        Button button0 = new Button("Exit");
        button0.setOnAction(e -> window.close());
        
        Button button0a = new Button("Exit");
        button0a.setOnAction(e -> window.close());
        
        //Button 1
        Button button1 = new Button("Start");
        button1.setOnAction(e -> {
            window.setScene(yearScene);
        });

        //Button 2
        Button button2 = new Button("Go Back");
        button2.setOnAction(e -> window.setScene(startScene));
        
        //Button 3
        Button button3 = new Button("Confirm Year");        
        
        /**
         * This is where the second button to go back to  
         * the yearScene is set up.
         */
        Button button4 = new Button("Go Back");
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
        question16Copy.setSelected(false);
        question17Copy.setSelected(false);
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
        question16.setSelected(false);
        question17.setSelected(false);
        button4.setOnAction(e -> window.setScene(yearScene));
        
        /**
         * This is where the second button to go back to  
         * the yearScene is set up.
         */
        Button button4Copy = new Button("Go back");
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
        question16Copy.setSelected(false);
        question17Copy.setSelected(false);
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
        question16.setSelected(false);
        question17.setSelected(false);
        button4Copy.setOnAction(e -> window.setScene(yearScene));
        
        /**
         * This is where the button to confirm questions 
         * in the someQuestionsScene is set up.
         */
        Button button5 = new Button("Confirm Questions");
        button5.setOnAction(e -> {
            try {
                
                if (!question1Copy.isSelected() && !question2Copy.isSelected() && 
                        !question3Copy.isSelected() && !question4Copy.isSelected() && 
                        !question5Copy.isSelected() && !question6Copy.isSelected() &&
                        !question7Copy.isSelected() && !question8Copy.isSelected() && 
                        !question9Copy.isSelected() && !question10Copy.isSelected() && 
                        !question14Copy.isSelected() && !question15Copy.isSelected() &&
                        !question16Copy.isSelected() && !question17Copy.isSelected()) {
                        NoQuestionsSelectedAlertBox.display();
                        window.setScene(someQuestionsScene);
                        
                } else {
                    String answer = handleSomeOptions(question1Copy, question2Copy, question3Copy, 
                            question4Copy, question5Copy, question6Copy, question7Copy, 
                            question8Copy, question9Copy, question10Copy, question14Copy, 
                            question15Copy, question16Copy, question17Copy);
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
        
        /**
         * This is where the button to confirm questions 
         * in the allQuestionsScene is set up.
         */
        Button button6 = new Button("Confirm Questions");
        button6.setOnAction(e -> {
            try {
                if (!question1.isSelected() && !question2.isSelected() && !question3.isSelected() && 
                        !question4.isSelected() && !question5.isSelected() && !question6.isSelected() &&
                        !question7.isSelected() && !question8.isSelected() && !question9.isSelected() && 
                        !question10.isSelected() && !question11.isSelected() && !question12.isSelected() && 
                        !question13.isSelected() && !question14.isSelected() && !question15.isSelected() &&
                        !question16.isSelected() && !question17.isSelected()) {
                       
                    NoQuestionsSelectedAlertBox.display();
                    window.setScene(allQuestionsScene);
                        
                } else {
                    String answer = handleOptions(question1, question2, question3, question4, 
                            question5, question6, question7, question8, question9, question10, 
                            question11, question12, question13, question14, question15, question16, 
                            question17);
                    
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
        
        /**
         * This is where the button to go back from 
         * the someAnswersScene is set up.
         */
        Button button7 = new Button("Go Back");
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
            question16Copy.setSelected(false);
            question17Copy.setSelected(false);
            window.setScene(someQuestionsScene);
        });
        
        
        /**
         * This is where the button to go back from 
         * the allAnswersScene is set up.
         */
        Button button8 = new Button("Go Back");
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
            question16.setSelected(false);
            question17.setSelected(false);
            window.setScene(allQuestionsScene);  
        });
        
        /**
         * This is where the ChoiceBox for users to pick
         * the year they want to analyze is set up.
         */
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

        /**
         * This is where the first scene is set up.
         */   
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(250, 100, 10, 150));
        grid1.setVgap(10);
        grid1.setHgap(1);
        
        grid1.add(new ImageView(image0), 0, 0);
        grid1.add(new ImageView(image1), 0, 4);
        GridPane.setConstraints(button1, 35, 15);
        grid1.getChildren().addAll(button1);
        startScene = new Scene(grid1, 1100, 850);

        /**
         * This is where the scene where users 
         * pick the year they want to 
         * analyze years is set up.
         */
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(100, 50, 50, 100));
        grid2.setVgap(10);
        grid2.setHgap(1);
        
        grid2.add(new ImageView(image2), 0, 0); 
        GridPane.setConstraints(allYears, 0, 2);
        GridPane.setConstraints(button3, 10, 41);
        GridPane.setConstraints(button2, 0, 41);
        
        grid2.getChildren().addAll(allYears, button3, button2);
        yearScene = new Scene(grid2, 1100, 850);
        
        /**
         * This is where the questions scene for the years 
         * without attorney data is set up.
         */
        GridPane grid3 = new GridPane();
        
        grid3.setPadding(new Insets(50, 100, 50, 100));
        grid3.setVgap(10);
        grid3.setHgap(1);
        
        grid3.add(new ImageView(image3), 0, 0); 
        grid3.add(new ImageView(image4), 0, 1); 
        GridPane.setConstraints(question1Copy, 0, 4);
        GridPane.setConstraints(question2Copy, 1, 4);
        GridPane.setConstraints(question4Copy, 0, 5);
        GridPane.setConstraints(question3Copy, 1, 5);
        GridPane.setConstraints(question5Copy, 0, 6);
        GridPane.setConstraints(question6Copy, 1, 6);
        GridPane.setConstraints(question7Copy, 0, 7);
        GridPane.setConstraints(question8Copy, 1, 7);
        GridPane.setConstraints(question9Copy, 0, 8);
        GridPane.setConstraints(question10Copy, 1, 8);
        GridPane.setConstraints(question14Copy, 0, 9);
        GridPane.setConstraints(question15Copy, 1, 9);
        GridPane.setConstraints(question16Copy, 0, 10);
        GridPane.setConstraints(question17Copy, 1, 10);
        GridPane.setConstraints(button5, 25, 26);
        GridPane.setConstraints(button4Copy, 0, 26);
        
        grid3.getChildren().addAll(question1Copy, question2Copy, question3Copy,
                 question4Copy, question5Copy, question6Copy, question7Copy, question8Copy,
                  question9Copy, question10Copy, question14Copy, question15Copy, question16Copy, 
                  question17Copy, button5, button4Copy);
        someQuestionsScene = new Scene(grid3, 1100, 850);
        
        /**
         * This is where the questions scene for the years 
         * with attorney data is set up.
         */
        GridPane grid4 = new GridPane();
        grid4.setPadding(new Insets(50, 100, 50, 100));
        grid4.setVgap(10);
        grid4.setHgap(1);
        
        grid4.add(new ImageView(image3a), 0, 0); 
        grid4.add(new ImageView(image5), 0, 1); 
        GridPane.setConstraints(question1, 0, 7);
        GridPane.setConstraints(question2, 1, 7);
        GridPane.setConstraints(question4, 0, 8);
        GridPane.setConstraints(question3, 1, 8);
        GridPane.setConstraints(question5, 0, 9);
        GridPane.setConstraints(question6, 1, 9);
        GridPane.setConstraints(question7, 0, 10);
        GridPane.setConstraints(question8, 1, 10);
        GridPane.setConstraints(question14, 0, 11);
        GridPane.setConstraints(question10, 1, 11);
        GridPane.setConstraints(question11, 0, 12);
        GridPane.setConstraints(question9, 1, 12);
        GridPane.setConstraints(question13, 0, 13);
        GridPane.setConstraints(question15, 1, 13);
        GridPane.setConstraints(question12, 0, 14);
        GridPane.setConstraints(question16, 0, 15);
        GridPane.setConstraints(question17, 1, 14);
        GridPane.setConstraints(button6, 25, 31);
        GridPane.setConstraints(button4, 0, 31);
        
        grid4.getChildren().addAll(question1, question2, question3, question4,
                question5, question6, question7, question8, question9, question10,
                question11, question12, question13, question14, question15, question16, question17, 
                button6, button4);
        allQuestionsScene = new Scene(grid4, 1100, 850);
        
        /**
         * This is where the answers scene for the years 
         * without attorney data is set up.
         */
        GridPane grid5 = new GridPane();
        grid5.setPadding(new Insets(50, 50, 50, 100));
        grid5.setVgap(10);
        grid5.setHgap(100);
        
        grid5.add(new ImageView(image6a), 0, 0);
        GridPane.setConstraints(textOutput, 0, 1);
        GridPane.setConstraints(button7, 0, 10);
        GridPane.setConstraints(button0a, 1, 10);
        GridPane.setHalignment(button0a, HPos.RIGHT);
        grid5.add(textOutput, 0, 5, 2, 1);
        grid5.getChildren().addAll(button7, button0a);
        someAnswersScene = new Scene(grid5, 1100, 850);
        
        /**
         * This is where the answers scene for the years with
         * attorney data is set up.
         */
        GridPane grid6 = new GridPane();
        grid6.setPadding(new Insets(50, 50, 50, 100));
        grid6.setVgap(10);
        grid6.setHgap(0);
        
        grid6.add(new ImageView(image6), 0, 0); 
        GridPane.setConstraints(button8, 0, 10);
        GridPane.setConstraints(button0, 1, 10);
        grid6.add(textOutput1, 0, 5, 2, 1);
        GridPane.setHalignment(button0, HPos.RIGHT);

        grid6.getChildren().addAll(button8, button0);
        allAnswersScene = new Scene(grid6, 1100, 850);
        
        /**
         * This is where the css file is imported 
         * and applied to all scenes.
         */
        String css = "file:resources/style.css";
        
        startScene.getStylesheets().add(css);
        yearScene.getStylesheets().add(css);
        someQuestionsScene.getStylesheets().add(css);
        allQuestionsScene.getStylesheets().add(css);
        someAnswersScene.getStylesheets().add(css);
        allAnswersScene.getStylesheets().add(css);

        //Display the first scene to begin with
        window.setScene(startScene);
        window.setTitle("H-1B Data Analyzer");
        window.show();
        
        console.close();
        console1.close();
        printOutput.close();
        printOutput1.close();
        
    }
    
    /**
     * This method handles the options for years where attorney data is 
     * provided. It checks for which questions are selected, and runs 
     * the corresponding methods.
     * @return answers for the selected questions
     * @throws IOException
     */
    private String handleOptions(CheckBox question1, CheckBox question2,
            CheckBox question3, CheckBox question4, CheckBox question5, CheckBox question6, 
            CheckBox question7, CheckBox question8, CheckBox question9, CheckBox question10, 
            CheckBox question11, CheckBox question12, CheckBox question13, CheckBox question14, 
            CheckBox question15, CheckBox question16, CheckBox question17) throws IOException {
        
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
        
        if(question16.isSelected()) {
            CompanyAnalysis cAn = new CompanyAnalysis(cases);
            sb.append(cAn.getTopTenCompanies());
            sb.append("\n");
        }
        
        if (question17.isSelected()) {
            WageBasedSimulation wa = new WageBasedSimulation(cases);
            sb.append(wa.runSimulation());
            sb.append(wa.runLosingSimulation());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    /**
     * This method handles the options for years where attorney data is 
     * not provided. It checks for which questions are selected, and runs 
     * the corresponding methods.
     * @return answers for the selected questions
     * @throws IOException
     */
    private String handleSomeOptions(CheckBox q1, CheckBox q2,
            CheckBox q3, CheckBox q4, CheckBox q5, CheckBox q6, 
            CheckBox q7, CheckBox q8, CheckBox q9, CheckBox q10, 
            CheckBox q14, CheckBox q15, CheckBox q16, CheckBox q17) throws IOException {  
                
        StringBuilder sb = new StringBuilder();

        if(q1.isSelected() || q2.isSelected() || q3.isSelected() || 
                q4.isSelected() || q5.isSelected()) {
            GeographyAnalysis ga = new GeographyAnalysis(cases); 
            if(q1.isSelected()) {
                sb.append(ga.getTopTenCities());
                sb.append("\n");
            } if(q2.isSelected()) {
                sb.append(ga.getTopTenStates());
                sb.append("\n");
            } if(q3.isSelected()) {
                sb.append(ga.getTopTenCitiesForWageDif());
                sb.append("\n");
            } if(q4.isSelected()) {
                sb.append(ga.getAverageWagevsAverageH1BWage(questionFourState));
                sb.append("\n");
            } if(q5.isSelected()) {
                sb.append(ga.getH1BPopulationPercentageVsStatePopulation(questionFiveState));
                sb.append("\n");
            } 
        }
            
        if(q6.isSelected() || q7.isSelected() || q8.isSelected() || 
                q9.isSelected() || q10.isSelected()) {
            JobAnalysis ja = new JobAnalysis(cases);
            if(q6.isSelected()) {
                sb.append(ja.getTopTenJobTitles(questionSixState));
                sb.append("\n");
            } if(q7.isSelected()) {
                sb.append(ja.getAverageWageDifferenceByJob(questionSevenState));
                sb.append("\n");
            } if(q8.isSelected()) {
                sb.append(ja.getTopTenJobTitles());
                sb.append("\n");
            } if(q9.isSelected()) {
                sb.append(ja.getTopTenCSuiteCities());
                sb.append("\n");
            } if(q10.isSelected()) {
                sb.append(ja.getAverageCSuitePay());
                sb.append("\n");
            } 
        }
            
        if(q14.isSelected() || q15.isSelected()) {
            UniversityAnalysis ua = new UniversityAnalysis(cases);
            if(q14.isSelected()) {
                sb.append(ua.getTopTenUniversityApplicants());
                sb.append("\n");    
            } if(q15.isSelected()) {
                sb.append(ua.getTopTenUniversityJobs());
                sb.append("\n");
            } 
        }
        
        if(q16.isSelected()) {
            CompanyAnalysis cAn = new CompanyAnalysis(cases);
            sb.append(cAn.getTopTenCompanies());
            sb.append("\n");
        }
        
        if(q17.isSelected()) {
            WageBasedSimulation wa = new WageBasedSimulation(cases);
            sb.append(wa.runSimulation());
            sb.append(wa.runLosingSimulation());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    /**
     * This method creates cases based on which year is picked to be used
     * in the various analysis methods. 
     * @param allYears: the year selected.
     * @throws IOException
     */
    private void getYearChoice(ChoiceBox<String> allYears) throws IOException {
        
        String year = allYears.getValue();
        
        if (year.contains("2008")) {
            thisCaseReader = new CaseReader("test-data/2008.csv");
            cases = thisCaseReader.getCases();
            
        } else if (year.contains("2009")) {
            thisCaseReader = new CaseReader("test-data/2009.csv");
            cases = thisCaseReader.getCases();
            
        } else if (year.contains("2010")) {
            thisCaseReader = new CaseReader("test-data/2010.csv");
            cases = thisCaseReader.getCases();
            
        } else if (year.contains("2011")) {
            thisCaseReader = new CaseReader("test-data/2011.csv");
            cases = thisCaseReader.getCases();
            
        } else if (year.contains("2012")) {
            thisCaseReader = new CaseReader("test-data/2012.csv");
            cases = thisCaseReader.getCases();
            
        } else if (year.contains("2013")) {
            thisCaseReader = new CaseReader("test-data/2013.csv");
            cases = thisCaseReader.getCases();
            
        } else if (year.contains("2014")) {
            thisCaseReader = new CaseReader("test-data/2014.csv");
            cases = thisCaseReader.getCases();
            
        } else if (year.contains("2015")) {
            thisCaseReader = new CaseReader("test-data/2015.csv");
            cases = thisCaseReader.getCases();
            
        } else if (year.contains("2016")) {
            thisCaseReader = new CaseReader("test-data/2016.csv");
            cases = thisCaseReader.getCases();
            
        } else if (year.contains("2017")) {
            thisCaseReader = new CaseReader("test-data/2017.csv");
            cases = thisCaseReader.getCases();   
        } 
    }
}