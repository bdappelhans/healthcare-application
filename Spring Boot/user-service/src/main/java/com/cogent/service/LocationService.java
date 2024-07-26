package com.cogent.service;

import com.cogent.entity.City;
import com.cogent.entity.State;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {

    List<State> findAllStates();

    List<City> findAllCities();

    State findStateById(Long id);

    City findCityById(Long id);
}
