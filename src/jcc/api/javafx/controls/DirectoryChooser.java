package jcc.api.javafx.controls;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jcc.api.javafx.controls.BSInputGroup.Position;

public class DirectoryChooser extends BorderPane{
	//Simple selection
	private BSInputGroup inputGroupSingle;
	
	private String title;
	private File directory;
	private javafx.stage.DirectoryChooser directoryChooser;
	
	public DirectoryChooser() {
		directoryChooser = new javafx.stage.DirectoryChooser();
		directoryChooser.setTitle(getTitle());
		this.setTop(null);
		this.setCenter(getInputGroupSingle());
		this.setRight(null);
	}
	public DirectoryChooser(File directory) {
		this();
		setDirectory(directory);
	}
	
	//Single selection mode
	private BSInputGroup getInputGroupSingle() {
		if(inputGroupSingle == null) {
			inputGroupSingle = new BSInputGroup(Position.RIGHT, new Label("..."));
			inputGroupSingle.getTextField().setOnDragOver(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent event) {
					if(event.getDragboard().getFiles().get(0).isDirectory())
						event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                event.consume();
				}
			});
			inputGroupSingle.getTextField().setOnDragDropped(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent arg0) {
					Dragboard db = arg0.getDragboard();
					if(db.hasFiles()) {
						inputGroupSingle.getTextField().setText(db.getFiles().get(0).getAbsolutePath());
						setDirectory(db.getFiles().get(0));
					}
				}
			});
			inputGroupSingle.getButton().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					//Creates and shows the FileChooser
					File dir = directoryChooser.showDialog(new Stage());
					if(dir != null) {
						setDirectory(dir);
						inputGroupSingle.getTextField().setText(getDirectory().getAbsolutePath());
					}
				}
			});
		}
		return inputGroupSingle;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		directoryChooser.setTitle(title);
	}

	public File getDirectory() {
		return directory;
	}
	public void setDirectory(File directory) {
		this.directory = directory;
		if(directory != null)
			inputGroupSingle.getTextField().setText(directory.getAbsolutePath());
	}
	
}