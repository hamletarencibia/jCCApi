package jcc.api.javafx.controls.menu.navigationdrawer;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class MenuItem extends Pane{
	private BorderPane iconPane;
	private Label labelTitle;
	private Region background;
	
	public MenuItem() {
		setCursor(Cursor.HAND);
		getChildren().add(getRegionBackground());
		getChildren().add(getIconPane());
		getChildren().add(getLabelTitle());
		addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
			FadeTransition ft = new FadeTransition(Duration.millis(250), getRegionBackground());
			ft.setFromValue(getRegionBackground().getOpacity());
			ft.setToValue(1);
			ft.play();
		});
		addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
			FadeTransition ft = new FadeTransition(Duration.millis(250), getRegionBackground());
			ft.setFromValue(getRegionBackground().getOpacity());
			ft.setToValue(0);
			ft.play();
		});
	}
	public MenuItem(String title) {
		this();
		setTitle(title);
	}
	public MenuItem(Node icon) {
		this();
		setIcon(icon);
	}
	public MenuItem(String title, Node icon) {
		this(title);
		setIcon(icon);
	}
	
	private Region getRegionBackground() {
		if(background == null) {
			background = new Region();
			background.prefHeightProperty().bind(this.heightProperty());
			background.prefWidthProperty().bind(this.widthProperty());
			background.setOpacity(0);
		}
		return background;
	}
	private BorderPane getIconPane() {
		if(iconPane == null) {
			iconPane = new BorderPane();
			iconPane.setPrefSize(50, 50);
			iconPane.setLayoutX(10);
		}
		return iconPane;
	}
	public Label getLabelTitle() {
		if(labelTitle == null) {
			labelTitle = new Label();
			labelTitle.setPrefHeight(50);
			labelTitle.setStyle("-fx-font-size: 24px;");
			labelTitle.setLayoutX(60);
		}
		return labelTitle;
	}
	
	public String getTitle() {
		return getLabelTitle().getText();
	}
	public void setTitle(String title) {
		getLabelTitle().setText(title);
	}
		
	public Node getIcon() {
		if(getIconPane().getChildren().isEmpty())
			return null;
		else
			return getIconPane().getChildren().get(0);
	}
	public void setIcon(Node icon) {
		getIconPane().setCenter(icon);
	}
	public void setHoverPaint(Paint color) {
		getRegionBackground().setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
	}
}