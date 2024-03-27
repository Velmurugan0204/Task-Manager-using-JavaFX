package application;


import java.io.IOException;
import javafx.application.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.Node;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Add_taskController implements Initializable {
    


	
	@FXML
	public void GotoCategory(MouseEvent event) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("category.fxml"));
		Parent root=loader.load();
		
		Scene scene=new Scene(root);
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
    	 
	
	@FXML
	public void GotoMyTasks(MouseEvent e) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("mainScene.fxml"));
		Parent root=loader.load();
		
		Scene scene=new Scene(root);
		Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void GotoTaskHistory(MouseEvent event) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("taskHistory.fxml"));
		Parent root=loader.load();
		
		Scene scene=new Scene(root);
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void GotoAddTask(MouseEvent e) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Add_task.fxml"));
		Parent root=loader.load();
		
		Scene scene=new Scene(root);
		Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	  @FXML
			public void GotoUpdateTasks(MouseEvent e) throws IOException {
				FXMLLoader loader=new FXMLLoader(getClass().getResource("update.fxml"));
				Parent root=loader.load();
				
				Scene scene=new Scene(root);
				Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
	
	
	@FXML
	TextField taskname;
	@FXML
	DatePicker duedate;
	@FXML
	TextField duetime;
	   @FXML
	    public ComboBox<String> categorys;
	@FXML
	TextArea description;
	
    @FXML
    public Label welcomelabel;
	
	 @FXML
	 public String Task;
	 public Date dueDate;
	 public Time time;
	 public String category;
	 public String des;
	 
	// Assuming duedate is your DatePicker
	 @FXML
	 LocalDate localDate ;

	 // Convert LocalDate to Date
	 @FXML
     public void enter() {
    	 Task=taskname.getText();
    	localDate=duedate.getValue();
    	 time=Time.valueOf(duetime.getText());
    	// String category = categorynames.getValue().toString();
    	 String category=categorys.getValue().toString();
    	 des=description.getText();
    	 
    	 
    	 if(Task.isEmpty()|| localDate==null|| time==null ||  des.isEmpty()) {System.out.println("Invalid category name");}
    	 else {
    		 try {
 				Class.forName("com.mysql.jdbc.Driver");
 				try(Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/TaskManager","root","Murugan@02**")){
 				    String categoryid="SELECT CategoryId FROM category WHERE CategoryName = ?";
 					try(PreparedStatement categoryIdStatement = connection.prepareStatement(categoryid)){
 						categoryIdStatement.setString(1, category);
 						 try (ResultSet resultSet = categoryIdStatement.executeQuery()) {
 		                    if (resultSet.next()) {
 		                        
 		                        int categoryId = resultSet.getInt("CategoryId");

 					
 					
 					
 					String insertDataQuery="Insert INTO task (TaskName,DueDate,DueTime,Category,Description,UserId,CategoryId)VALUES(?,?,?,?,?,?,?)";
 					int a=unp.ID; 
 					System.out.println(a);
 					try(PreparedStatement insertDataStatement=connection.prepareStatement(insertDataQuery)){
 				
 						insertDataStatement.setString(1,Task);
 						insertDataStatement.setDate(2,Date.valueOf(localDate));
 						insertDataStatement.setTime(3,time);
 						insertDataStatement.setString(4,category);
 						insertDataStatement.setString(5,des);
 					    insertDataStatement.setInt(6,a);
 					   insertDataStatement.setInt(7,categoryId);
 						insertDataStatement.executeUpdate();
 						
                      
 					showAlert("Success","Task added successfully");
 						System.out.println("Data inserted");
 						
 					}
 				}else{System.out.println("Category doesn't exist");}
 				
 			}
 					}
 				}
    		 }
 				catch(SQLException|ClassNotFoundException e) {
 				e.printStackTrace();
 			}			
 		}	
	
	
     }


	 

		


	private void populateCategoryComboBox() {
		// TODO Auto-generated method stub
		  try {
	            Class.forName("com.mysql.jdbc.Driver");
	            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/TaskManager", "root", "Murugan@02**")) {
	               int m=unp.ID;
	            	String selectCategoriesQuery = "SELECT CategoryName FROM category where UserId=?";

	                try (PreparedStatement preparedStatement = connection.prepareStatement(selectCategoriesQuery)) {
	                	preparedStatement.setInt(1,m);
	                	ResultSet resultSet = preparedStatement.executeQuery();

	                    // Clear existing items
	                    categorys.getItems().clear();

	                    // Add new items from the result set
	                    while (resultSet.next()) {
	                        String categoryName = resultSet.getString("CategoryName");
	                        categorys.getItems().add(categoryName);
	                        
	                    }
	                }
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		
		
	}

	  @FXML
		public void GotoLogout(MouseEvent e) throws IOException {
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


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		populateCategoryComboBox();
	}
}
	
	
	
	

