package jcc.api.javafx.controls;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DirectoryChooser extends BorderPane{
	//Simple selection
	private TextField txtFldSingle;
	private Button btnAddSingle;
	
	private String title;
	private File directory;
	private javafx.stage.DirectoryChooser directoryChooser;
	
	public DirectoryChooser() {
		directoryChooser = new javafx.stage.DirectoryChooser();
		directoryChooser.setTitle(getTitle());
		this.setTop(null);
		this.setCenter(getTxtFldSingle());
		this.setRight(getBtnAddSingle());
	}
	public DirectoryChooser(File directory) {
		this();
		setDirectory(directory);
	}
	
	//Single selection mode
	//Initialize the TextField containing the route for the selected
	private TextField getTxtFldSingle() {
		if(txtFldSingle == null) {
			txtFldSingle = new TextField();
			txtFldSingle.setId("txtFldSingle");
			
			//Sets the drag and drop event
			txtFldSingle.setOnDragOver(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent event) {
					if(event.getDragboard().getFiles().get(0).isDirectory())
						event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                event.consume();
				}
			});
			txtFldSingle.setOnDragDropped(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent arg0) {
					Dragboard db = arg0.getDragboard();
					if(db.hasFiles()) {
						getTxtFldSingle().setText(db.getFiles().get(0).getAbsolutePath());
						setDirectory(db.getFiles().get(0));
					}
				}
			});
		}
		return txtFldSingle;
	}
	//Initialize the Button to select a file
	private Button getBtnAddSingle() {
		if(btnAddSingle == null) {
			btnAddSingle = new Button("...");
			btnAddSingle.setId("btnAddSingle");
			
			//Sets the action event
			btnAddSingle.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					//Creates and shows the FileChooser
					File dir = directoryChooser.showDialog(new Stage());
					if(dir != null) {
						setDirectory(dir);
						getTxtFldSingle().setText(getDirectory().getAbsolutePath());
					}
				}
			});
		}
		return btnAddSingle;
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
		getTxtFldSingle().setText(directory.getAbsolutePath());
	}
	
}