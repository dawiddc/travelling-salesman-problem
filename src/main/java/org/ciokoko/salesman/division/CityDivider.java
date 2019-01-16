package org.ciokoko.salesman.division;

import org.ciokoko.salesman.model.City;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CityDivider {
    private static final Random rand = new Random();

    private CityDivider() {
    }

    public static List<List<City>> divideCitiesToEqualPair(List<City> cities, DivisionAlgorithmType algorithmType) {
        DivisionAlgorithm algorithm = new GreedyCycle();
        if (!algorithmType.equals(DivisionAlgorithmType.GREEDY_CYCLE)) {
            algorithm = new NearestNeighbour();
        }
        City leftFirstCity = cities.get(rand.nextInt(100));
        City rightFirstCity = cities.get(rand.nextInt(100));
        while (leftFirstCity.equals(rightFirstCity)) {
            rightFirstCity = cities.get(rand.nextInt(100));
        }

        List<City> leftList = new ArrayList<>();
        List<City> rightList = new ArrayList<>();
        leftList.add(leftFirstCity);
        rightList.add(rightFirstCity);
        cities.remove(leftFirstCity);
        cities.remove(rightFirstCity);

        City leftCurrentCity;
        City rightCurrentCity;
        City leftNextCity;
        City rightNextCity;

        int forSize = cities.size() / 2;
        for (int i = 0; i < forSize; i++) {
            leftCurrentCity = leftList.get(leftList.size() - 1);
            rightCurrentCity = rightList.get(rightList.size() - 1);
            leftNextCity = algorithm.nextPoint(cities, leftCurrentCity);
            leftList.add(leftNextCity);
            cities.remove(leftNextCity);
            rightNextCity = algorithm.nextPoint(cities, rightCurrentCity);
            rightList.add(rightNextCity);
            cities.remove(rightNextCity);
        }

        return Arrays.asList(leftList, rightList);
    }
}
