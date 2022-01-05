package ru.rubik.weather.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rubik.weather.DTO.WeatherDTO;
import ru.rubik.weather.service.WeatherService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;


@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/time")
    public ResponseEntity<Map<String, Object>> time() {
        var map = new LinkedHashMap<String, Object>();

        map.put("LocalDate", LocalDate.now());
        map.put("LocalDateTime", LocalDateTime.now());
        map.put("ZonedDateTime", ZonedDateTime.now());
        map.put("ZonedDateTime of date", ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()));

        return ResponseEntity.of(Optional.of(map));
    }

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherDTO getWeatherByCity(@RequestParam("city") String city) throws IOException, JSONException {
        JSONObject json = weatherService.weatherByCity(city);
        BigDecimal temp = (BigDecimal) ((JSONObject) json.get("main")).get("temp");
        int weather_id = (int) ((JSONObject) json.getJSONArray("weather").get(0)).get("id");

        return new WeatherDTO(city, temp, weather_id);
    }

    @GetMapping("/weatherCord")
    public WeatherDTO getWeatherByCords(@RequestParam("lat") double lat,
                                        @RequestParam("lon") double lon) throws IOException {
        JSONObject json = weatherService.weatherByCords(lat, lon);
        String city = json.getString("name");
        BigDecimal temp = (BigDecimal) ((JSONObject) json.get("main")).get("temp");
        int weather_id = (int) ((JSONObject) json.getJSONArray("weather").get(0)).get("id");
        System.out.println(json);

        return new WeatherDTO(city, temp, weather_id);

    }
}
