package dk.ninjabear.rssscanner.gui;

import dk.ninjabear.rssscanner.service.Service;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KeyWordDialog extends Stage {
	private Controller controller = new Controller();
	
	public KeyWordDialog() {
		initModality(Modality.APPLICATION_MODAL);
		setTitle("Edit Keywords");
		GridPane pane = new GridPane();
		initContent(pane);
		setScene(new Scene(pane));
	}
	
	private final TextField addTextField = new TextField();
	private final ListView<String> wordListView = new ListView<>();
	
	private void initContent(GridPane root) {
		root.setPadding(new Insets(10));
		root.setVgap(5);
		root.setHgap(5);
		

		Button addButton = new Button("+");
		Button removeButton = new Button("-");
		addButton.setOnAction(e -> controller.addAction());
		removeButton.setOnAction(e -> controller.removeAction());

		HBox.setHgrow(addTextField, Priority.ALWAYS);
		HBox top = new HBox();
		top.setSpacing(5);
		top.getChildren().addAll(addTextField, addButton, removeButton);
		root.add(top, 0, 0);
		
		root.add(wordListView, 0, 1);
		
		Button closeButton = new Button("close");
		closeButton.setDefaultButton(true);
		closeButton.setOnAction(e -> controller.closeAction());
		GridPane.setHalignment(closeButton, HPos.RIGHT);
		root.add(closeButton, 0, 2);
		
		controller.updateControls();
	}
	
	public void reset() {
		controller.updateControls();
	}
	
	private class Controller {
		
		public void updateControls() {
			wordListView.getItems().setAll(Service.getKeyWords());
			wordListView.getSelectionModel().clearSelection();
			addTextField.setText(null);
		}
		
		public void addAction() {
			String keyWord = addTextField.getText();
			if (!keyWord.isEmpty()) {
				Service.addKeyWord(keyWord);
				addTextField.setText(null);
				updateControls();
			}
		}
		
		public void removeAction() {
			String keyWord = wordListView.getSelectionModel().getSelectedItem();
			if (keyWord != null) {
				Service.removeKeyWord(keyWord);
				updateControls();
			}
		}
		
		public void closeAction() {
			KeyWordDialog.this.hide();
		}
	}
}
