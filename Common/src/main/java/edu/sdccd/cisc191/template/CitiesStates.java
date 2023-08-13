package edu.sdccd.cisc191.template;

import com.opencsv.bean.CsvBindByName;

public class CitiesStates {
    @CsvBindByName(column = "State")
    private String state;

    @CsvBindByName(column = "City")
    private String city;
    @Override
    public String toString() {
        return String.format(
                "StateCountry[State=%s, City=%s]",
                state, city);
    }

    public String getState() {
        return state;
    }
    public String getCity() {
        return city;
    }
}