package dk.ninjabear.rssscanner.gui;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import dk.ninjabear.rssscanner.model.Message;
import dk.ninjabear.rssscanner.service.Service;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * @author asbjoern1982@gmail.com
 * @see http://www.vogella.com/tutorials/RSSFeed/article.html
 */
public class RSSScannerMain extends Application {
	private final Controller controller = new Controller();
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) {
		controller.primaryStage = primaryStage;
		primaryStage.setTitle("RSS Scanner");
		BorderPane root = new BorderPane();
		initContent(root);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	private final TextField keyWordsTextField = new TextField();
	private final ListView<String> feedList = new ListView<>();
	private final ListView<Message> messageList = new ListView<>();
	private final TextArea messageText = new TextArea();
	
	private void initContent(BorderPane root) {
		root.setPadding(new Insets(10));
		GridPane center = new GridPane();
		root.setCenter(center);
		
		keyWordsTextField.setFocusTraversable(false);
		keyWordsTextField.setMouseTransparent(true);
		Button keyWordButton = new Button("Edit Keywords");
		keyWordButton.setOnAction(e -> controller.editKeyWordAction());
		HBox top = new HBox();
		HBox.setHgrow(keyWordsTextField, Priority.ALWAYS);
		top.setSpacing(5);
		top.getChildren().addAll(keyWordsTextField, keyWordButton);
		center.add(top, 0, 0, 3, 1);
		
		messageList.setOnMouseClicked(e->controller.updateControls());
		
		center.setPadding(new Insets(5));
		center.setHgap(5);
		center.setVgap(5);
		center.add(feedList, 0, 1);
		center.add(messageList, 1, 1);
		center.add(messageText, 2, 1);
		
		
		Button addFeedButton = new Button("add");
		Button editFeedButton = new Button("edit");
		Button removeFeedButton = new Button("remove");
		addFeedButton.setOnAction(e -> controller.addFeedAction());
		editFeedButton.setOnAction(e -> controller.editFeedsAction());
		removeFeedButton.setOnAction(e -> controller.removeFeedAction());
		HBox feedButtons = new HBox();
		feedButtons.setSpacing(5);
		feedButtons.getChildren().addAll(addFeedButton, editFeedButton, removeFeedButton);
		
		center.add(feedButtons, 0, 2);
				
		Button searchButton = new Button("Search");
		searchButton.setDefaultButton(true);
		searchButton.setOnAction(e -> controller.searchAction());
		GridPane.setHalignment(searchButton, HPos.RIGHT);
		center.add(searchButton, 2, 2);
		controller.updateControls();
	}

	private class Controller {
		public Stage primaryStage;
		private KeyWordDialog keyWordDialog;
		
		public void updateControls() {
			keyWordsTextField.setText(String.join("; ", Service.getKeyWords()));
			feedList.getItems().setAll(Service.getUrls());
			
			Message selectedMessage = messageList.getSelectionModel().getSelectedItem();
			if (selectedMessage != null) {
				StringBuilder sb = new StringBuilder();
				sb.append(selectedMessage.getTitle()); sb.append("\n");
				sb.append(selectedMessage.getLink()); sb.append("\n");
				sb.append(selectedMessage.getDescription());
				messageText.setText(sb.toString());
			} else
				messageText.setText(null);
		}
		
		public void editKeyWordAction() {
			if (keyWordDialog == null) {
				keyWordDialog = new KeyWordDialog();
				keyWordDialog.initOwner(primaryStage);
			} else
				keyWordDialog.reset();
			
			keyWordDialog.showAndWait();
			updateControls();
		}
		
		public void addFeedAction() {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Add Feed Dialog");
			dialog.setHeaderText("Enter url to a new rssfeed");
			dialog.setContentText(null);

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
			    Service.addUrl(result.get());
				updateControls();
			}

		}
		
		public void editFeedsAction() {
			String selectedFeed = feedList.getSelectionModel().getSelectedItem();
			if (selectedFeed != null) {
				TextInputDialog dialog = new TextInputDialog(selectedFeed);
				dialog.setTitle("Edit Feed Dialog");
				dialog.setHeaderText("Edit url to this rssfeed");
				dialog.setContentText(null);

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    Service.updateUrl(selectedFeed, result.get());
					updateControls();
				}
			}
		}
		
		public void removeFeedAction() {
			String selectedFeed = feedList.getSelectionModel().getSelectedItem();
			if (selectedFeed != null) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("Delete " + selectedFeed + "?");
				alert.setContentText(null);
	
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					Service.removeUrl(selectedFeed);
					updateControls();
				}
			}
		}
		
		public void searchAction() {
			List<Message> messages = Service.searchFeeds();
			Collections.sort(messages);
			Collections.reverse(messages);
			messageList.getItems().setAll(messages);
		}
	}
}
