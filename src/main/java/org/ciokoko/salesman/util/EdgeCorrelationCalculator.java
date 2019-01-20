package org.ciokoko.salesman.util;

import org.ciokoko.salesman.division.CityDivider;
import org.ciokoko.salesman.division.DivisionAlgorithmType;
import org.ciokoko.salesman.model.City;
import org.ciokoko.salesman.optimalization.LocalSearch;

import java.util.ArrayList;
import java.util.List;

public class EdgeCorrelationCalculator {
    private final List<List<City>> leftOptimalPath = new ArrayList<>();
    private final List<List<City>> rightOptimalPath = new ArrayList<>();

    public EdgeCorrelationCalculator() {}

    public void calculateOptimalPathsCorrelation(List<City> cities) {
        for (int i = 0; i < 1000; i++) {
            List<List<City>> dividedCities = CityDivider.divideCitiesToEqualPair(cities, DivisionAlgorithmType.GREEDY_CYCLE);
            LocalSearch localSearch = new LocalSearch();
            leftOptimalPath.add(localSearch.arrangePoints(dividedCities.get(0)));
            rightOptimalPath.add(localSearch.arrangePoints(dividedCities.get(1)));
        }

        leftOptimalPath.forEach(path -> {
            int similarEdges = 0;

        });
    }

}
