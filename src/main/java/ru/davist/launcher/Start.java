package ru.davist.launcher;

import com.tulskiy.keymaster.common.Provider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Start {

    public static Provider provider;

    public static Stage mainWindow;

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
                System.out.println("Show app");
                mainWindow.show();
                mainWindow.toFront();
                mainWindow.requestFocus(); // FIXME doesnt work :(
            });

        });



        System.out.println("Starting app. Press ALT+SPACE to start");
        Application.launch(MainApp.class, args);

//        JXGrabKeyTest keyTest = new JXGrabKeyTest();
//        keyTest.register(app::run);

    }


}
