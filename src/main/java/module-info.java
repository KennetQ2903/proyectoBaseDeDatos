module com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires lombok;

    opens com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1 to javafx.fxml;
    exports com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1;
    exports com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers;
    opens com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Controllers to javafx.fxml;
    opens com.proyecto.kennet.eduardo.raul.francisco.proyectobasededatos1.Classes;
}