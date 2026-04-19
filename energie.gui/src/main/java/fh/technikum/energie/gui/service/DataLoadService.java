package fh.technikum.energie.gui.service;

import fh.technikum.energie.gui.model.CurrentData;
import fh.technikum.energie.gui.model.HistoryData;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataLoadService {

    public CurrentData loadCurrentData() {
        return new CurrentData(new BigDecimal("1.50"), new BigDecimal("2.533")); //dummy data
        //TODO: server per rest-aufruf + mappign der response in /model/CurrentData
    }

    public HistoryData loadHistoryData(LocalDateTime start, LocalDateTime end) {
        return new HistoryData(new BigDecimal("10.50"), new BigDecimal("20.5"), new BigDecimal("30.5")); //dummy data
        //TODO: server call for history data
    }
}
