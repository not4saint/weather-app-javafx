package com.artem.projects.weatherapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    void initialize() {
        assert city != null : "fx:id=\"city\" was not injected: check your FXML file 'Untitled'.";
        assert getData != null : "fx:id=\"getData\" was not injected: check your FXML file 'Untitled'.";
        getData.setOnAction(event ->
                String output = getUrlContent("https://history.openweathermap.org/data/2.5/aggregated/year?lat=35&lon=139&appid={API key}")
                System.out.println("All works"));
    }

    private static String getUrlContent(String urlAddress) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("City's name is incorrected");
        }
        return content.toString();
    }

}

