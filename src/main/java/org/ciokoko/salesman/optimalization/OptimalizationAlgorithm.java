package org.ciokoko.salesman.optimalization;

import org.ciokoko.salesman.model.City;

import java.util.List;

public interface OptimalizationAlgorithm {
    List<City> arrangePoints(List<City> citiesPath);
}
