package jcc.api.javafx.skins;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import jcc.api.javafx.controls.BSInputGroup;

public class BSInputGroupSkin extends SkinBase<BSInputGroup>{

	private BSInputGroup control;
	
	public BSInputGroupSkin(BSInputGroup arg0) {
		super(arg0);
		this.control = arg0;
	}
	
	@Override
    protected void layoutChildren(final double x, final double y, final double w, final double h) {
        super.layoutChildren(x, y, w, h);
        HBox root = (HBox) control.getChildrenUnmodifiable().get(0);
        root.getChildren().get(0).getStyleClass().remove("bs-input-group-right-control");
        root.getChildren().get(0).getStyleClass().add("bs-input-group-left-control");
        root.getChildren().get(1).getStyleClass().remove("bs-input-group-left-control");
        root.getChildren().get(1).getStyleClass().add("bs-input-group-right-control");
	}
}
