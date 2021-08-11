package jcc.api.javafx.controls;

import javafx.scene.control.TextField;

public class SelectableLabel extends TextField{
	
	public SelectableLabel() {
		setEditable(false);
		setStyle("-fx-background-color:transparent;-fx-background-insets:0;");
	}
	
	public SelectableLabel(String text) {
		this();
		setText(text);
		fitToContent();
	}
	
	public void fitToContent() {
		setPrefWidth(USE_COMPUTED_SIZE);
	}
}
