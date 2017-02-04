package dk.ninjabear.rssscanner;

import java.util.List;

import dk.ninjabear.rssscanner.model.Feed;
import dk.ninjabear.rssscanner.model.Message;
import dk.ninjabear.rssscanner.service.Service;
import dk.ninjabear.rssscanner.test.StorageTest;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * @author asbjoern1982@gmail.com
 * @see http://www.vogella.com/tutorials/RSSFeed/article.html
 */
public class RSSScannerMain extends Application {
	private final Controller controller = new Controller();
	
	public static void main(String[] args) {
		StorageTest.initStorage();
		
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("RSS Scanner");
		GridPane root = new GridPane();
		initContent(root);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	private final TextField keyWordsTextField = new TextField();
	private final ListView<Message> messageList = new ListView<>();
	private final TextArea messageText = new TextArea();
	
	private void initContent(GridPane root) {
		root.setHgap(5);
		root.setVgap(5);
		root.setPadding(new Insets(10));
		root.add(keyWordsTextField, 0, 0, 2, 1);
		root.add(messageList, 0, 1);
		root.add(messageText, 1, 1);
		
		Button editFeedButton = new Button("Edit Feeds");
		editFeedButton.setOnAction(e -> controller.editFeedsAction());
		GridPane.setHalignment(editFeedButton, HPos.LEFT);
		root.add(editFeedButton, 0, 2);
		
		Button searchButton = new Button("Search");
		searchButton.setDefaultButton(true);
		searchButton.setOnAction(e -> controller.searchAction());
		GridPane.setHalignment(searchButton, HPos.RIGHT);
		root.add(searchButton, 1, 2);
		
		controller.updateControls();
	}

	private class Controller {
		public void updateControls() {
			keyWordsTextField.setText(String.join("; ", Service.getKeyWords()));
		}
		
		public void editFeedsAction() {
			
		}
		
		public void searchAction() {
			String[] keywords = keyWordsTextField.getText().split(",");
			List<Message> messages = Service.searchFeeds();
			messageList.getItems().setAll(messages);
		}
	}
}
