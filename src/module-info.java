module Manager {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires java.base;
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
