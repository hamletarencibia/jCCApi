package jcc.api.javafx.controls;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import jcc.api.utils.FileUtils;

public class FileChooser extends BorderPane{
	//Extensions
	public static final ExtensionFilter IMAGES = new ExtensionFilter("Images", "*.jpg","*.jpeg","*.png","*.gif","*.svg","*.bmp");
	public static final ExtensionFilter VIDEOS = new ExtensionFilter("Videos", "*.avi","*.flv","*.mp4","*.mpg","*.mpeg","*.mkv","*.rmvb");
	public static final ExtensionFilter ALL = new ExtensionFilter("All", "*.*");
	//Simple selection
	private TextField txtFldSingle;
	private Button btnAddSingle;
	
	//Multiple selection
	private HBox buttonPane;
	private Button btnAddMultiple;
	private Button btnClearAll;
	private ScrollPane scrollPane;
	private VBox vBox;
	
	private boolean multiple;
	private String lang;
	private String title;
	private List<File> files;
	private javafx.stage.FileChooser fileChooser;
	
	public FileChooser() {
		files = new LinkedList<File>();
		setMultiple(false);
		fileChooser = new javafx.stage.FileChooser();
		fileChooser.setTitle(getTitle());
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
					if (event.getGestureSource() != getTxtFldSingle() && event.getDragboard().hasFiles()) {
						if(fileChooser.getExtensionFilters().size() > 0)
							loop:for(ExtensionFilter extFilter : fileChooser.getExtensionFilters()) {
								for(String extension : extFilter.getExtensions()) {
									if(event.getDragboard().getFiles().get(0).getName().toLowerCase().matches("." + extension + "$.*")) {
										event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
										break loop;
									}
								}
							}
						else
							event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                }
	                event.consume();
				}
			});
			txtFldSingle.setOnDragDropped(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent arg0) {
					Dragboard db = arg0.getDragboard();
					if(db.hasFiles()) {
						getTxtFldSingle().setText(db.getFiles().get(0).getAbsolutePath());
						files = new LinkedList<File>();
						files.add(db.getFiles().get(0));
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
					File tempFile = fileChooser.showOpenDialog(new Stage());
					if(tempFile != null) {
						getTxtFldSingle().setText(tempFile.getAbsolutePath());
						files.clear();
						files.add(tempFile);
					}
				}
			});
		}
		return btnAddSingle;
	}
	
	//Multiple selection mode
	//Initialize the panel containing the buttons Add and Clear
	private HBox getButtonPane() {
		if(buttonPane == null) {
			buttonPane = new HBox();
			buttonPane.getChildren().add(getBtnAddMultiple());
			buttonPane.getChildren().add(getBtnClearAll());
		}
		return buttonPane;
	}
	//Initialize the Button to select multiple files
	private Button getBtnAddMultiple() {
		if(btnAddMultiple == null) {
			btnAddMultiple = new Button("Add");
			//Inserts the FontAwesome PLUS icon
			Text layoutIcon = new FontIcon(FontAwesomeSolid.PLUS);
			btnAddMultiple.setGraphic(layoutIcon);
			btnAddMultiple.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					//Creates and shows the FileChooser
					List<File> tempFiles = fileChooser.showOpenMultipleDialog(new Stage());
					if(tempFiles != null) {
						//Adds the labels with the file routes to the VBox
						addLabelsToVBox(tempFiles);
					}
				}
			});
		}
		return btnAddMultiple;
	}
	//Initialize the Button to clear the selected files
	private Button getBtnClearAll() {
		if(btnClearAll == null) {
			btnClearAll = new Button("Clear");
			Text layoutIcon = new FontIcon(FontAwesomeSolid.MINUS);
			btnClearAll.setGraphic(layoutIcon);
			btnClearAll.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					files.clear();
					getVBox().getChildren().clear();
				}
			});
		}
		return btnClearAll;
	}
	private ScrollPane getScrollPane() {
		if(scrollPane == null) {
			scrollPane = new ScrollPane(getVBox());
			scrollPane.setOnDragOver(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent event) {
					if (event.getGestureSource() != getScrollPane()
	                        && event.getDragboard().hasFiles()) {
	                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                }
	                event.consume();
				}
			});
			scrollPane.setOnDragDropped(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent arg0) {
					Dragboard db = arg0.getDragboard();
					if(db.hasFiles()) {
						addLabelsToVBox(db.getFiles());
						
					}
				}
			});
		}
		return scrollPane;
	}
	private VBox getVBox() {
		if(vBox == null) {
			vBox = new VBox();
			vBox.setSpacing(2);
		}
		return vBox;
	}
	
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
		if(multiple) {
			this.setRight(null);
			this.setTop(getButtonPane());
			this.setCenter(getScrollPane());
		}
		else {
			this.setTop(null);
			this.setCenter(getTxtFldSingle());
			this.setRight(getBtnAddSingle());
		}
	}

	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
		switch (this.lang.toLowerCase()) {
			case "spanish":
			case "es":
			case "español":
				getBtnAddMultiple().setText("Agregar");
				getBtnClearAll().setText("Limpiar");
				break;
			default:
				break;
		}
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		fileChooser.setTitle(title);
	}

	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	
	private void addLabelsToVBox(List<File> files) {
		for(File file : files) {
			List<File> tempList = FileUtils.fetchAllFiles(file);
			loop:for(File newFile : tempList) {
				if(this.files.contains(newFile)) {
					tempList.remove(newFile);
					continue;
				}
				if(fileChooser.getExtensionFilters().size() > 0) {
					if(fileChooser.getExtensionFilters().size() == 1 && fileChooser.getExtensionFilters().get(0).getDescription().toLowerCase().equals("all")) {
						getVBox().getChildren().add(getLabel(newFile));
						continue;
					}
					for(ExtensionFilter extFilter : fileChooser.getExtensionFilters()) {
						for(String extension : extFilter.getExtensions()) {
							if(newFile.getName().toLowerCase().matches("." + extension + "$.*")) {
								getVBox().getChildren().add(getLabel(newFile));
								continue loop;
							}
						}
						
					}
				}
				else {
					getVBox().getChildren().add(getLabel(newFile));
					continue;
				}
				tempList.remove(newFile);
			}
			this.files.addAll(tempList);
		}
	}
	private Pane getLabel(File file) {
		Pane pane = new Pane();
		//Initializing label
		Label label = new Label(file.getName());
		label.setBackground(new Background(new BackgroundFill(Color.web("#e3e3e3"), new CornerRadii(5), Insets.EMPTY)));
		label.setPadding(new Insets(0, 5, 0, 15));
		label.setLayoutX(2);
		//Initializing the remove buton label
		Label removeLabel = new Label();
		removeLabel.setLayoutX(5);
		Text layoutIcon = new FontIcon(FontAwesomeSolid.TIMES);
		removeLabel.setGraphic(layoutIcon);
		removeLabel.setVisible(false);
		removeLabel.setCursor(Cursor.HAND);
		removeLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				getVBox().getChildren().remove(pane);
				getFiles().remove(file);
			}
		});
		//Setting the mouse entered and exited events for showing the remove label
		pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				removeLabel.setVisible(true);
			}
		});
		pane.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				removeLabel.setVisible(false);
			}
		});
		pane.getChildren().add(label);
		pane.getChildren().add(removeLabel);
		return pane;
	}
	
	public void addExtensionFilter(ExtensionFilter extensionFilter) {
		fileChooser.getExtensionFilters().add(extensionFilter);
	}
	public ObservableList<ExtensionFilter> getExtensionFilters(){
		return fileChooser.getExtensionFilters();
	}
	public void removeFilters() {
		fileChooser.getExtensionFilters().removeAll(fileChooser.getExtensionFilters());
	}
	
}