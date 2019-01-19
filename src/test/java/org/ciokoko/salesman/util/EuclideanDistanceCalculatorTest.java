package org.ciokoko.salesman.util;

import org.ciokoko.salesman.model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class EuclideanDistanceCalculatorTest {

    private City cityA;
    private City cityB;
    private City cityC;
    private City cityD;
    private City cityE;

    @Before
    public void setup() {
        cityA = new City.CityBuilder().setX(0.0).setY(0.0).build();
        cityB = new City.CityBuilder().setX(10.0).setY(0.0).build();
        cityC = new City.CityBuilder().setX(0.0).setY(10.0).build();
        cityD = new City.CityBuilder().setX(10.0).setY(10.0).build();
        cityE = new City.CityBuilder().setX(0.0).setY(0.0).build();
    }

    @Test
    public void calcForCitiesShouldReturnProperDistance() {
        // when
        double distance0 = EuclideanDistanceCalculator.calcForCities(cityA, cityE);
        double distance1 = EuclideanDistanceCalculator.calcForCities(cityA, cityB);
        double distance2 = EuclideanDistanceCalculator.calcForCities(cityA, cityC);
        double distance3 = EuclideanDistanceCalculator.calcForCities(cityA, cityD);
        double distance4 = EuclideanDistanceCalculator.calcForCities(cityB, cityD);
        // then
        Assert.assertEquals(0, distance0, 0.1);
        Assert.assertEquals(10, distance1, 0.1);
        Assert.assertEquals(10, distance2, 0.1);
        Assert.assertEquals(Math.sqrt(100 + 100), distance3, 0.1);
        Assert.assertEquals(10, distance4, 0.);
    }

    @Test
    public void calcForPath() {
        // given
        List<City> cities = Arrays.asList(cityA, cityB, cityC, cityD, cityE);
        // when
        double distance = EuclideanDistanceCalculator.calcForPath(cities);
        // then
        Assert.assertEquals(20 + 2 * Math.sqrt(200), distance, 0.1);
    }
}