package org.ciokoko.salesman;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ciokoko.salesman.division.DivisionAlgorithmType;
import org.ciokoko.salesman.optimalization.OptimalizationAlgorithmType;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Controller.show(DivisionAlgorithmType.NEAREST_NEIGHBOUR,
                OptimalizationAlgorithmType.ITERATIVE_LOCAL_SEARCH);
    }
}
