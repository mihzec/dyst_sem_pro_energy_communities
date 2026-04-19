package fh.technikum.energie.gui.controller.components;

import fh.technikum.energie.gui.util.Constants;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimePickerController {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField inputHour;
    @FXML
    private TextField inputMinute;

    private ObjectProperty<String> dateTimeInputProperty;

    public void setDateTimeInputProperty(ObjectProperty<String>  dateTimeInputProperty) {
        this.dateTimeInputProperty = dateTimeInputProperty;
    }

    @FXML
    public void confirmBtnClicked() {
        //convert gui input to LocalDateTime
        LocalDateTime convertedLocalDateTime = LocalDateTime.of(
                datePicker.getValue(),
                LocalTime.of(
                        Integer.parseInt(inputHour.getText()),
                        Integer.parseInt(inputMinute.getText())
                )
        );
        //convert to pattern "dd.MM.yyyy HH:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.GUI_DATETIME_PATTERN);
        String convertedToPattern = convertedLocalDateTime.format(formatter);
        this.dateTimeInputProperty.setValue(convertedToPattern);

        datePicker.getScene().getWindow().hide();
    }
}
