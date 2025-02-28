package iessanalberto.dam1.proyectomaven.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LoginScreen {
    private VBox root = new VBox();
    private HBox fila1 = new HBox();
    private HBox fila2 = new HBox();
    //Componentes de la ventana
    private Label lblMatricula = new Label("Usuario");
    private TextField txtMatricula = new TextField();

    private Label lblDni = new Label("Password");
    private PasswordField txtDni = new PasswordField();
    private Button btnDni = new Button("Buscar");


    public LoginScreen() {
        //Configuramos los elementos del layout
        root.setPadding(new Insets(10));
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        fila1.setAlignment(Pos.CENTER_RIGHT);
        fila1.setPadding(new Insets(0, 20, 0, 0));
        fila1.setSpacing(5);
        fila2.setAlignment(Pos.CENTER_RIGHT);
        fila2.setPadding(new Insets(0, 20, 0, 0));
        fila2.setSpacing(5);

        //Añadimos los componentes a su layout
        fila1.getChildren().addAll(lblMatricula, txtMatricula);
        fila2.getChildren().addAll(lblDni, txtDni);
        root.getChildren().addAll(fila1, fila2, btnDni);
        //Añadimos la interactividad con los botones

    }

    public VBox getRoot() {
        return root;
    }
}

