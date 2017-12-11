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
        
        window.setTitle("H-1B Data Analyzer | Pick Your City");
        window.setMinWidth(500);

        Label label = new Label();
        label.setText("Pick the city that you want to analyze: ");
        
        //State Drop Down
        ChoiceBox<String> allCities = new ChoiceBox<>();
        allCities.getItems().addAll("ALISO VIEJO, CA","ALPHARETTA, GA","ANN ARBOR, MI",
                "ARLINGTON HEIGHTS, IL","ASHBURN, VA","ATLANTA, GA","AUBURN HILLS, MI",
                "AURORA, IL","AUSTIN, TX","BALTIMORE, MD","BELLEVUE, WA","BENTONVILLE, AR",
                "BETHESDA, MD","BOSTON, MA","BRIDGEWATER, NJ","BRONX, NY","BROOKLYN, NY",
                "BURLINGTON, MA","CAMBRIDGE, MA","CARROLLTON, TX","CARY, NC","CHANDLER, AZ",
                "CHANTILLY, VA","CHARLOTTE, NC","CHESTERFIELD, MO","CHICAGO, IL","CINCINNATI, OH",
                "CLEVELAND, OH","COLLEGE STATION, TX","COLUMBIA, MD","COLUMBUS, OH","CRANBURY, NJ",
                "CUMMING, GA","CUPERTINO, CA","DALLAS, TX","DENVER, CO","DETROIT, MI","DORAL, FL",
                "DUBLIN, OH","DULUTH, GA","DURHAM, NC","EAST BRUNSWICK, NJ","EAST HARTFORD, CT",
                "EDISON, NJ","EL SEGUNDO, CA","ENGLEWOOD, CO","EVANSTON, IL","EXTON, PA",
                "FAIRFAX, VA","FARMINGTON HILLS, MI","FORT WORTH, TX","FOSTER CITY, CA",
                "FREMONT, CA","FRISCO, TX","GREENWOOD VILLAGE, CO","HERNDON, VA","HOUSTON, TX",
                "INDIANAPOLIS, IN","IRVINE, CA","IRVING, TX","ISELIN, NJ","JACKSONVILLE, FL",
                "JERSEY CITY, NJ","KATY, TX","LAS VEGAS, NV","LIVONIA, MI","LOS ANGELES, CA",
                "LOUISVILLE, KY","MADISON, WI","MCLEAN, VA","MEMPHIS, TN","MENLO PARK, CA",
                "MIAMI, FL","MILPITAS, CA","MILWAUKEE, WI","MINNEAPOLIS, MN","MONMOUTH JUNCTION, NJ",
                "MOON TOWNSHIP, PA","MORRISTOWN, NJ","MORRISVILLE, NC","MOUNTAIN VIEW, CA",
                "NAPERVILLE, IL","NASHVILLE, TN","NEW YORK, NY","NEWARK, CA","NEWTOWN, PA",
                "NORCROSS, GA","NORTH BRUNSWICK, NJ","NORTH KANSAS CITY, MO","NORTHVILLE, MI",
                "NOVI, MI","OAK BROOK, IL","OMAHA, NE","ORLANDO, FL","OVERLAND PARK, KS",
                "PALO ALTO, CA","PARSIPPANY, NJ","PASADENA, CA","PHILADELPHIA, PA","PHOENIX, AZ",
                "PISCATAWAY, NJ","PITTSBURGH, PA","PLAINSBORO, NJ","PLANO, TX","PLEASANTON, CA",
                "PRINCETON, NJ","RALEIGH, NC","REDMOND, WA","REDWOOD CITY, CA","REDWOODSHORES, CA",
                "RESEARCH TRIANGLE PARK, NC","RESTON, VA","RICHARDSON, TX","RICHMOND, VA",
                "ROCHESTER, NY","ROCKVILLE, MD","ROUND ROCK, TX","SALT LAKE CITY, UT",
                "SAN ANTONIO, TX","SAN DIEGO, CA","SAN FRANCISCO, CA","SAN JOSE, CA",
                "SAN MATEO, CA","SAN RAMON, CA","SANTA CLARA, CA","SANTA MONICA, CA",
                "SCHAUMBURG, IL","SCOTTSDALE, AZ","SEATTLE, WA","SECAUCUS, NJ","SHORT HILLS, NJ",
                "SHREWSBURY, MA","SOMERSET, NJ","SOUTH PLAINFIELD, NJ","SOUTHFIELD, MI",
                "SPRINGFIELD, IL","ST. LOUIS, MO","STAMFORD, CT","STERLING, VA","SUNNYVALE, CA",
                "SUNRISE, FL","TAMPA, FL","TROY, MI","WALTHAM, MA","WARREN, NJ","WASHINGTON, DC",
                "WESTBOROUGH, MA","WILMINGTON, DE","WOBURN, MA","WORCESTER, MA");
        
        allCities.setValue("ALISO VIEJO, CA");
        
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