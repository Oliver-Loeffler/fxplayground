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
        Button showErrorMessage = new Button("Show Message in FXTray");
               
        // Layout
        VBox box = new VBox();
        box.getChildren().add(l);
        box.getChildren().add(closeApplication);
        box.getChildren().add(textField);
        box.getChildren().add(showTrayIcon);
        box.getChildren().add(hideTrayIcon);
        box.getChildren().add(showErrorMessage);
                
        // Actions
        FXTrayIcon icon = new FXTrayIcon(primaryStage, getClass().getResource("/Icon.png"));
        primaryStage.setOnCloseRequest(event->icon.hide());
        showTrayIcon.setOnAction(evt->{
        	  
              MenuItem fxMenuItem = new MenuItem("Say Hello from Tray");
              fxMenuItem.setOnAction(event->{
              	textField.setText("Hello from Sys Tray");
              });
              
              icon.addMenuItem(fxMenuItem);
              icon.show();
        });
        
        hideTrayIcon.setOnAction(evt->icon.hide());
        showErrorMessage.setOnAction(evt->icon.showErrorMessage("Error!", "A message or a stack trace\n or something else."));
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
