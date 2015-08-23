package org.frege;

import frege.runtime.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FregeFX extends Application {
    private static Lambda lambda;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Applicable inter = lambda.apply(primaryStage);
            Delayed.forced(inter.apply(null).result().forced()); // the second argument is the IO context
        } catch(RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    /**
     * @param callback The callback lambda that will receive the primaryStage to work on.
     */
     public static void launch(Lambda callback) {
         lambda = callback;
         Application.launch();
     }

    public static Parent fxml(String className, String resourceName ) {
        try {
            return FXMLLoader.load(Class.forName(className).getClass().getResource(resourceName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return new Label("could not load " + resourceName);
    }


}
