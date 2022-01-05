package ru.rubik.weather.DTO;

import java.math.BigDecimal;

public class WeatherDTO {
    private String city;
    private BigDecimal temp;
    private int weather_id;

    public WeatherDTO(String city, BigDecimal temp, int weather_id) {
        this.city = city;
        this.temp = temp;
        this.weather_id = weather_id;
    }

    public int getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(int weather_id) {
        this.weather_id = weather_id;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
