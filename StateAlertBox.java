import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class StateAlertBox {

    static String answer;
    
    public static String display() {

        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        
        window.setTitle("H1B Data Analyzer | Pick Your State");
        window.setMinWidth(500);

        Label label = new Label();
        label.setText("Pick the state that you want to analyze: ");
        
        //State Drop Down
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

        //Display window and wait for it to be closed before returning
        Scene alertScene = new Scene(layout);
        
        String css = "style.css";
        alertScene.getStylesheets().add(css);
        
        window.setScene(alertScene);
        window.showAndWait();
        
        return answer;
    }

}