package org.ciokoko.salesman.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityParser {
    private static final String REGEX_PATTERN = "[0-9]{1,4}\\s[0-9]{1,4}\\s[0-9]{1,4}";
    private static final Pattern r = Pattern.compile(REGEX_PATTERN);
    private static final Logger LOGGER = Logger.getLogger(CityParser.class.getName());

    public List<String> readCitiesCoordList() {
        List<String> points = new ArrayList<>();
        String[] splitLine;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream("/data/kroA100.tsp")))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = r.matcher(line);
                if (m.find()) {
                    splitLine = line.split(" ");
                    points.add(splitLine[1] + " " + splitLine[2]);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Parsing exception", e);
        }
        return points;
    }
}
