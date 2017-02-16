package dk.ninjabear.rssscanner.gui;

import dk.ninjabear.rssscanner.service.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KeyWordDialog extends Stage {
	private Controller controller = new Controller();
	
	public KeyWordDialog() {
		initModality(Modality.APPLICATION_MODAL);
		setTitle("Select Sudoku");
		BorderPane borderPane = new BorderPane();
		initContent(borderPane);
		setScene(new Scene(borderPane));
	}
	
	private final TextField addTextField = new TextField();
	private final ListView<String> wordListView = new ListView<>();
	
	private void initContent(BorderPane root) {
		root.setPadding(new Insets(10));

		Button addButton = new Button("+");
		Button removeButton = new Button("-");
		addButton.setOnAction(e -> controller.addAction());
		removeButton.setOnAction(e -> controller.removeAction());
		HBox top = new HBox();
		top.setSpacing(5);
		top.getChildren().addAll(addTextField, addButton, removeButton);
		root.setTop(top);
		
		root.setCenter(wordListView);
		
		Button closeButton = new Button("close");
		closeButton.setDefaultButton(true);
		closeButton.setOnAction(e -> controller.closeAction());
		root.setBottom(closeButton);
		
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
