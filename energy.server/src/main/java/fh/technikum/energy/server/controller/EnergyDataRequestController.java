package fh.technikum.energy.server.controller;

import fh.technikum.energy.server.dto.CurrentDataDto;
import fh.technikum.energy.server.dto.HistoryDataDto;
import fh.technikum.energy.server.service.DataLoadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/energy")
public class EnergyDataRequestController {

    private final DataLoadService dataLoadService;

    public EnergyDataRequestController(DataLoadService dataLoadService) {
        this.dataLoadService = dataLoadService;
    }

    @GetMapping("/historical")
    public HistoryDataDto getHistoricalData(@RequestParam(name = "start") LocalDateTime start,
                                            @RequestParam(name = "end") LocalDateTime end) {
        return dataLoadService.loadHistoryData(start, end);
    }

    @GetMapping("/current")
    public CurrentDataDto getCurrentData() {
        return dataLoadService.loadCurrentData();
    }
}
