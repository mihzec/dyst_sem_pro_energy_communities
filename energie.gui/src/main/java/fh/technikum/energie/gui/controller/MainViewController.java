package fh.technikum.energie.gui.controller;
import fh.technikum.energie.gui.controller.components.DateTimePickerController;
import fh.technikum.energie.gui.util.Constants;
import fh.technikum.energie.gui.viewmodel.CurrentDataViewModel;
import fh.technikum.energie.gui.viewmodel.HistoryDataViewModel;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Label outputCommunityPoolValue;
    @FXML
    private Label outputGridPortionValue;

    @FXML
    private ComboBox<String> startTimeDatePicker;
    @FXML
    private ComboBox<String> endTimeDatePicker;

    @FXML
    private Label historyOutputProduced;
    @FXML
    private Label historyOutputUsed;
    @FXML
    private Label historyOutputGridUsed;

    CurrentDataViewModel currentDataViewModel = new CurrentDataViewModel();
    HistoryDataViewModel historyDataViewModel = new HistoryDataViewModel();

    //click-methode für aktuelle daten
    public void onRefreshClicked() {
        currentDataViewModel.loadCurrentData();
    }

    //click-methode für historische daten
    public void onShowDataClicked() {
        historyDataViewModel.loadHistoryData();
    }

    @FXML
    public void initialize() {
        startTimeDatePicker.getEditor().setEditable(false);
        endTimeDatePicker.getEditor().setEditable(false);

        outputCommunityPoolValue.textProperty().bind(currentDataViewModel.communityPoolOutputProperty());
        outputGridPortionValue.textProperty().bind(currentDataViewModel.gridPortionOutputProperty());

        historyOutputProduced.textProperty().bind(historyDataViewModel.historyOutputProducedProperty());
        historyOutputUsed.textProperty().bind(historyDataViewModel.historyOutputUsedProperty());
        historyOutputGridUsed.textProperty().bind(historyDataViewModel.historyOutputGridUsedProperty());

        startTimeDatePicker.valueProperty().bindBidirectional(historyDataViewModel.startTimeProperty());
        endTimeDatePicker.valueProperty().bindBidirectional(historyDataViewModel.endTimeProperty());

        //load current-data and history-data with localdatetime.now() on initialization
        currentDataViewModel.loadCurrentData();
        historyDataViewModel.loadHistoryData();

        //set date pattern as prompt text for datepicker
        startTimeDatePicker.setPromptText(Constants.GUI_DATETIME_PATTERN);
        endTimeDatePicker.setPromptText(Constants.GUI_DATETIME_PATTERN);
    }

    @FXML
    public void openDateTimePicker(Event event) {
        if (event != null && event.getSource() instanceof ComboBox<?>) {
            event.consume();
            ComboBox<String> callingCombobox = (ComboBox<String>) event.getSource();
            showDateTimePicker(callingCombobox);
        }
    }

    private void showDateTimePicker(ComboBox<String> callingCombobox) {
        //call popup via platfrom.runLater() -> same application thread -> wait for combobox.open() is finished
        Platform.runLater(() -> {
            callingCombobox.hide();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/components/DateTimePicker.fxml")
                );
                //init datepicker popup layout
                Stage datePickerPopup = new Stage();
                datePickerPopup.setTitle("Datepicker");
                datePickerPopup.initModality(Modality.APPLICATION_MODAL);
                datePickerPopup.setScene(new Scene(loader.load()));

                DateTimePickerController dateTimePickerController = loader.getController();
                //parse calling combobox to datetimepicker controller
                dateTimePickerController.setDateTimeInputProperty(callingCombobox.valueProperty());

                datePickerPopup.showAndWait();
            } catch (IOException _) {} // _ = unnamed variable -> da nicht benutzt (feature seit java 21)
        });
    }
}