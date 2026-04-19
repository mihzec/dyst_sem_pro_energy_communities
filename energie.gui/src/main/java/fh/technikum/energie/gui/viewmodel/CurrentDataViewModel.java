package fh.technikum.energie.gui.viewmodel;

import fh.technikum.energie.gui.model.CurrentData;
import fh.technikum.energie.gui.service.DataLoadService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class CurrentDataViewModel {

    private final static String NOT_DATA_AVAILABLE = "no data available";

    private final DataLoadService dataLoadService = new DataLoadService();

    private final StringProperty communityPoolOutputProperty = new SimpleStringProperty(NOT_DATA_AVAILABLE);
    private final StringProperty gridPortionOutputProperty = new SimpleStringProperty(NOT_DATA_AVAILABLE);

    public void loadCurrentData() {

        CurrentData currentData = dataLoadService.loadCurrentData();

        if (Objects.nonNull(currentData)) {
            communityPoolOutputProperty.set(currentData.getCommunityPoolOutput() + "% used");
            gridPortionOutputProperty.set(currentData.getGridPortionOutput() + "%");
        } else {
            communityPoolOutputProperty.set(NOT_DATA_AVAILABLE);
            gridPortionOutputProperty.set(NOT_DATA_AVAILABLE);
        }
    }

    public StringProperty communityPoolOutputProperty() {
        return communityPoolOutputProperty;
    }

    public StringProperty gridPortionOutputProperty() {
        return gridPortionOutputProperty;
    }
}
