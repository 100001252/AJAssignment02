/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author XC8184
 */
public class TestJavaFx extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            Pane root = new Pane();

            Button button = new Button("Click me!");
            //button.setStyle("-fx-background-image: url('/testing/background.jpg')");
            button.setStyle("-fx-background-color:red;");//everywhere you can use css but you need to style css attribute with -fx-color:blue;

            root.getChildren().add(button);

            Scene scene = new Scene(root, 800, 400);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
