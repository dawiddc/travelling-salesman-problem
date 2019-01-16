package org.ciokoko.salesman.division;

import org.ciokoko.salesman.model.City;
import org.ciokoko.salesman.util.EuclideanDistanceCalculator;

import java.util.*;

public class NearestNeighbour implements DivisionAlgorithm {

    public NearestNeighbour() {}

    @Override
    public City nextPoint(List<City> points, City actualPoint) {

        Map<City, Double> distanceLengths = new HashMap<>();
        points.forEach(point -> distanceLengths.put(point, EuclideanDistanceCalculator.calcForCities(actualPoint, point)));
        Map.Entry<City, Double> min = Collections.min(distanceLengths.entrySet(), Comparator.comparing(Map.Entry::getValue));
        return min.getKey();
    }
}
