package fxtray.demo;

import com.dustinredmond.fxtrayicon.FXTrayIcon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("FXTrayIcon  demo");
		
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        
        // Declaring all GUI elements
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Button closeApplication = new Button("Exit!");
        TextField textField = new TextField();
        Button showTrayIcon = new Button("Show FXTrayIcon");
        Button hideTrayIcon = new Button("hide FXTrayIcon");
        Button showErrorMessage = new Button("Error");
        Button showInfoMessage = new Button("Info");
        Button showWarningMessage = new Button("Warning");
        Button showMessage = new Button("Message");
               
        // Layout
        VBox box = new VBox();
        box.getChildren().add(l);
        box.getChildren().add(closeApplication);
        box.getChildren().add(textField);
        box.getChildren().add(showTrayIcon);
        box.getChildren().add(hideTrayIcon);
        box.getChildren().add(showErrorMessage);
        box.getChildren().add(showInfoMessage);
        box.getChildren().add(showWarningMessage);
        box.getChildren().add(showMessage);
        
        // Actions
        FXTrayIcon icon = new FXTrayIcon(primaryStage, getClass().getResource("/Icon.png"));
        icon.setTrayIconTooltip("This is AWT helping out JavaFX.");
        MenuItem fxMenuItem = new MenuItem("Say Hello from Tray");
        icon.addMenuItem(fxMenuItem);
        fxMenuItem.setOnAction(event->textField.setText("Hello from Sys Tray"));
        
        icon.setOnAction(evt->textField.setText("Double click on icon received."));
        icon.setOnClick(evt->textField.setText("Got a single click."));
        
        primaryStage.setOnCloseRequest(event->icon.hide());
        showTrayIcon.setOnAction(evt->icon.show());
        hideTrayIcon.setOnAction(evt->icon.hide());
        showErrorMessage.setOnAction(evt->icon.showErrorMessage("Error!", "A message or a stack trace\n or something else."));
        showInfoMessage.setOnAction(evt->icon.showInfoMessage("Info", "Some info"));
        showWarningMessage.setOnAction(evt->icon.showWarningMessage("Warning", "Something is wrong.\nCannot tell here!"));
        showMessage.setOnAction(evt->icon.showMessage("Message", "Two SQL developers meet in a bar.\nUnfortunately they left\nas there were no tables."));
        
        closeApplication.setOnAction(evt->{
        	if (icon.isShowing())
        		icon.hide();
        	primaryStage.close();
        });
        
        Scene scene = new Scene(box, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
	}	

	public static void main(String[] args) {
        launch();
    }
}
