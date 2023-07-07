module com.example.projkecik {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires javafx.graphics;

    opens com.example.projkecik to javafx.fxml;
    exports com.example.projkecik;
    exports com.example.projkecik.Controllers;
    opens com.example.projkecik.Controllers to javafx.fxml;
    exports com.example.projkecik.Constructor;
    opens com.example.projkecik.Constructor to javafx.fxml,org.hibernate.orm.core;
}