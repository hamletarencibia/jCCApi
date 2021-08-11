module jcc.api{
	requires transitive java.desktop;
	requires transitive java.sql;
	requires static transitive java.validation;
	requires static transitive javafx.base;
	requires static transitive javafx.graphics;
	requires static transitive javafx.controls;
	requires static transitive org.kordamp.ikonli.javafx;
	requires static transitive org.kordamp.ikonli.fontawesome5;
	requires static transitive com.jfoenix;
	requires static transitive com.gluonhq.scenebuilder.kit;
	
	exports jcc.api.database;
	exports jcc.api.database.mysql;
	exports jcc.api.database.sqlite;
	exports jcc.api.database.sqloader;
	exports jcc.api.interfaces;
	exports jcc.api.javafx.animation;
	exports jcc.api.javafx.controls;
	exports jcc.api.javafx.controls.menu;
	exports jcc.api.javafx.controls.menu.navigationdrawer;
	exports jcc.api.objects;
	exports jcc.api.utils;
	exports jcc.api.validation.bean.constraints;
	exports jcc.api.validation.bean.validators;
	
	opens jcc.api.javafx.controls to javafx.fxml;
}