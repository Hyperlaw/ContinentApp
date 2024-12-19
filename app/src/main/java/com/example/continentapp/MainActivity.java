package com.example.continentapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Continent> continents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initializeData();
        showContinentList();

    }

    private void initializeData() {
        List<City> usaCities = Arrays.asList(
                new City("Нью-Йорк", "крупнейший город США, входящий в одну из крупнейших агломераций мира. Население города составляет 8 467 513 человек, агломерации — 19,77 млн (оценка на 2021 год). Нью-Йорк расположен на берегу Атлантического океана в юго-восточной части штата Нью-Йорк.", R.drawable.new_york),
                new City("Лос-Анджелес", "Крупнейший город США расположенный в западной части страны, в Штате Калифорния, на побережье Тихого Океана. Лос-Анджелес является вторым по размеру городом Соединенных Штатов Америки, с общим населением агломерации (то есть города и прилегающих к нему пригородных территорий) около 18 миллионов человек.", R.drawable.los_angeles)
        );

        List<City> fanceCities = Arrays.asList(
                new City("Париж", "Париж – столица Франции, важнейший экономический и культурный центр страны, расположенный в северной части центральной Франции, в регионе Иль-де-Франс на берегах реки Сены. Кроме того, Париж имеет большое международное значение - здесь находятся штаб-квартиры ЮНЕСКО, ОЭСР и Международной торговой палаты.", R.drawable.paris)
        );


        List<Country> northAmericaCountries = Arrays.asList(
                new Country("США", usaCities),
                new Country("Канада", new ArrayList<>())
        );
        List<Country> europeCountries = Arrays.asList(
                new Country("Франция", fanceCities),
                new Country("Италия", new ArrayList<>())
        );

        continents = Arrays.asList(
                new Continent("Северная Америка", northAmericaCountries),
                new Continent("Европа", europeCountries),
                new Continent("Азия", new ArrayList<>()),
                new Continent("Африка", new ArrayList<>())
        );
    }
    public void setHeaderTitle(String title) {
        TextView headerTextView = findViewById(R.id.titleTextView);
        if (headerTextView != null) {
            headerTextView.setText(title);
        }
    }

    private void showContinentList() {
        setHeaderTitle("Континенты");

        List<String> continentNames = new ArrayList<>();
        for (Continent continent : continents) {
            continentNames.add(continent.getName());
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ListFragment fragment = new ListFragment(continentNames, position -> {
            showCountryList(continents.get(position).getCountries());
        }, "Континенты");
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void showCountryList(List<Country> countries) {
        setHeaderTitle("Страны");

        List<String> countryNames = new ArrayList<>();
        for (Country country : countries) {
            countryNames.add(country.getName());
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ListFragment fragment = new ListFragment(countryNames, position -> {
            showCityList(countries.get(position).getCities());
        }, "Страны");
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showCityList(List<City> cities) {
        setHeaderTitle("Города");

        List<String> cityNames = new ArrayList<>();
        for (City city : cities) {
            cityNames.add(city.getName());
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ListFragment fragment = new ListFragment(cityNames, position -> {
            showCityDetails(cities.get(position));
        }, "Города");
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void showCityDetails(City city) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, new CityFragment(city));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
