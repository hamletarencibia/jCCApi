package jcc.api.javafx.controls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import jcc.api.javafx.skins.SelectableLabelSkin;

public class SelectableLabel extends TextField{
	
	public SelectableLabel() {
		super();
		textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				setPrefWidth(USE_COMPUTED_SIZE);
			}
		});
	}
	
	public SelectableLabel(String text) {
		this();
		setText(text);
		setPrefWidth(USE_COMPUTED_SIZE);
	}
	
	@Override
    protected Skin<?> createDefaultSkin() {
        return new SelectableLabelSkin<>(this);
    }
}
