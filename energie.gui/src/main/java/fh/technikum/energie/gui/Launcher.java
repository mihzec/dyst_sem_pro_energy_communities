package fh.technikum.energie.gui;

import javafx.application.Application;

/**
 * Separate entry point required so the fat JAR manifest does not reference
 * a class that extends javafx.application.Application directly, which would
 * fail without the JavaFX modules on the class path.
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(GuiApplication.class, args);
    }
}
