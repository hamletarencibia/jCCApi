package jcc.api.javafx.controls.menu.navigationdrawer;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jcc.api.interfaces.Closeable;

public class NavigationDrawer extends AnchorPane implements Closeable{
	public final boolean STATE_CLOSED = false;
	public final boolean STATE_OPENED = true;
	private boolean state;
	private EventHandler<ActionEvent> eventHandler;
	
	private AnchorPane header;
	private ImageView banner;
	private Node closeIcon;
	
	private ScrollPane scrollPane;
	private Accordion body;
	
	private AnchorPane footer;
	
	public NavigationDrawer(){
		state = STATE_CLOSED;
		setId("side-menu");
		setLayoutX(-300);
		getChildren().add(getHeader());
		getChildren().add(getScrollPane());
		getChildren().add(getFooter());
	}
	
	public boolean getState() {
		return state;
	}
	public boolean isClosed() {
		if(state)
			return false;
		else
			return true;
	}
	public boolean isOpened() {
		if(state)
			return true;
		else
			return false;
	}
	
	public void open() {
		state = STATE_OPENED;
		setEffect(new DropShadow());
		TranslateTransition translateTransition = new TranslateTransition(new Duration(500), this);
		translateTransition.setFromX(0);
		translateTransition.setToX(300);
		translateTransition.play();
	}
	public void close() {
		state = STATE_CLOSED;
		TranslateTransition translateTransition = new TranslateTransition(new Duration(500), this);
		translateTransition.setFromX(300);
		translateTransition.setToX(0);
		translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				setEffect(null);
			}
		});
		translateTransition.play();
		if(eventHandler != null)
			eventHandler.handle(new ActionEvent());
	}
	public void toggle() {
		if(getState()) {
			close();
		}
		else {
			open();
		}
	}
	public void setOnCloseAction(EventHandler<ActionEvent> eventHandler) {
		this.eventHandler = eventHandler;
	}
	
	private AnchorPane getHeader() {
		if(header == null) {
			header = new AnchorPane();
			header.setPrefSize(300, 180);
			header.getChildren().add(getBanner());
			header.getChildren().add(getCloseIcon());
		}
		return header;
	}
	public ImageView getBanner() {
		if(banner == null) {
			banner = new ImageView();
			banner.setFitHeight(180);
			banner.setFitWidth(300);
		}
		return banner;
	}
	public void setCloseIcon(Node closeIcon) {
		header.getChildren().remove(getCloseIcon());
		this.closeIcon = closeIcon;
		this.closeIcon.setCursor(Cursor.HAND);
		AnchorPane.setTopAnchor(this.closeIcon, 10.0);
		AnchorPane.setRightAnchor(this.closeIcon, 10.0);
		this.closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				close();
			}
		});
		header.getChildren().add(getCloseIcon());
	}
	public Node getCloseIcon() {
		if(closeIcon == null) {
			closeIcon = new Button("Cerrar");
			closeIcon.setCursor(Cursor.HAND);
			AnchorPane.setTopAnchor(closeIcon, 10.0);
			AnchorPane.setRightAnchor(closeIcon, 10.0);
			closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					close();
				}
			});
		}
		return closeIcon;
	}
	
	private ScrollPane getScrollPane() {
		if(scrollPane == null) {
			scrollPane = new ScrollPane(getBody());
			scrollPane.setStyle("-fx-background-color: transparent;");
			scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
			scrollPane.setPrefWidth(300);
			scrollPane.backgroundProperty().bind(getBody().backgroundProperty());
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			AnchorPane.setTopAnchor(scrollPane, 180.0);
			AnchorPane.setBottomAnchor(scrollPane, 20.0);
		}
		return scrollPane;
	}
	public Accordion getBody() {
		if(body == null) {
			body = new Accordion();
			body.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
			body.setPrefWidth(300);
			body.minHeightProperty().bind(getScrollPane().heightProperty());
		}
		return body;
	}
	public void addBodyPane(TitledPane pane) {
		getBody().getPanes().add(pane);
	}
	
	public AnchorPane getFooter() {
		if(footer == null) {
			footer = new AnchorPane();
			footer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			footer.setMaxHeight(20);
			footer.setPrefHeight(20);
			footer.setPrefWidth(300);
			AnchorPane.setBottomAnchor(footer, 0.0);
		}
		return footer;
	}
}
