import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * This class implements the alert box that forces users to pick a state
 * for any questions that require the user to input a state. By asking the
 * user to pick from a list of states, we're also (for the most part) 
 * reducing the chance of the user entering an incorrect input.
 * @author adi
 *
 */
public class StateAlertBox {

    static String answer;
    
    public static String display() {

        Stage window = new Stage();

        //block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        
        window.setTitle("H-1B Data Analyzer | Pick Your State");
        window.setMinWidth(500);

        Label label = new Label();
        label.setText("Pick the state that you want to analyze: ");
        
        //state drop down box
        ChoiceBox<String> allStates = new ChoiceBox<>();
        allStates.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL",
                 "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA",
                  "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
                   "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
                    "VA", "WA", "WV", "WI", "WY");
        allStates.setValue("AL");
        
        Button closeButton = new Button("Confirm");
        
        closeButton.setOnAction (e -> {
            answer = allStates.getValue();
            window.close(); 
        });

        VBox layout = new VBox(25);
        layout.setPadding(new Insets(100, 0, 100, 0));
        layout.getChildren().addAll(label, allStates, closeButton);
        layout.setAlignment(Pos.CENTER);

        //display window and wait for it to be closed before returning
        Scene alertScene = new Scene(layout);
        
        String css = "file:resources/style.css";
        alertScene.getStylesheets().add(css);
        
        window.setScene(alertScene);
        window.showAndWait();
        
        return answer;
    }

}