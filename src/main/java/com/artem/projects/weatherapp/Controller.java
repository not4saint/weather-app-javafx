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
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    public Text info;
    @FXML
    public Text temp;
    @FXML
    public Text tempReal;
    @FXML
    public Text minTemp;
    @FXML
    public Text maxTemp;
    @FXML
    public Text pressure;
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
        getData.setOnAction(event -> {
            String userCity = city.getText().trim();
            if (!userCity.isBlank()) {
                String output = getUrlContent(String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,uk&APPID=%s", userCity, "78534afcafcfd0caf669bb44eb1d8c14"));

                if (!output.isBlank()) {
                    JSONObject obj = new JSONObject(output);
                    temp.setText("ТЕМПЕРАТУРА: " + Math.ceil(obj.getJSONObject("main").getDouble("temp") - 273.15) + "°C");
                    tempReal.setText("ОЩУЩАЕТСЯ: " + Math.floor(obj.getJSONObject("main").getDouble("feels_like") - 273.15) + "°C");
                    maxTemp.setText("МАКСИМУМ: " + Math.ceil(obj.getJSONObject("main").getDouble("temp_max") - 273.15) + "°C");
                    minTemp.setText("МИНИМУМ: " + Math.floor(obj.getJSONObject("main").getDouble("temp_min") - 273.15) + "°C");
                    pressure.setText("ДАВЛЕНИЕ: " + (obj.getJSONObject("main").getDouble("pressure") * 0.75) + " мм рт.ст.");
                }
            }
        });
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

