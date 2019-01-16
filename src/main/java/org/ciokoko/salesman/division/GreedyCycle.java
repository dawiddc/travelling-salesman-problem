package org.ciokoko.salesman.division;

import org.ciokoko.salesman.model.City;
import org.ciokoko.salesman.util.EuclideanDistanceCalculator;

import java.util.*;

public class GreedyCycle implements DivisionAlgorithm {

    public GreedyCycle() {
    }

    @Override
    public City nextPoint(List<City> points, City actualPoint) {

        Map<City, Double> distanceLengths = new HashMap<>();
        points.forEach(point -> {
            double summaryDistance = EuclideanDistanceCalculator.calcForCities(actualPoint, point) + EuclideanDistanceCalculator
                    .calcForCities(points.get(0), point);
            distanceLengths.put(point, summaryDistance);
        });
        Map.Entry<City, Double> min = Collections.min(distanceLengths.entrySet(), Comparator.comparing(Map.Entry::getValue));
        return min.getKey();
    }
}
