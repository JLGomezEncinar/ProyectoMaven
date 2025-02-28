module iessanalberto.dam1.proyectomaven {
    requires javafx.controls;
    requires javafx.fxml;


    opens iessanalberto.dam1.proyectomaven to javafx.fxml;
    exports iessanalberto.dam1.proyectomaven;
}