package org.ciokoko.salesman.optimalization;

import org.ciokoko.salesman.model.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Opt2Algorithm {

    protected List<List<City>> twoOpt(List<City> citiesPath) {
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
