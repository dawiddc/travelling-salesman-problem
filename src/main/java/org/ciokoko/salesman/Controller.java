package org.ciokoko.salesman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.ciokoko.salesman.division.CityDivider;
import org.ciokoko.salesman.division.DivisionAlgorithmType;
import org.ciokoko.salesman.model.City;
import org.ciokoko.salesman.optimalization.IterativeLocalSearch;
import org.ciokoko.salesman.optimalization.OptimalizationAlgorithm;
import org.ciokoko.salesman.util.CityParser;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Controller {

    private Controller() {}

    static void show(DivisionAlgorithmType algorithmType) {
        Stage primaryStage = new Stage();
        BorderPane mainRoot = new BorderPane();
        mainRoot.setPadding(new Insets(14));
        Pane root = new Pane();
        root.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        List<City> cities = createCities();
        List<Circle> circles = createCircles(cities);
        drawCircles(root, circles);
        List<List<City>> citiesPair = divideAndArrangeCitiesPair(algorithmType, cities);
        for (Line line : joinPoints(citiesPair.get(0), Color.BLUE)) {
            root.getChildren().add(line);
        }
        for (Line line : joinPoints(citiesPair.get(1), Color.RED)) {
            root.getChildren().add(line);
        }
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(0, 100, 15, 100));
        mainRoot.setCenter(root);
        mainRoot.setBottom(label);
        Scene scene = new Scene(mainRoot, 1980, 1080);
        primaryStage.setTitle("Travelling Salesman Problem");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private static List<City> createCities() {
        List<City> cities = new ArrayList<>();
        CityParser parser = new CityParser();
        List<String> points = parser.readCitiesCoordList();
        String[] splitPoints;
        for (String element : points) {
            splitPoints = element.split(" ");
            cities.add(new City.CityBuilder()
                    .setX(Double.valueOf(splitPoints[0]))
                    .setY(Double.valueOf(splitPoints[1]))
                    .build());
        }
        return cities;
    }

    private static List<Circle> createCircles(List<City> cities) {
        List<Circle> circles = new ArrayList<>();
        cities.forEach(city -> {
            Circle circle = new Circle();
            circle.setCenterX(city.getX());
            circle.setCenterY(city.getY());
            circle.setRadius(12);
            circle.setFill(Color.BLACK);
            circles.add(circle);
        });
        return circles;
    }

    private static void drawCircles(Pane root, List<Circle> circles) {
        circles.forEach(circle -> {
            Scale scale = new Scale();
            Label label = new Label(Integer.toString(circles.indexOf(circle)));
            final double MAX_FONT_SIZE = 10.0;
            label.setFont(new Font(MAX_FONT_SIZE));
            scale.setX(0.3);
            scale.setY(0.3);
            scale.setPivotX(80);
            scale.setPivotY(40);
            label.setLayoutX(circle.getCenterX() * 0.3 + 40);
            label.setLayoutY(circle.getCenterY() * 0.3 + 20);
            circle.getTransforms().addAll(scale);
            root.getChildren().add(circle);
            root.getChildren().add(label);
        });
    }

    private static List<List<City>> divideAndArrangeCitiesPair(DivisionAlgorithmType algorithmType, List<City> cities) {
        List<List<City>> lists = CityDivider.divideCitiesToEqualPair(cities, algorithmType);
        Instant startTime = Instant.now();
//        OptimalizationAlgorithm alg = new LocalSearch();
        OptimalizationAlgorithm alg = new IterativeLocalSearch();
        List<City> orderedCities = new ArrayList<>();
        List<City> orderedCities2 = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            orderedCities = alg.arrangePoints(lists.get(0));
            orderedCities2 = alg.arrangePoints(lists.get(1));
        }
        System.out.println("100x Local Search Time: " + Duration.between(startTime, Instant.now()));
        return Arrays.asList(orderedCities, orderedCities2);
    }

    private static List<Line> joinPoints(List<City> orderedCities, Color color) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < orderedCities.size(); i++) {
            int next = i + 1;
            if (i == orderedCities.size() - 1) {
                next = 0;
            }
            Line line = new Line();
            Scale scale = new Scale();
            line.setStartX(orderedCities.get(i).getX());
            line.setStartY(orderedCities.get(i).getY());
            line.setEndX(orderedCities.get(next).getX());
            line.setEndY(orderedCities.get(next).getY());
            line.setStroke(color);
            line.setStrokeWidth(5);
            line.setVisible(true);
            scale.setX(0.3);
            scale.setY(0.3);
            scale.setPivotX(80);
            scale.setPivotY(40);
            line.getTransforms().addAll(scale);
            lines.add(line);
        }
        return lines;
    }
}
