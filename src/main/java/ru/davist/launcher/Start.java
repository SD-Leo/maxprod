package ru.davist.launcher;

import com.tulskiy.keymaster.common.Provider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Start {

    public static Provider provider;

    public static Stage window;

    public static TextField text;

    public static void main(String[] args) throws Exception {

//        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//        for ( int i = 0; i < fonts.length; i++ ) {
//            System.out.println(fonts[i]);
//        }

        provider = Provider.getCurrentProvider(false);

        Platform.setImplicitExit(false);

        provider.register(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.ALT_DOWN_MASK), hotKey -> {

            System.out.println("ALT+SPACE pressed");

            Platform.runLater(() -> {
//                if (!window.isShowing()) {
                System.out.println("Show app");
                window.show();
//                window.centerOnScreen();
//                window.setY(window.getY() - 100.0);
//                System.out.println(window.getX() + " - " + window.getY());
//                System.out.println("" + window.isFocused());
                window.toFront();
//                System.out.println("" + window.isFocused());
                window.requestFocus(); // FIXME doesnt work :(
                text.requestFocus();
//                System.out.println("" + window.isFocused());
//                window.setOpacity(0.5);
//                }
            });

        });



        System.out.println("Starting app. Press ALT+SPACE to start");
        Application.launch(MainApp.class, args);

//        JXGrabKeyTest keyTest = new JXGrabKeyTest();
//        keyTest.register(app::run);

    }


}
