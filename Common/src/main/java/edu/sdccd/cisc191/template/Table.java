package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
Javafx table of all capital cities and select popular cities of the United States listed as city = value
and state = value key value pairs
Allows user to delete any city
Selects random city and returns object of city and state
 */

public class Table extends Application {
    private static Map<String, String> location;
    private TableView<Map.Entry<String, String>> tableView;

    // Launch application when main is called
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) {
        tableView = new TableView<>();

        TableColumn<Map.Entry<String, String>, String> keyColumn = new TableColumn<>("City");
        keyColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));
        keyColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Map.Entry<String, String>, String> valueColumn = new TableColumn<>("State");
        valueColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue()));
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Only uses one HashMap for more efficient storage and redundancy of code
        location = new HashMap<>();

        location.put("San Diego", "California");
        location.put("New York City", "New York");
        location.put("Chicago", "Illinois");
        location.put("Houston", "Texas");
        location.put("Phoenix", "Arizona");
        location.put("Montgomery", "Alabama");
        location.put("Juneau", "Alaska");
        location.put("Little Rock", "Arkansas");
        location.put("Sacramento", "California");
        location.put("Denver", "Colorado");
        location.put("Hartford", "Connecticut");
        location.put("Dover", "Delaware");
        location.put("Tallahassee", "Florida");
        location.put("Atlanta", "Georgia");
        location.put("Honolulu", "Hawaii");
        location.put("Boise", "Idaho");
        location.put("Springfield", "Illinois");
        location.put("Indianapolis", "Indiana");
        location.put("Des Moines", "Iowa");
        location.put("Topeka", "Kansas");
        location.put("Frankfort", "Kentucky");
        location.put("Baton Rouge", "Louisiana");
        location.put("Augusta", "Maine");
        location.put("Annapolis", "Maryland");
        location.put("Boston", "Massachusetts");
        location.put("Lansing", "Michigan");
        location.put("Saint Paul", "Minnesota");
        location.put("Jackson", "Mississippi");
        location.put("Jefferson City", "Missouri");
        location.put("Helena", "Montana");
        location.put("Lincoln", "Nebraska");
        location.put("Carson City", "Nevada");
        location.put("Las Vegas", "Nevada");
        location.put("Concord", "New Hampshire");
        location.put("Trenton", "New Jersey");
        location.put("Santa Fe", "New Mexico");
        location.put("Albany", "New York");
        location.put("Raleigh", "North Carolina");
        location.put("Bismarck", "North Dakota");
        location.put("Columbus", "Ohio");
        location.put("Oklahoma City", "Oklahoma");
        location.put("Salem", "Oregon");
        location.put("Harrisburg", "Pennsylvania");
        location.put("Providence", "Rhode Island");
        location.put("Columbia", "South Carolina");
        location.put("Pierre", "South Dakota");
        location.put("Nashville", "Tennessee");
        location.put("Austin", "Texas");
        location.put("Salt Lake City", "Utah");
        location.put("Montpelier", "Vermont");
        location.put("Richmond", "Virginia");
        location.put("Olympia", "Washington");
        location.put("Charleston", "West Virginia");
        location.put("Madison", "Wisconsin");
        location.put("Cheyenne", "Wyoming");
        location.put("Detroit", "Michigan");
        location.put("Los Angeles", "California");
        location.put("Miami", "Florida");
        location.put("Albuquerque", "New Mexico");
        location.put("Seattle", "Washington");

        ObservableList<Map.Entry<String, String>> entryList = FXCollections.observableArrayList(location.entrySet());

        tableView.getColumns().addAll(keyColumn, valueColumn);
        tableView.setItems(entryList);

        //Adding multiple containers for better organization
        //also allows for quicker GUI changes
        Label title = new Label("Locations");

        VBox locationContainer = new VBox(tableView);
        locationContainer.setSpacing(5);
        locationContainer.setPadding(new Insets(10, 0, 0, 0));

        // Container for delete and random location button
        Button randomLocationButton = new Button("Get Random Location");
        randomLocationButton.setOnAction(event -> {
            generateLocation();
            // removed closing application
        });

        // Deletes selected location
        Button deleteLocationButton = new Button("Delete");
        deleteLocationButton.setOnAction(event -> {
            tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
        });

        VBox buttonContainer = new VBox(10, deleteLocationButton, randomLocationButton);
        buttonContainer.setPadding(new Insets(5));

        VBox root = new VBox(title, locationContainer, buttonContainer);

        // Scene & stage setup
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cities in the United States");
        primaryStage.show();
    }

    // Generate a random number of the table size to get a random row of data and set value to variable location
    public void generateLocation() {
        Random rand = new Random();
        int random = rand.nextInt((tableView.getItems().size()));
        tableView.requestFocus();
        tableView.getSelectionModel().select(random);
        tableView.getFocusModel().focus(random);
    }

    // get method for client class
    public static HashMap<String, String> getLocation() {
        return (HashMap<String, String>) location;
    }
}