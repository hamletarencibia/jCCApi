package jcc.api.javafx.controls;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import jcc.api.interfaces.Closeable;

public class FrostGlass extends Region{	
	private double fadeDurationProperty;
	private Closeable[] nodes;
	private WritableImage image;
	private Node container;
	
	public FrostGlass(Node container) {
		this.container = container;
		setId("jcc-frost-glass");
		setFadeDurationProperty(500);
		setOpacity(0);
		setLayoutX(-10);
		setLayoutY(-10);
		setEffect(new BoxBlur(15, 15, 3));
		setVisible(false);
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(nodes != null)
					for(Closeable node : nodes) {
						node.close();
					}
			}
		});
		widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				updateBackground();
			}
		});
		heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				updateBackground();
			}
		});
	}
	
	public void setCloseNodes(Closeable... nodes) {
		this.nodes = nodes;
	}
	public Closeable[] getCloseNodes() {
		return nodes;
	}
	
	public void show() {
		updateBackground();
		setVisible(true);
		FadeTransition fadeTransition = new FadeTransition(new Duration(getFadeDurationProperty()), this);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
		fadeTransition.play();
	}
	public void hide() {
		FadeTransition fadeTransition = new FadeTransition(new Duration(getFadeDurationProperty()), this);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				setVisible(false);
			}
		});
		fadeTransition.play();
	}

	public double getFadeDurationProperty() {
		return fadeDurationProperty;
	}
	public void setFadeDurationProperty(double fadeDurationProperty) {
		this.fadeDurationProperty = fadeDurationProperty;
	}
	
	private void updateBackground() {
		if(getWidth() <= 0 || getHeight() <= 0) {
			return;
		}
		
		if(image == null || getWidth() != image.getWidth() || getHeight() != image.getHeight()) {
			image = new WritableImage((int)getWidth(), (int)getHeight());
			BackgroundImage backImg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
			Pane pane = new Pane();
			pane.setLayoutX(0);
			pane.setLayoutY(0);
			pane.prefHeightProperty().bind(heightProperty());
			pane.prefWidthProperty().bind(widthProperty());
			pane.setBackground(new Background(backImg));
			getChildren().add(pane);
		}
		
		double x = getLayoutX();
		double y = getLayoutY();
		
		SnapshotParameters sp = new SnapshotParameters();
		Rectangle2D rect = new Rectangle2D(x, y, getWidth(), getHeight());
		sp.setViewport(rect);
		if(container != null)
		container.snapshot(sp, image);
	}
}
