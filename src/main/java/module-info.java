module iessanalberto.dam1.proyectomaven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens iessanalberto.dam1.proyectomaven to javafx.fxml;
    exports iessanalberto.dam1.proyectomaven;
}