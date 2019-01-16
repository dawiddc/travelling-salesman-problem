package org.ciokoko.salesman.util;

import org.ciokoko.salesman.model.City;

import java.util.List;

public class EuclideanDistanceCalculator {
    private EuclideanDistanceCalculator() {
    }

    public static double calcForCities(City a, City b) {
        return Math.sqrt(Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2));
    }

    public static double calcForPath(List<City> path) {
        double distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += calcForCities(path.get(i), path.get(i + 1));
        }
        distance += calcForCities(path.get(path.size() - 1), path.get(0));
        return distance;
    }
}
