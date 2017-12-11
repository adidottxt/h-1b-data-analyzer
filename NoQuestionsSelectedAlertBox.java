import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class NoQuestionsSelectedAlertBox {
    
    public static void display() {

        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        
        window.setTitle("H-1B Data Analyzer | Pick A Question (pls)");
        window.setMinWidth(650);

        Label label = new Label();
        label.setText("You haven't picked a question.");
        Label label2 = new Label();
        label2.setText("Please pick a question.\n\n");
 
        Button closeButton = new Button("Close");
        
        closeButton.setOnAction (e -> {
            window.close(); 
        });

        VBox layout = new VBox(25);
        layout.setPadding(new Insets(100, 0, 100, 0));
        layout.getChildren().addAll(label, label2, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene alertScene = new Scene(layout);
        
        String css = "style.css";
        alertScene.getStylesheets().add(css);
        
        window.setScene(alertScene);
        window.showAndWait();

    }

}