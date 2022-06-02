package jcc.api.javafx.skins;

import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;

public class SelectableLabelSkin<T extends TextField> extends TextFieldSkin{

	public SelectableLabelSkin(T textField) {
		super(textField);
		textField.setEditable(false);
		textField.setStyle("-fx-background-color:transparent;-fx-background-insets:0;");
	}
	
}
