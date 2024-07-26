package com.cogent.controller;

import com.cogent.entity.City;
import com.cogent.entity.State;
import com.cogent.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/states")
    public List<State> getStates() {
        return locationService.findAllStates();
    }

    @GetMapping("/cities")
    public List<City> getCities() {
        return locationService.findAllCities();
    }

    @GetMapping("/city/{id}")
    public City getCity(@PathVariable Long id) {
        return locationService.findCityById(id);
    }

    @GetMapping("/state/{id}")
    public State getState(@PathVariable Long id) {
        return locationService.findStateById(id);
    }
}
