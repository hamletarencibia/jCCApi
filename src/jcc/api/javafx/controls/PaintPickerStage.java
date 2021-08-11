package jcc.api.javafx.controls;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.oracle.javafx.scenebuilder.kit.util.control.paintpicker.PaintPicker;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PaintPickerStage{
	private Window parent;
	private Stage stage;
	private PaintPicker paintPicker;
	private VBox root;
	private ButtonBar buttonBar;
	private Button okButton;
	private Button cancelButton;
	
	public PaintPickerStage(Window parent) {
		this.parent = parent;
		getStage();
	}
	
	private Stage getStage() {
		if(stage == null) {
			stage = new Stage();
			stage.setResizable(false);
			stage.initOwner(parent);
			stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(getRoot());
			stage.setScene(scene);
		}
		return stage;
	}
	private VBox getRoot() {
		if(root == null) {
			root = new VBox();
			root.setPadding(new Insets(5));
			root.setSpacing(10);
			root.getChildren().add(new Pane(getPaintPicker()));
			root.getChildren().add(getButtonBar());
		}
		return root;
	}
	private PaintPicker getPaintPicker() {
		if(paintPicker == null) {
			paintPicker = new PaintPicker(null);
			paintPicker.paintProperty().addListener(new ChangeListener<Paint>() {

				@Override
				public void changed(ObservableValue<? extends Paint> arg0, Paint arg1, Paint arg2) {
					getRoot().autosize();
					Platform.runLater(()->{getRoot().autosize();});
				}
			});
			paintPicker.heightProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					getStage().setHeight(arg2.doubleValue() + 64);
					getStage().setY((screenSize.getHeight() - getStage().getHeight()) / 2);
				}
			});
		}
		return paintPicker;
	}
	private ButtonBar getButtonBar() {
		if(buttonBar == null) {
			buttonBar = new ButtonBar();
			buttonBar.getButtons().add(getOkButton());
			buttonBar.getButtons().add(getCancelButton());
		}
		return buttonBar;
	}
	private Button getOkButton() {
		if(okButton == null) {
			okButton = new Button("Ok");
			okButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					getStage().close();
				}
			});
		}
		return okButton;
	}
	private Button getCancelButton() {
		if(cancelButton == null) {
			cancelButton = new Button("Cancel");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					getPaintPicker().setPaintProperty(null);
					getStage().close();
				}
			});
		}
		return cancelButton;
	}
	public Paint show() {
		getStage().showAndWait();
		return getPaintPicker().getPaintProperty();
	}
	public Paint show(Paint initialPaint) {
		getPaintPicker().setPaintProperty(initialPaint);
		getStage().showAndWait();
		return getPaintPicker().getPaintProperty();
	}
}
