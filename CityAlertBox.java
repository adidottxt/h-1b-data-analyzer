import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class CityAlertBox {

    static String answer;
    
    public static String display() {

        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        
        window.setTitle("H1B Data Analyzer | Pick Your City");
        window.setMinWidth(500);

        Label label = new Label();
        label.setText("Pick the city that you want to analyze: ");
        
        //State Drop Down
        ChoiceBox<String> allCities = new ChoiceBox<>();
        allCities.getItems().addAll("aliso viejo","alpharetta","ann arbor","ashburn","atlanta",
                "austin","baltimore","bellevue","bloomington","boston","bridgewater","brooklyn",
                "cambridge","cary","chantilly","charlotte","chicago","cincinnati",
                "college station","columbia","columbus","cupertino","dallas","detroit",
                "dublin","duluth","durham","east brunswick","edison","fairfax","farmington hills",
                "fremont","frisco","herndon","houston","indianapolis","irvine","irving","iselin",
                "jacksonville","jersey city","livonia","los angeles","louisville","menlo park",
                "miami","milpitas","minneapolis","moon township","mountain view","naperville",
                "nashville","new york","newark","norcross","north brunswick","omaha","palo alto",
                "philadelphia","phoenix","piscataway","pittsburgh","plainsboro","plano","pleasanton",
                "princeton","raleigh","redmond","redwood city","reston","richardson","richmond",
                "rochester","rockville","rosemont","round rock","san antonio","san diego",
                "san francisco","san jose","san mateo","san ramon","santa clara","schaumburg",
                "seattle","secaucus","somerset","south plainfield","southfield","springfield",
                "st. louis","sterling","sunnyvale","tampa","troy","waltham","washington","westborough",
                "wilmington","woburn");
        
        allCities.setValue("aliso viejo");
        
        Button closeButton = new Button("Confirm");
        
        closeButton.setOnAction (e -> {
            answer = allCities.getValue();
            window.close(); 
        });

        VBox layout = new VBox(25);
        layout.setPadding(new Insets(100, 0, 100, 0));
        layout.getChildren().addAll(label, allCities, closeButton);
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