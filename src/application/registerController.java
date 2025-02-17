package application;

import java.io.IOException;
import javafx.application.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.Node;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class registerController implements Initializable{
	
   @FXML
   public TextField userInput;
   @FXML
   public PasswordField userPassword;
//   public Button enter;
   
   
   
  	public void initialize(URL arg0, ResourceBundle arg1) {
  		// TODO Auto-generated method stub
  		
  		userInput.setOnKeyPressed(event->{
  			if(event.getCode()==KeyCode.ENTER) {
  				userPassword.requestFocus();
  			}
  		});
  		userPassword.setOnKeyPressed(event->{
  			if(event.getCode()==KeyCode.ENTER) {
  				//enter();
  			}
  		});
  		
  	}
   
   
   
   
   @FXML
	public String username;
	public String password;
	
	@FXML
	public void enter() {
		username=userInput.getText();
		password=userPassword.getText();
		if(username.isEmpty()|| password.isEmpty()) {System.out.println("no empty fields");}
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				try(Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/TaskManager","root","Murugan@02**")){
					String insertDataQuery="Insert INTO user (UserName,Password)VALUES(?,?)";
					
					try(PreparedStatement insertDataStatement=connection.prepareStatement(insertDataQuery)){
						insertDataStatement.setString(1,username);
						insertDataStatement.setString(2,password);
						insertDataStatement.executeUpdate();
						showAlert("Success","Registered successfully!");
						System.out.println("Data inserted");
						
					}
				}
				
			}catch(SQLException|ClassNotFoundException e) {
				e.printStackTrace();
			}			
		}	
		
	}
   
   
   
   
   
   
  
   
   
   
   
   
   
	
	
	
	@FXML	
	public void GotoLogin(MouseEvent e)throws IOException {
		System.out.println("Hello world");
		FXMLLoader loader=new FXMLLoader(getClass().getResource("scene1.fxml"));
		Parent root=loader.load();
		
		Scene scene=new Scene(root);
		Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
		
	}
	 private void showAlert(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	
	
//	@FXML
//	public void GotoMainScene(MouseEvent e)throws IOException {
//		
//	
//		
//		
//		FXMLLoader loader=new FXMLLoader(getClass().getResource("mainScene.fxml"));
//		Parent root=loader.load();
//		
//		Scene scene=new Scene(root);
//		Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
//		stage.setScene(scene);
//		stage.show();
//		
//		
//	}
//	
	
	


	
	


	
}
