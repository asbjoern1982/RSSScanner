package dk.ninjabear.rssscanner.gui;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProgressDialog extends Stage {
		public ProgressDialog() {
			initModality(Modality.APPLICATION_MODAL);
			initStyle(StageStyle.UTILITY);
			setResizable(false);
			setTitle("scanning..");
			GridPane pane = new GridPane();
			initContent(pane);
			setScene(new Scene(pane));
		}

		private ProgressIndicator progressIndicator;
		
		public void initContent(GridPane pane) {
			pane.setPadding(new Insets(10));
			progressIndicator = new ProgressIndicator();
			progressIndicator.setProgress(-1F);
			pane.add(progressIndicator, 0, 0);
		}

		public void bindTask(final Task<?> task) {
			if (progressIndicator != null)
				progressIndicator.progressProperty().bind(task.progressProperty());
		}
		
		public void reset() {
			progressIndicator = new ProgressIndicator();
			progressIndicator.setProgress(-1F);
		}
}
