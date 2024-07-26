package com.cogent.service;

import com.cogent.entity.City;
import com.cogent.entity.State;
import com.cogent.repository.CityRepository;
import com.cogent.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<State> findAllStates() {
        return stateRepository.findAll();
    }

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public State findStateById(Long id) {
        Optional<State> result = stateRepository.findById(id);
        State state = null;

        if (result.isPresent()) {
            state = result.get();
        } else {
            throw new RuntimeException("Did not find state with ID " + id);
        }

        return state;
    }

    @Override
    public City findCityById(Long id) {
        Optional<City> result = cityRepository.findById(id);
        City city = null;

        if (result.isPresent()) {
            city = result.get();
        } else {
            throw new RuntimeException("Did not find city with ID " + id);
        }

        return city;
    }
}
