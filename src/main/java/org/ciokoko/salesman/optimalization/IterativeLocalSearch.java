package org.ciokoko.salesman.optimalization;

import org.ciokoko.salesman.model.City;
import org.ciokoko.salesman.util.EuclideanDistanceCalculator;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IterativeLocalSearch implements OptimalizationAlgorithm {

    private final Random random = new Random();
    private final LocalSearch localSearch = new LocalSearch();

    @Override
    public List<City> arrangePoints(List<City> citiesPath) {
        Instant startTime = Instant.now();
        int noImprovalCount = 0;
        double bestPathDistance = EuclideanDistanceCalculator.calcForPath(citiesPath);
        while (noImprovalCount < 1000) {
            List<City> newCitiesPath = perturbate(citiesPath);
            newCitiesPath = localSearch.arrangePoints(newCitiesPath);
            double currentIterationDistance = EuclideanDistanceCalculator.calcForPath(newCitiesPath);
            if (currentIterationDistance < bestPathDistance) {
                bestPathDistance = currentIterationDistance;
                citiesPath = newCitiesPath;
                noImprovalCount = 0;
            } else {
                noImprovalCount++;
            }
        }
        System.out.println("Iterative Local Search time elapsed: "
                + Duration.between(startTime, Instant.now()));
        System.out.println("Best distance found:" + EuclideanDistanceCalculator.calcForPath(citiesPath));
        return citiesPath;
    }

    private List<City> perturbate(List<City> citiesPath) {
        List<City> newPath = new ArrayList<>(citiesPath);
        for (int i = 0; i < 4; i++) {
            int randomPoint = random.nextInt(citiesPath.size() - 1) + 1;
            int nextRandomPoint = random.nextInt(citiesPath.size() - 1) + 1;
            newPath = switchPoints(citiesPath, randomPoint, nextRandomPoint);
        }
        return newPath;
    }

    private List<City> switchPoints(List<City> citiesPath, int randomPoint, int nextRandomPoint) {
        List<City> newCitiesPath = new ArrayList<>(citiesPath);
        City tempCity = newCitiesPath.get(randomPoint);
        newCitiesPath.set(randomPoint, newCitiesPath.get(nextRandomPoint));
        newCitiesPath.set(nextRandomPoint, tempCity);
        return newCitiesPath;
    }


}
