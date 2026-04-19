package fh.technikum.energie.gui.controller;
import fh.technikum.energie.gui.util.Constants;
import fh.technikum.energie.gui.viewmodel.CurrentDataViewModel;
import fh.technikum.energie.gui.viewmodel.HistoryDataViewModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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
        //TODO: implement date-time picker logik
    }
}