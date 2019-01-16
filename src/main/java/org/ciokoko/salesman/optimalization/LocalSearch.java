package org.ciokoko.salesman.optimalization;

import org.ciokoko.salesman.model.City;
import org.ciokoko.salesman.util.EuclideanDistanceCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalSearch implements OptimalizationAlgorithm {
    private static final Logger LOGGER = Logger.getLogger(LocalSearch.class.getName());

    @Override
    public List<City> arrangePoints(List<City> citiesPath) {
        double currentTotalDistance = EuclideanDistanceCalculator.calcForPath(citiesPath);
        boolean next;
        do {
            next = false;
            List<List<City>> newPaths = twoOpt(citiesPath);
            for (List<City> path : newPaths) {
                double newDistance = EuclideanDistanceCalculator.calcForPath(path);
                if (newDistance < currentTotalDistance) {
                    currentTotalDistance = newDistance;
                    LOGGER.log(Level.INFO, "Current Total Dist: {0}", currentTotalDistance);
                    citiesPath = path;
                    next = true;
                }
            }
        } while (next);

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
