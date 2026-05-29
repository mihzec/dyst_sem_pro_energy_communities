package fh.technikum.energy.gui.service;

import fh.technikum.energy.gui.model.CurrentData;
import fh.technikum.energy.gui.model.HistoryData;
import fh.technikum.energy.gui.util.Constants;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class DataLoadService {

    public CurrentData loadCurrentData() {

        try {
            String serverHistoryURI = String.format("%s/energy/current", Constants.URL_LOCALHOST);
            String response = callServerByURI(serverHistoryURI).body();
            return mapResponseToCurrentData(response);
        } catch (Exception e) {
            System.out.println("exception in loadCurrentData: " + e.getMessage());
            return null; //no data available or error
        }
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

    private HistoryData mapResponseToHistoryData(String response) {
        //mapping with jackson -> dependency notwendig
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, HistoryData.class);
    }

    private CurrentData mapResponseToCurrentData(String response) {
        //mapping with jackson -> dependency notwendig
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, CurrentData.class);
    }
}
