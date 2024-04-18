package ajaxcities.web;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

@Named
@SessionScoped
public class CityBean implements Serializable {

    private static final String[] ALL_COUNTRIES = {"China", "England", "France", "Germany", "USA"};
    private static final Map<String, String[]> CITIES_MAP = createCitiesMap();

    // Properties to support the Facelets page
    private ArrayList<SelectItem> countrySelectItems;
    private String selectedCountry;
    private ArrayList<SelectItem> citySelectItems;
    private String selectedCity;
    
    // Post-construction initialization -- do not change
    @PostConstruct
    public void init() {
        createCountrySelectItems();
        createCitySelectItems();
    }

    // Default constructor -- do not change
    public void CityBean() {
    }

    // Getter and setter methods for the properties declared
    /*
    Hints:
    1. Not all properties have a setter method -- Don't make 
       one if the property should not be modified directly
    2. IMPORTANT! If the selected Country is changed, the
       City options must be updated! (by calling
	   the helper method createCitySelectItems())
    */

    
    // For showing the timestamps -- do not change
    public String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    
    // Private helper methods
    
    private synchronized void createCountrySelectItems() {
        countrySelectItems = new ArrayList<>(ALL_COUNTRIES.length + 1);
        countrySelectItems.add(new SelectItem(""));
        for (String country : ALL_COUNTRIES) {
            countrySelectItems.add(new SelectItem(country));
        }
        selectedCountry = "";
    }

    private synchronized void createCitySelectItems() {
        String[] cities;
        if (selectedCountry != null && !selectedCountry.isEmpty()
                && (cities = getCities(selectedCountry)) != null
                && cities.length > 0) {
            selectedCity = cities[0];
            citySelectItems = new ArrayList<>(cities.length);
            for (String city : cities) {
                citySelectItems.add(new SelectItem(city));
            }
        } else {
            selectedCity = "";
            citySelectItems = new ArrayList<>();
        }
    }

    private String[] getCities(String country) {
        return CITIES_MAP.get(country);
    }

    private static Map<String, String[]> createCitiesMap() {
        HashMap<String, String[]> result = new HashMap<>();
        result.put("China", new String[]{"Beijing", "Hongkong", "Shanghai"});
        result.put("England", new String[]{"London", "Oxford", "Manchester"});
        result.put("France", new String[]{"Paris", "Avignon", "Bordeaux"});
        result.put("Germany", new String[]{"Berlin", "Munich", "Hamburg"});
        result.put("USA", new String[]{"New York", "Los Angeles", "Chicago"});

        return result;
    }

}
