package fh.technikum.energie.gui.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fh.technikum.energie.gui.model.CurrentData;
import fh.technikum.energie.gui.model.HistoryData;
import fh.technikum.energie.gui.util.Constants;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class DataLoadService {

    public CurrentData loadCurrentData() {
        return new CurrentData(new BigDecimal("1.50"), new BigDecimal("2.533")); //dummy data
        //TODO: server per rest-aufruf + mappign der response in /model/CurrentData
    }

    public HistoryData loadHistoryData(LocalDateTime start, LocalDateTime end) {
        try {
            String serverHistoryURI = String.format("%s/energy/historical?start=%s&end=%s",
                    //folgende params verden statt dem %s gesetzt -> reihenfolge wichtig
                    Constants.URL_LOCALHOST, //localhost url als constante, damit wiederverwendet werden kann
                    start,
                    end);

            String response = callServerByURI(serverHistoryURI).body();
            return mapResponseToHistoryData(response);
        } catch (Exception e) {
            System.out.println("exception in loadHistoryData: " + e.getMessage());
            return null; //no data available or error
        }
    }

    private HttpResponse<String> callServerByURI(String serverUri) throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUri)).GET().build();
        HttpClient client = HttpClient.newBuilder().build();
        return client.send(getRequest, HttpResponse.BodyHandlers.ofString());
    }

    private HistoryData mapResponseToHistoryData(String response) throws JsonProcessingException {
        //mapping with jackson -> dependency notwendig
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, HistoryData.class);
    }
}
