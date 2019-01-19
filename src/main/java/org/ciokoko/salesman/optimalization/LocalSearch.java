package org.ciokoko.salesman.optimalization;

import org.ciokoko.salesman.model.City;
import org.ciokoko.salesman.util.EuclideanDistanceCalculator;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalSearch implements OptimalizationAlgorithm {

    @Override
    public List<City> arrangePoints(List<City> citiesPath) {
        Instant startTime = Instant.now();
        double currentTotalDistance = EuclideanDistanceCalculator.calcForPath(citiesPath);
        int noImprovalCount = 0;
        while (noImprovalCount < 10) {
            List<List<City>> newPaths = twoOpt(citiesPath);
            for (List<City> path : newPaths) {
                double newDistance = EuclideanDistanceCalculator.calcForPath(path);
                if (newDistance < currentTotalDistance) {
                    System.out.println("Dist. delta: " + (newDistance - currentTotalDistance) + " -- Total curr. dist: " + newDistance);
                    currentTotalDistance = newDistance;
                    citiesPath = path;
                    noImprovalCount = 0;
                } else {
                    noImprovalCount++;
                }
            }
        }
        System.out.println("Local Search time elapsed: "
                + Duration.between(startTime, Instant.now()));
        return citiesPath;
    }

    private List<List<City>> twoOpt(List<City> citiesPath) {
        List<List<City>> availablePaths = new ArrayList<>();
        int pathLength = citiesPath.size();
        for (int i = 0; i < pathLength - 1; i++) {
            for (int j = i + 1; j < pathLength - 1; j++) {
                List<City> beginningSublist = new ArrayList<>(citiesPath.subList(0, i));
                List<City> middleSublist = new ArrayList<>(citiesPath.subList(i, j));
                Collections.reverse(middleSublist);
                List<City> endSublist = new ArrayList<>(citiesPath.subList(j, pathLength));
                List<City> newPath = new ArrayList<>();
                newPath.addAll(beginningSublist);
                newPath.addAll(middleSublist);
                newPath.addAll(endSublist);
                availablePaths.add(newPath);
            }
        }
        return availablePaths;
    }


}
