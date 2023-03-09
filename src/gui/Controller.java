package gui;

import domain.Weather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    ObservableList<Weather> listOBS= FXCollections.observableArrayList();
    ObservableList<String> listcombo= FXCollections.observableArrayList();
    @FXML
    private ListView<Weather> weather_list_FX;
    @FXML
    private ComboBox<String> description_FX;

    public void initialize()
    {
        service.ReadfromDB();
        service.sortbyStrathour().forEach(w->listOBS.add(w));
        weather_list_FX.setItems(listOBS);


        Vector<String> vector = new Vector<>();
        for(Weather g:service.getAll())
            if(g.getDescription()!=null)
            {
                String[] arr=g.getDescription().split(", ");
                vector.add(arr[0]);
            }

        for(String s:vector)
            if(!listcombo.contains(s))
                listcombo.add(s);
        description_FX.setItems(listcombo);
    }

    @FXML
    void afterselectingComboBox(ActionEvent event)
    {
        //select a description from the combo box the list is filtered and sorted by start hour
        ArrayList<Weather>weathersorted=service.sortbyStrathour(); //ii dam la un array list de tip weather lista sortata
        ArrayList<Weather> weatherfiltered=weathersorted.stream()  //o filtram cu acele elem care au fost selectate din combo box
                .filter(w->w.getDescription().contains(description_FX.getSelectionModel().getSelectedItem()))
                .collect(Collectors.toCollection(ArrayList::new));

        for(Weather w:weatherfiltered)
            listOBS.add(w);
        weather_list_FX.setItems(listOBS);
    }

    @FXML
    private TextArea descriptionText_FX;
    @FXML
    private TextField precipitation_FX;
    @FXML
    private TextField updatePrec_FX;
    @FXML
    private TextField updateDesc_FX;
    @FXML
    void afterclick(ActionEvent event)
    {
        //an interval is clicked in the list precipitation and description is showed in 2 other places
        //update
        Weather selected=weather_list_FX.getSelectionModel().getSelectedItem();
        descriptionText_FX.setText(selected.getDescription());
        precipitation_FX.setText(selected.getPrecipitaion_prob());

        var newdescription=updateDesc_FX.getText();
        selected.setDescription(newdescription);

        var newprec=updatePrec_FX.getText();
        selected.setPrecipitaion_prob(newprec);

        weather_list_FX.refresh();

    }
}
