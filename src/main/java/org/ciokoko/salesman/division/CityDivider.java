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
        City leftFirstCity = cities.get(rand.nextInt(100));
        City rightFirstCity = cities.get(rand.nextInt(100));
        while (leftFirstCity.equals(rightFirstCity)) {
            rightFirstCity = cities.get(rand.nextInt(100));
        }
        List<City> leftList = new ArrayList<>();
        List<City> rightList = new ArrayList<>();
        leftList.add(leftFirstCity);
        cities.remove(leftFirstCity);
        rightList.add(rightFirstCity);
        cities.remove(rightFirstCity);

        DivisionAlgorithm leftAlgorithm = new GreedyCycle(leftFirstCity);
        DivisionAlgorithm rightAlgorithm = new GreedyCycle(rightFirstCity);
        if (!algorithmType.equals(DivisionAlgorithmType.GREEDY_CYCLE)) {
            leftAlgorithm = rightAlgorithm = new NearestNeighbour();
        }

        fillLists(leftList, rightList, cities, leftAlgorithm, rightAlgorithm);
        return Arrays.asList(leftList, rightList);
    }

    private static void fillLists(List<City> leftList, List<City> rightList, List<City> cities,
                                  DivisionAlgorithm leftAlgorithm, DivisionAlgorithm rightAlgorithm) {
        City leftCurrentCity;
        City rightCurrentCity;
        City leftNextCity;
        City rightNextCity;
        int dividedCitiesExpectedSize = cities.size() / 2;
        for (int i = 0; i < dividedCitiesExpectedSize; i++) {
            leftCurrentCity = leftList.get(leftList.size() - 1);
            rightCurrentCity = rightList.get(rightList.size() - 1);
            leftNextCity = leftAlgorithm.nextPoint(cities, leftCurrentCity);
            leftList.add(leftNextCity);
            cities.remove(leftNextCity);
            rightNextCity = rightAlgorithm.nextPoint(cities, rightCurrentCity);
            rightList.add(rightNextCity);
            cities.remove(rightNextCity);
        }
    }
}
