package org.ciokoko.salesman.optimalization;

import org.ciokoko.salesman.model.City;
import org.ciokoko.salesman.util.EuclideanDistanceCalculator;

import java.util.List;

public class LocalSearch extends Opt2Algorithm implements OptimalizationAlgorithm {

    @Override
    public List<City> arrangePoints(List<City> citiesPath) {
        long startTime = System.currentTimeMillis();
        double currentTotalDistance = EuclideanDistanceCalculator.calcForPath(citiesPath);
        boolean next;
        do {
            next = false;
            List<List<City>> newPaths = super.twoOpt(citiesPath);
            for (List<City> path : newPaths) {
                double newDistance = EuclideanDistanceCalculator.calcForPath(path);
                if (newDistance < currentTotalDistance) {
                    System.out.println("Dist. delta: " + (newDistance - currentTotalDistance) + " -- Total curr. dist: " + newDistance);
                    currentTotalDistance = newDistance;
                    citiesPath = path;
                    next = true;
                }
            }
        } while (next);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Local Search time elapsed: " + elapsedTime + " ms");
        return citiesPath;
    }
}
