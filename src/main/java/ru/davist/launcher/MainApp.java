package ru.davist.launcher;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainApp extends Application {

    private List<String> database; // = new ArrayList<>();

    public void run() throws IOException {
        System.out.println("launch");
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Launcher");
        primaryStage.setMinWidth(50);
        primaryStage.setMinHeight(30);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        System.out.println("read names");
        readNames();

        VBox box = new VBox();

        GridPane root = new GridPane();
        root.setVgap(5);
        root.setHgap(5);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);
        root.setMinSize(50.0, 30.0);

//        Label label = new Label("Text");
//        root.add(label, 0, 0);

//        TextField text = TextFields.createClearableTextField();
        TextField text = new TextField();
        StringBuilder input = new StringBuilder();

        TableView<Item> table = new TableView<>();

        text.textProperty().addListener(observable -> {
//            System.out.println("Database: " + database);
            String inputText = text.getText();
            if (inputText == null || inputText.isEmpty()) {
                table.setItems(FXCollections.emptyObservableList());
            } else {
                System.out.println("input: " + inputText);
                table.setItems(search(inputText));
            }
        });

        text.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    System.out.println("focus");
                } else {
                    primaryStage.close();
                }
            }
        });


//        ObservableList<Item> items = FXCollections.observableArrayList(
//                new Item("Item1"),
//                new Item("Item2"),
//                new Item("Item3")
//        );

        TableColumn<Item, String> column = new TableColumn<>();
//        column.setMinWidth(100);
        column.setPrefWidth(300.0);
        column.setCellValueFactory(new PropertyValueFactory<>("value"));

        table.getColumns().add(column);

//        table.setItems(items);




//        text.setOnKeyReleased(event -> {
//            if (event.getCode() != KeyCode.ENTER) {
//
////                input.append(event.getText());
//                TextField source = (TextField) event.getSource();
//                System.out.println(source.getText());
////                System.out.println(event.getCode() + " - " + event.getText());
////                System.out.println(event.getCode());
////                label.setText(input.toString());
//            } else {
////                primaryStage.toBack();
//            }
//        });
//        text.setOnInputMethodTextChanged(event -> {
//            String input1 = event.getCommitted();
//            System.out.println(input1);
//            label.setText(input1);
//        });
        root.add(text, 1, 0);
        root.add(table, 1, 1);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    private ObservableList<Item> search(String input) {

        List<String> finded = new ArrayList<>();
        System.out.println("Search size: " + database.size());
        for (String item : database) {
            if (item.toLowerCase().startsWith(input.toLowerCase())) {
                finded.add(item);
            }
        }
        for (String item : database) {
            if (!finded.contains(item) && item.toLowerCase().contains(input.toLowerCase())) {
                finded.add(item);
            }
        }

        List<Item> collect = finded.stream().map(Item::new).collect(Collectors.toList());

        return FXCollections.observableArrayList(collect);
    }

    private void readNames() throws IOException {

        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("names.txt");
        System.out.println("Hello " + resourceAsStream);

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
