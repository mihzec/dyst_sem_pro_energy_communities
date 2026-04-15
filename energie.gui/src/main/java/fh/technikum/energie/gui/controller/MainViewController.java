package fh.technikum.energie.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainViewController {

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        statusLabel.setText("Energie Communities - bereit");
    }
}
