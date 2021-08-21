package jcc.api.javafx.controls;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import jcc.api.javafx.css.ResourceLoader;
import jcc.api.javafx.skins.BSInputGroupSkin;

public class BSInputGroup extends Control{
	public enum Position {LEFT, RIGHT}
	
	private HBox root;
	private TextField textField;
	private Button button;
	
	public BSInputGroup() {
		this(Position.RIGHT);
	}
	public BSInputGroup(Position position) {
		super();
		setButtonPosition(position);
		getChildren().add(getRoot());
	}
	public BSInputGroup(Node graphic) {
		this(Position.RIGHT);
		getButton().setGraphic(graphic);
	}
	public BSInputGroup(Position position, Node graphic) {
		this(position);
		getButton().setGraphic(graphic);
	}
	
	private HBox getRoot() {
		if(root == null) {
			root = new HBox();
			root.prefHeightProperty().bind(heightProperty());
		}
		return root;
	}
	public TextField getTextField() {
		if(textField == null) {
			textField = new TextField();
			textField.prefHeightProperty().bind(heightProperty());
			HBox.setHgrow(textField, Priority.ALWAYS);
		}
		return textField;
	}
	public Button getButton() {
		if(button == null) {
			button = new Button();
			button.prefHeightProperty().bind(heightProperty());
		}
		return button;
	}
	@Override
	protected Skin<?> createDefaultSkin(){
		return new BSInputGroupSkin(this);
	}
	
	public void setButtonStyle(String style) {
		getButton().setStyle(style);
	}
	public ObservableList<String> getButtonStyleClass() {
		return getButton().getStyleClass();
	}
	 /*
	  * ######################################################################################
	  * ####		Styleable Properties #####################################################
	  * ######################################################################################
	  */    
    private StyleableObjectProperty<Position> buttonPosition = new SimpleStyleableObjectProperty<>(StyleableProperties.BUTTON_POSITION, BSInputGroup.this, "buttonPosition", Position.RIGHT);
    public final StyleableObjectProperty<Position> buttonPositionProperty() {
        return this.buttonPosition;
    }
    public final Position getButtonPosition() {
        return this.buttonPositionProperty().get();
    }
    public final void setButtonPosition(final Position buttonPosition) {
    	this.buttonPositionProperty().set(buttonPosition);
		getRoot().getChildren().removeAll(getRoot().getChildren());
		if(this.buttonPositionProperty().isEqualTo(Position.RIGHT).get()) {
			getRoot().getChildren().add(getTextField());
			getRoot().getChildren().add(getButton());
		}
		else {
			getRoot().getChildren().add(getButton());
			getRoot().getChildren().add(getTextField());
		}
    }
    
	private static class StyleableProperties {        
        private static final CssMetaData<BSInputGroup, Position> BUTTON_POSITION = new CssMetaData<BSInputGroup, Position>("-jfx-button-position", StyleConverter.getEnumConverter(Position.class), Position.RIGHT) {
            @Override
            public boolean isSettable(BSInputGroup control) {
                return control.buttonPosition == null || !control.buttonPosition.isBound();
            }

            @Override
            public StyleableObjectProperty<Position> getStyleableProperty(BSInputGroup control) {
                return control.buttonPositionProperty();
            }
        };
	}
	
	@Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }
	
	@Override
    public String getUserAgentStylesheet() {
        return ResourceLoader.get("bs-input-group.css").toExternalForm();
    }
}