package jcc.api.javafx.controls;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jcc.api.interfaces.Closeable;

public class LightBox extends Region{
	private double fadeDurationProperty;
	private Closeable[] nodes;
	
	public LightBox() {
		setId("jcc-lightbox");
		setFadeDurationProperty(500);
		setOpacity(0);
		setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, .6), CornerRadii.EMPTY, Insets.EMPTY)));
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
		
	}
	
	public void setCloseNodes(Closeable... nodes) {
		this.nodes = nodes;
	}
	public Closeable[] getCloseNodes() {
		return nodes;
	}
	public double getFadeDurationProperty() {
		return fadeDurationProperty;
	}
	public void setFadeDurationProperty(double fadeDurationProperty) {
		this.fadeDurationProperty = fadeDurationProperty;
	}
	
	public void show() {
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
}
