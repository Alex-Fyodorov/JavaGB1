package oop.sem7.dop.meteo;

import java.time.LocalDateTime;

public class AdaptorST500Info implements MeteoSensor {

    private SensorTemperature data;

    public AdaptorST500Info(SensorTemperature data) {
        this.data = data;
    }

    @Override
    public int getId() {
        return data.identifier();
    }

    @Override
    public Float getTemperature() {
        return (float) data.temperature();
    }

    @Override
    public Float getHumidity() {
        return null;
    }

    @Override
    public Float getPressure() {
        return null;
    }

    @Override
    public LocalDateTime getDateTime() {
        LocalDateTime day2 = LocalDateTime.now();
        //LocalDateTime day = LocalDateTime.of(data.year(), 1, data.day(), 0, 0, data.second());
        return day2;
    }
}
