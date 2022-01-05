package ru.rubik.weather.service;


import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
@Service
public class WeatherService {
    private final String token = "de5f48f58c5f6e63e14fa2eb16cafcc5";

    public JSONObject weatherByCity(String city) throws IOException {
        final URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ city +
                "&appid=" + token + "&units=metric");
        return getJsonObject(url);
    }

    public JSONObject weatherByCords(double lat, double lon) throws IOException {
        final URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+
                "&appid="+token + "&units=metric");
        return getJsonObject(url);
    }

    private JSONObject getJsonObject(URL url) throws IOException {
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            JSONObject json = new JSONObject(String.valueOf(content));
            return json;
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
