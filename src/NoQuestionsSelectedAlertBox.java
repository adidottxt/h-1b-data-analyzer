import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * This class implements the alert box that forces users to pick a 
 * question before clicking on the "confirm questions" button to 
 * receive a result.
 * @author adi
 *
 */
public class NoQuestionsSelectedAlertBox {
    
    public static void display() {

        Stage window = new Stage();

        //block events to other windows
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

        //display window and wait for it to be closed before returning
        Scene alertScene = new Scene(layout);
        
        String css = "file:resources/style.css";
        alertScene.getStylesheets().add(css);
        
        window.setScene(alertScene);
        window.showAndWait();

    }

}