module com.fx.todofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.fx.todofx to javafx.fxml;
    exports com.fx.todofx;
}