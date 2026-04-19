package fh.technikum.energie.gui.viewmodel;

import fh.technikum.energie.gui.model.HistoryData;
import fh.technikum.energie.gui.service.DataLoadService;
import fh.technikum.energie.gui.util.Constants;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class HistoryDataViewModel {

    private final DataLoadService dataLoadService = new DataLoadService();

    private final static String NOT_DATA_AVAILABLE = "no data available";

    private final StringProperty historyOutputProducedProperty = new SimpleStringProperty(NOT_DATA_AVAILABLE);
    private final StringProperty historyOutputUsedProperty = new SimpleStringProperty(NOT_DATA_AVAILABLE);
    private final StringProperty historyOutputGridUsedProperty = new SimpleStringProperty(NOT_DATA_AVAILABLE);

    private final StringProperty startTimeProperty = new SimpleStringProperty();
    private final StringProperty endTimeProperty = new SimpleStringProperty();

    public StringProperty historyOutputProducedProperty() {
        return historyOutputProducedProperty;
    }

    public StringProperty historyOutputUsedProperty() {
        return historyOutputUsedProperty;
    }

    public StringProperty historyOutputGridUsedProperty() {
        return historyOutputGridUsedProperty;
    }

    public StringProperty startTimeProperty() {
        return startTimeProperty;
    }

    public StringProperty endTimeProperty() {
        return endTimeProperty;
    }

    public void loadHistoryData() {
        LocalDateTime start = parseStringToLocalDateTime(startTimeProperty.get());
        LocalDateTime end = parseStringToLocalDateTime(endTimeProperty.get());
        HistoryData historyData = dataLoadService.loadHistoryData(start, end);

        if (Objects.nonNull(historyData)) {
            historyOutputProducedProperty.set(historyData.getCommunityProduced() + " kwh");
            historyOutputUsedProperty.set(historyData.getCommunityUsed() + " kwh");
            historyOutputGridUsedProperty.set(historyData.getGridUsed() + " kwh");
        } else {
            historyOutputProducedProperty.set(NOT_DATA_AVAILABLE);
            historyOutputUsedProperty.set(NOT_DATA_AVAILABLE);
            historyOutputGridUsedProperty.set(NOT_DATA_AVAILABLE);
        }
    }

    private LocalDateTime parseStringToLocalDateTime(String dateTime) {
        //datum = (wenn eingabe nicht  gesetzt = null || wenn eingabe leer)
        // ? nimm jetztiges localdattime
        // : sonst aus inputfeld in der gui
        // info: (? :) ist eine "ternray operation", statt if-else
        return (Objects.isNull(dateTime) || dateTime.isEmpty())
                ? LocalDateTime.now()
                : LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(Constants.GUI_DATETIME_PATTERN)); //pattern notwendig, sonst exception, da custom format für app
    }
}
