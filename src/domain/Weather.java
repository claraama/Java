package domain;

public class Weather {
    private int start_hour;
    private int end_hour;
    private int temperature;
    private String precipitaion_prob;
    private String description;

    public Weather(int start_hour, int end_hour, int temperature, String precipitaion_prob, String description) {
        this.start_hour = start_hour;
        this.end_hour = end_hour;
        this.temperature = temperature;
        this.precipitaion_prob = precipitaion_prob;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "start_hour=" + start_hour +
                ", end_hour=" + end_hour +
                ", temperature=" + temperature +
                ", precipitaion_prob=" + precipitaion_prob +
                ", description='" + description + '\'' +
                '}';
    }

    public int getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(int start_hour) {
        this.start_hour = start_hour;
    }

    public int getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(int end_hour) {
        this.end_hour = end_hour;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getPrecipitaion_prob() {
        return precipitaion_prob;
    }

    public void setPrecipitaion_prob(String precipitaion_prob) {
        this.precipitaion_prob = precipitaion_prob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
