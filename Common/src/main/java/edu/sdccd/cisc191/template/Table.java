package edu.sdccd.cisc191.template;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/*
Javafx table of all capital cities and select popular cities of the United States listed as city = value
and state = value key value pairs
Allows user to delete any city
Selects random city and returns object of city and state
 */

public class Table extends Application {
    private static TableColumn<Map.Entry<String, String>, String> valueColumn;
    private static TableColumn<Map.Entry<String, String>, String> keyColumn;
    private static Map<String, String> location;
    private static String randomLocation;
    private TableView<Map.Entry<String, String>> tableView;

    // Launch application when main is called
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) {

        initTableView();

        location = new HashMap<>();

        cvsToMap();
        pushCVSDataToTable();


        // Set label and container for table
        Label title = new Label("Locations");

        VBox locationContainer = new VBox(tableView);
        locationContainer.setSpacing(5);
        locationContainer.setPadding(new Insets(10, 0, 0, 0));


        // Set buttons in container
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem()));

        final Button showLocation = new Button("Get Random Location");
        showLocation.setOnAction(e -> {
            generateLocation();

            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished( event -> primaryStage.close() );
            delay.play();
        });

        // Text input with 1 second delay wait after user stops typing in input
        // Searches through to find where input equals to city then scrolls to and highlights row
        Label textFieldLabel = new Label("Search For City");

        TextField textField = new TextField();
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.setOnFinished(event -> {
                for (int i = 0; i < tableView.getItems().size(); i++) {
                    if (newValue.equals(tableView.getItems().get(i).getKey().toString())) {
                        tableView.requestFocus();
                        tableView.getSelectionModel().select(i);
                        tableView.getFocusModel().focus(i);
                        tableView.scrollTo(i);
                        break;
                    }
                }
            });
            pause.playFromStart();

        });

        // Set buttons container and add buttons to
        VBox buttonContainer = new VBox(10, textFieldLabel, textField, deleteButton, showLocation);
        buttonContainer.setPadding(new Insets(5));

        // Add label, table, and buttons to vertical container
        VBox root = new VBox(title, locationContainer, buttonContainer);

        // Set scene with container root and application title
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cities in United States");
        primaryStage.show();
    }

    /**
     * Adds table top values
     */
    public void initTableView() {
        tableView = new TableView<>();

        keyColumn = new TableColumn<>("City");
        keyColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));
        keyColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        valueColumn = new TableColumn<>("State");
        valueColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue()));
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /**
     * Parses cvs and pushes row data into a map of string, string city to state pairs
     */
    public void cvsToMap() {
        List<CitiesStates> items = new CsvToBeanBuilder<CitiesStates>(new InputStreamReader(Table.class.getResourceAsStream("/cities_states.csv")))
                .withType(CitiesStates.class)
                .build()
                .parse();

        Map<String,String> CVSData = items.parallelStream()
                .collect(toMap(CitiesStates::getCity, CitiesStates::getState));
        CVSData.entrySet().parallelStream()
                .forEach(entry -> location.put(entry.getKey(), entry.getValue()));
    }

    public void pushCVSDataToTable() {
        ObservableList<Map.Entry<String, String>> entryList = FXCollections.observableArrayList(location.entrySet());

        tableView.getColumns().addAll(keyColumn, valueColumn);
        tableView.setItems(entryList);
        keyColumn.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(keyColumn);
        tableView.sort();
    }

    /**
     *  Generate a random number of the table size to get a random row of data and set value to variable location
      */
    public void generateLocation() {
        Random rand = new Random();
        int random = rand.nextInt((tableView.getItems().size()));
        tableView.requestFocus();
        tableView.getSelectionModel().select(random);
        tableView.getFocusModel().focus(random);
        tableView.scrollTo(random);
        String randomCity = tableView.getSelectionModel().getSelectedItem().getKey();
        String randomState = tableView.getSelectionModel().getSelectedItem().getValue();
        randomLocation = randomCity + "," + randomState;
    }

    /**
     *  get method for client class
     */
    public static String getLocation() {
        return randomLocation;
    }
}