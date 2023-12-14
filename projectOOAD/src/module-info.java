module projectOOAD {
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	opens main;
	opens model.object;
	opens model.database;
	opens view;
}