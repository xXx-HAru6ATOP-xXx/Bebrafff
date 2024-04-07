module recipes.nicefood {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.jsoup;
    requires mysql.connector.j;
    opens recipes.utility to javafx.fxml;
    exports recipes.utility;
    exports recipes.scenes;
    opens recipes.scenes to javafx.fxml;
}