package iessanalberto.dam1.proyectomaven;

import iessanalberto.dam1.proyectomaven.navigation.Navigation;

import javafx.application.Application;

import javafx.stage.Stage;



public class HelloApplication extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Navigation.navigate("LoginScreen");
    }

    public static void main(String[] args) {
        launch(args);

    }




}


