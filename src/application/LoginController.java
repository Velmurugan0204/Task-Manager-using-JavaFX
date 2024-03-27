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
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.Node;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import application.unp;
import java.sql.*;
import java.util.*;


public class LoginController implements Initializable{
	

	// Event Listener on Hyperlink[#si_createAccount].onAction
	@FXML
	   public TextField userInput;
	   @FXML
	   public PasswordField userPassword;
	 //  public Button enter;
	   
	   public Label label;
	   public Label errorlabel;
	   
	  	public void initialize(URL arg0, ResourceBundle arg1) {
	  		// TODO Auto-generated method stub
	  		
	  		userInput.setOnKeyPressed(event->{
	  			if(event.getCode()==KeyCode.ENTER) {
	  				userPassword.requestFocus();
	  			}
	  		});
	  		userPassword.setOnKeyPressed(event->{
	  			if(event.getCode()==KeyCode.ENTER) {
	  				
	  			}
	  		});
	  		
	  	}
	   
	   
	   
	   
	   @FXML
		public String username;
		public String password;
		
		@FXML
		public void enter(MouseEvent e) throws IOException {
			username=userInput.getText();
			password=userPassword.getText();
			if(username.isEmpty()|| password.isEmpty()) {System.out.println("no empty fields");}
			else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					try(Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/TaskManager","root","Murugan@02**")){
						String insertDataQuery="SELECT * from user WHERE UserName=? AND Password =?";
						
						try(PreparedStatement insertDataStatement=connection.prepareStatement(insertDataQuery)){
							insertDataStatement.setString(1,username);
							insertDataStatement.setString(2,password);
							
							ResultSet resultSet=insertDataStatement.executeQuery();
							if(resultSet.next()) {
								String insertDataQuery1="SELECT UserId from user WHERE UserName=?";
								try(PreparedStatement insertDataStatement1=connection.prepareStatement(insertDataQuery1)){
									insertDataStatement1.setString(1,username);
									
									ResultSet resultSet1=insertDataStatement1.executeQuery();
									if(resultSet1.next()) {
									int users=resultSet1.getInt("UserId");
								System.out.println(users);
								//unp u1=new unp();
								//u1.setID(users);
								try {
									unp.ID=users;
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								
									}else {
										errorlabel.setText("Invalid user");
										System.out.println("Invalid user");}
								
								
								
								System.out.println("Login");
								FXMLLoader loader=new FXMLLoader(getClass().getResource("mainScene.fxml"));
								Parent root=loader.load();
								
								Scene scene=new Scene(root);
								Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
								stage.setScene(scene);
								stage.show();
								
							}}else {
								userInput.clear();
								userPassword.clear();
								
								errorlabel.setText("Invalid Credentials");
								System.out.println("Invalid Credentials");
								
							}
//							insertDataStatement.executeUpdate();
//							System.out.println("Data inserted");
							
						}
					}
					
				}catch(SQLException|ClassNotFoundException ev) {
					ev.printStackTrace();
				}			
			}
			
			
		}
	   
	   
	   
	   
	   
		
	
	
	
	
	
	
	
	
	@FXML
	public void GotoRegister(MouseEvent e) throws IOException {
		
		FXMLLoader loader=new FXMLLoader(getClass().getResource("register.fxml"));
		Parent root=loader.load();
		
		Scene scene=new Scene(root);
		Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();	
		
	}
	
//	@FXML
//	public void GotoMainScene(MouseEvent e)throws IOException {
//		
//		System.out.println("hello World");
//		FXMLLoader loader=new FXMLLoader(getClass().getResource("mainScene.fxml"));
//		Parent root=loader.load();
//	
//		Scene scene=new Scene(root);
//		Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
//		stage.setScene(scene);
//	stage.show();
//	}
	
}
