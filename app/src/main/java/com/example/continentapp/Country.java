package com.example.continentapp;

import java.util.List;
public class Country {
    private String name;
    private List<City> cities;

    public Country(String name, List<City> cities) {
        this.name = name;
        this.cities = cities;
    }

    public String getName() { return name; }
    public List<City> getCities() { return cities; }
}