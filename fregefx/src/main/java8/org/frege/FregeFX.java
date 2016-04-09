package org.frege;

import frege.runtime.*;
import frege.runtime.Phantom.RealWorld;
import frege.prelude.PreludeBase;
import frege.run8.Func;
import frege.run8.Lazy;
import frege.run8.Thunk;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// The type of Stage -> IO () is
// Func.U<Stage, Func.U<RealWorld, Short>>

import java.io.IOException;

public class FregeFX extends Application {
    private static Func.U<Stage, Func.U<RealWorld, Short>> lambda;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	final Lazy<Stage> lazyStage= Thunk.<Stage>lazy(primaryStage);
        try {
        	PreludeBase.TST.performUnsafe(lambda.apply(lazyStage).call()).call();
        } catch(RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    /**
     * @param callback The callback lambda that will receive the primaryStage to work on.
     */
     public static void launch(Func.U<Stage, Func.U<RealWorld, Short>> callback) {
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
