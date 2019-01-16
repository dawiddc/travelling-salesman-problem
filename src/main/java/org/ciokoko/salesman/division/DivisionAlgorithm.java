package org.ciokoko.salesman.division;

import org.ciokoko.salesman.model.City;

import java.util.List;

interface DivisionAlgorithm {
    City nextPoint(List<City> points, City actualPoint);
}
