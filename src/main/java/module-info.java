module com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1 to javafx.fxml;
    exports com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1;
}