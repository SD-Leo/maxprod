package ru.davist.launcher;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ru.davist.launcher.model.DesktopEntry;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainApp extends Application {

    private List<String> database; // = new ArrayList<>();
    private StopHandler handler;
    private Stage window;
    private AppScanner scanner;

//    public void run(StopHandler handler) throws IOException {
//        System.out.println("11111111: " + this);
//        this.handler = handler;
//        System.out.println("launch");
//        launch();
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = new Stage();
        Start.window = window;
        window.setTitle("Launcher");
        window.setMinWidth(50);
        window.setMinHeight(30);
        window.setResizable(false);
//        window.initStyle(StageStyle.UNDECORATED);
//        window.initStyle(StageStyle.TRANSPARENT);
//        window.initStyle(StageStyle.UTILITY);
//        window.setAlwaysOnTop(true);
//        window.centerOnScreen();
//        window.setY(window.getY() - 100.0);


//        System.out.println("read names");
//        readNames();

        scanner = new AppScanner();
        scanner.scan();

        GridPane root = new GridPane();
        root.setVgap(5);
        root.setHgap(5);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);
        root.setMinSize(50.0, 30.0);

        TextField text = new TextField();
        Start.text = text;
//        StringBuilder input = new StringBuilder();

        TableView<Item> table = new TableView<>();

        text.setFont(Font.font("Liberation Sans", FontWeight.LIGHT, 30));
        text.textProperty().addListener(observable -> {
//            System.out.println("Database: " + database);
            String inputText = text.getText();
            if (inputText == null || inputText.isEmpty()) {
                table.setItems(FXCollections.emptyObservableList());
            } else {
//                System.out.println("input: " + inputText);
                table.setItems(search(inputText));
                table.getSelectionModel().select(0);
            }
        });
        text.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {

                Item selectedItem = table.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {

                    String command = exec(selectedItem.getDesktopEntry());
                    System.out.println("Hide app on Enter: " + command);
                    text.clear();
                    window.hide();
                } else {
                    System.out.println("Selected item is null");
                }

//                System.out.println("Hide app on Enter");
                text.clear();

                window.hide();
            }
            if (event.getCode() == KeyCode.DOWN) {
                table.requestFocus();
            }
        });


        table.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Item selectedItem = table.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {

                    String command = exec(selectedItem.getDesktopEntry());
                    System.out.println("Hide app on Enter: " + command);
                    text.clear();
                    window.hide();
                } else {
                    System.out.println("Selected item is null");
                }
            }
        });


        TableColumn<Item, String> name = new TableColumn<>();
//        name.setMinWidth(100);
        name.setPrefWidth(300.0);
        name.setCellValueFactory(new PropertyValueFactory<>("value"));
        table.getColumns().add(name);

        TableColumn<Item, String> exec = new TableColumn<>();
        exec.setPrefWidth(300.0);
        exec.setCellValueFactory(new PropertyValueFactory<>("exec"));
        table.getColumns().add(exec);

//        table.setItems(items);


        root.add(text, 1, 0);
        root.add(table, 1, 1);

        window.setScene(new Scene(root, 600, 400));
//        primaryStage.show();

        window.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("old: " + oldValue + ", new: " + newValue);
            if (!newValue) {
                System.out.println("Hide app");
                text.clear();
                window.hide();
            }
        });

        window.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("Hide app by escape key");
                text.clear();
                window.hide();
            }
        });
    }

    private String exec(DesktopEntry entry) {
        //                    String command = "/opt/Enpass/bin/runenpass.sh %U";
        String command = entry.getExec();

        List<String> commAndArgs = Arrays.asList(command.split(" "));
        ProcessBuilder pb = new ProcessBuilder(commAndArgs);
        try {
            Process p = pb.start();
//            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return command;
    }


    private ObservableList<Item> search1(String input) {

        List<String> found = new ArrayList<>();
//        System.out.println("Search size: " + database.size());
        for (String item : database) {
            if (item.toLowerCase().startsWith(input.toLowerCase())) {
                found.add(item);
            }
        }
        for (String item : database) {
            if (!found.contains(item) && item.toLowerCase().contains(input.toLowerCase())) {
                found.add(item);
            }
        }

        List<Item> collect = found.stream().map(Item::new).collect(Collectors.toList());

        return FXCollections.observableArrayList(collect);
    }

    private ObservableList<Item> search(String input) {

        List<DesktopEntry> found = scanner.find(input);

        List<Item> collect = found.stream().map(entry -> new Item(entry.getName(), entry)).collect(Collectors.toList());

        return FXCollections.observableArrayList(collect);
    }

    private void readNames() throws IOException {

        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("names.txt");

        Scanner scanner = new Scanner(resourceAsStream);

        database = new ArrayList<>();
        while (scanner.hasNext()) {
            String readLine = scanner.nextLine();
            database.add(readLine);
//            System.out.println(readLine);
        }
        scanner.close();

        System.out.println("Read size: " + database.size());
//        System.out.println("Database after read: " + database);


        resourceAsStream.close();

    }
}
