package service;

import domain.Weather;
import repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Weather> getAll(){return repo.getWeather();}

    public void ReadfromDB() {repo.readFromDB();}

    public ArrayList<Weather> sortbyStrathour()
    {
        return repo.getWeather()
                .stream()
                .sorted(Comparator.comparing(w->w.getStart_hour()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
