package main.netmonitor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.netmonitor.controller.ControllerInterface;
import org.jnetpcap.PcapIf;

import java.io.File;
import java.net.URL;

public class JavaFx extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		PcapIf device = null;

		URL url1 = new File("src/main/resources/fxml/interface.fxml").toURI().toURL();
		URL url2 = new File("src/main/resources/fxml/main.fxml").toURI().toURL();
		FXMLLoader fxmlLoaderInterface = new FXMLLoader(url1);
		FXMLLoader fxmlLoaderMain = new FXMLLoader(url2);
		Parent interfaces = fxmlLoaderInterface.load();
		Parent main = fxmlLoaderMain.load();
		ControllerInterface CtrlInterf = fxmlLoaderInterface.getController();


		device = CtrlInterf.getInterface();

		primaryStage.setTitle("Sniffer");
		// primaryStage.setScene(new Scene(interfaces));

		primaryStage.setScene(new Scene(main)); //
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
