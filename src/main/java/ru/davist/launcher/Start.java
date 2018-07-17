package ru.davist.launcher;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.util.Random;

public class Start  {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, Launcher!");
        MainApp app = new MainApp();


//        Provider provider = Provider.getCurrentProvider(false);
//
//        provider.register(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.ALT_DOWN_MASK), hotKey -> {
//            try {
//                app.run();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

//        JXGrabKeyTest keyTest = new JXGrabKeyTest();
//        keyTest.register(app::run);

        System.out.println(randomString(-229985452) + " " + randomString(-147909649));

    }

    public static String randomString(int i)
    {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int k = ran.nextInt(27);
            if (k == 0)
                break;

            sb.append((char)('`' + k));
        }

        return sb.toString();
    }

//    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void start3(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Launcher");

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));


        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setOnAction(event -> {
            Popup popup = new Popup();
            Label lbl = new Label("Wasaup fuckers!");
            lbl.setOnMouseReleased(e -> popup.hide());
            popup.getContent().add(lbl);
            popup.setOnShown(e -> {
                popup.setX(primaryStage.getX() + primaryStage.getWidth()/2 - popup.getWidth()/2);
                popup.setY(primaryStage.getY() + primaryStage.getHeight()/2 - popup.getHeight()/2);
            });
            popup.show(primaryStage);
            Notifications notifications = Notifications.create()
                    .title("Hello")
                    .text("I will gane you");
            notifications.show();

        });
        grid.add(hbBtn, 1, 4);

        primaryStage.setScene(new Scene(grid, 600, 400));
        primaryStage.show();
    }

//    @Override
    public void start2(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Launcher");
        TextField text = new TextField("Hello");

        Label lbl = new Label();

        Button btn = new Button("Push");
        btn.setOnAction(event -> lbl.setText(text.getText()));


        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(10.0);
        pane.setPadding(new Insets(10, 10, 10, 10));

        pane.add(text, 0, 0);
        pane.add(btn, 0, 1);
        pane.add(lbl, 1, 1);

//        StackPane root = new StackPane();
//        root.getChildren().add(text);
//        root.getChildren().add(btn);

        primaryStage.setScene(new Scene(pane, 600, 400));
        primaryStage.show();
    }

    public void start1(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Launcher");
        TextField text = new TextField("Hello");

        Button btn = new Button("Push");
        btn.setOnAction(event -> text.setText("Launcher pushed"));

        StackPane root = new StackPane();
        root.getChildren().add(text);
        root.getChildren().add(btn);

        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }
}
