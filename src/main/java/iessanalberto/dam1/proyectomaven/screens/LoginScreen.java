package iessanalberto.dam1.proyectomaven.screens;


import iessanalberto.dam1.proyectomaven.services.UsuarioService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;


public class LoginScreen {

    public HBox getRow1() {
        return row1;
    }

    public TextField getTxtUser() {
        return txtUser;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    private VBox root = new VBox();
    private HBox row1 = new HBox();
    private HBox row2 = new HBox();
    //Componentes de la ventana
    private Label lblUser = new Label("Usuario");
    private TextField txtUser = new TextField();

    private Label lblPassword = new Label("Password");
    private PasswordField txtPassword = new PasswordField();
    private Button btnLogin = new Button("Login");





    UsuarioService usuarioService = new UsuarioService();




    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void setTxtUser(TextField txtUser) {
        this.txtUser = txtUser;
    }

    public void setTxtPassword(PasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public LoginScreen() throws SQLException {

        //Configuramos los elementos del layout
        root.setPadding(new Insets(10));
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        row1.setAlignment(Pos.CENTER_RIGHT);
        row1.setPadding(new Insets(0, 20, 0, 0));
        row1.setSpacing(5);
        row2.setAlignment(Pos.CENTER_RIGHT);
        row2.setPadding(new Insets(0, 20, 0, 0));
        row2.setSpacing(5);

        //Añadimos los componentes a su layout
        row1.getChildren().addAll(lblUser, txtUser);
        row2.getChildren().addAll(lblPassword, txtPassword);
        root.getChildren().addAll(row1, row2, btnLogin);
        //Añadimos la interactividad con los botones
        btnLogin.setOnAction(event -> {

            try {
               if(this.usuarioService.isLogin(txtUser.getText(),txtPassword.getText())){
                    Platform.exit();
               }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } );
    }



    public VBox getRoot() {
        return root;
    }
}

