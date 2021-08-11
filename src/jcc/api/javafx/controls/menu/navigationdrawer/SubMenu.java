package jcc.api.javafx.controls.menu.navigationdrawer;

import com.jfoenix.transitions.JFXFillTransition;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jcc.api.javafx.animation.TextFillTransition;

public class SubMenu extends TitledPane{
	private VBox body;
	private Color hoverColor = Color.LIGHTGRAY;
	private Color backgroundColor = Color.WHITE;
	private Color fillColor = Color.WHITE;
	private Color hoverFillColor = Color.BLACK;
	
	public SubMenu() {
		TitledPane self = this;
		Platform.runLater(() -> {
			Pane title = (Pane) this.lookup(".title");
			title.setStyle(title.getStyle() + "-fx-font-size: 24px;");
			title.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					JFXFillTransition transition = new JFXFillTransition();
					transition.setDuration(Duration.millis(250));
					transition.setRegion(title);
					transition.setFromValue((Color) title.getBackground().getFills().get(0).getFill());
					transition.setToValue(hoverColor);
					transition.play();
					TextFillTransition tft = new TextFillTransition(Duration.millis(250), self);
					tft.setFrom((Color) getTextFill());
					tft.setTo(hoverFillColor);
					tft.play();
				}
			});
			title.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					JFXFillTransition transition = new JFXFillTransition();
					transition.setDuration(Duration.millis(250));
					transition.setRegion(title);
					transition.setFromValue((Color) title.getBackground().getFills().get(0).getFill());
					transition.setToValue((Color) backgroundColor);
					transition.play();
					TextFillTransition tft = new TextFillTransition(Duration.millis(250), self);
					tft.setFrom((Color) getTextFill());
					tft.setTo(fillColor);
					tft.play();
				}
			});
		});
		setContent(getBody());
	}
	
	public VBox getBody() {
		if(body == null) {
			body = new VBox();
			body.setStyle("-fx-padding: 0;");
		}
		return body;
	}

	public Color getHoverColor() {
		return hoverColor;
	}

	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		Platform.runLater(() -> {
			Pane title = (Pane) this.lookup(".title");
			title.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
		});
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		setTextFill(fillColor);
	}

	public Color getHoverFillColor() {
		return hoverFillColor;
	}

	public void setHoverFillColor(Color hoverFillColor) {
		this.hoverFillColor = hoverFillColor;
	}
}
