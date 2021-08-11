package jcc.api.javafx.controls.menu;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import com.jfoenix.transitions.JFXFillTransition;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class MenuButton extends Button{
	private Paint backgroundColor = Color.WHITE;
	private Paint backgroundHoverColor = Color.BLACK;
	
	public MenuButton() {
		FontIcon icon = new FontIcon(FontAwesomeSolid.BARS);
		icon.setIconSize(18);
		setGraphic(icon);
		setStyle("-fx-border-style: solid; -fx-border-width: 2; -fx-border-radius: 5;");
		setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(5), Insets.EMPTY)));
		setCursor(Cursor.HAND);
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				fillBackground(backgroundHoverColor);
				Transition transition = new Transition() {
					{
						setCycleDuration(Duration.millis(250));
		                setInterpolator(Interpolator.EASE_OUT);
					}
					
					@Override
					protected void interpolate(double arg0) {
						icon.setFill(new Color(arg0, arg0, arg0, 1));
					}
				};
				transition.play();
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				fillBackground(backgroundColor);
				Transition transition = new Transition() {
					{
						setCycleDuration(Duration.millis(250));
		                setInterpolator(Interpolator.EASE_OUT);
					}
					
					@Override
					protected void interpolate(double arg0) {
						icon.setFill(new Color(1-arg0, 1-arg0, 1-arg0, 1));
					}
				};
				transition.play();
			}
		});
	}
	private void fillBackground(Paint to) {
		JFXFillTransition transition = new JFXFillTransition();
		transition.setDuration(Duration.millis(250));
		transition.setRegion(this);
		transition.setFromValue((Color) getBackground().getFills().get(0).getFill());
		transition.setToValue((Color) to);
		transition.play();
	}
	public Paint getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Paint backgroundColor) {
		this.backgroundColor = backgroundColor;
		setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(5), Insets.EMPTY)));
		
	}
	public Paint getBackgroundHoverColor() {
		return backgroundHoverColor;
	}
	public void setBackgroundHoverColor(Paint backgroundHoverColor) {
		this.backgroundHoverColor = backgroundHoverColor;
	}

}
