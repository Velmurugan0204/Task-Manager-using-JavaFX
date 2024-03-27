package application;

import java.io.IOException;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
//import java.util.Locale.Category;

public class categoryController {
@FXML
public Label welcomelabel;
	
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
		public void GotoCategory(MouseEvent event) throws IOException {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("category.fxml"));
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
     TextField CategoryName;
     
     
     @FXML
     public String category; 
     
    
     
     @FXML
     public void enter() {
    	 category=CategoryName.getText();
    	 if(category.isEmpty()) {System.out.println("Invalid category name");}
    	 else {
    		 try {int k=unp.ID;
 				Class.forName("com.mysql.jdbc.Driver");
 				try(Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/TaskManager","root","Murugan@02**")){
 					String insertDataQuery="Insert INTO category (CategoryName,UserId)VALUES(?,?)";
 					
 					try(PreparedStatement insertDataStatement=connection.prepareStatement(insertDataQuery)){
 						insertDataStatement.setString(1,category);
 						insertDataStatement.setInt(2,k);

 						insertDataStatement.executeUpdate();
 						
 						showAlert("Success","Category added successfully");
 						System.out.println("Data inserted");
 						
 					}
 					
 				}
 				
 			}catch(SQLException|ClassNotFoundException e) {
 				e.printStackTrace();
 			}			
 		}	
    		 
    		 
    		 
    	 }
 
     @FXML
     private TableView<Category> categoryTableView;

     @FXML
     private TableColumn<Category, String> categoryNameColumn;

     @FXML
     private void initialize() {
         List<Category> categories = getCategories();
         categoryTableView.getItems().addAll(categories);

         categoryNameColumn.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
         
         
        
         
     }

     private List<Category> getCategories() {

    	 List<Category> categories = new ArrayList<>();
	        
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/TaskManager","root","Murugan@02**")) {
	        	int c=unp.ID;
	           
	            String query = "SELECT CategoryName FROM category WHERE UserId=?";
	           
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            		preparedStatement.setInt(1,c);
            		ResultSet resultSet = preparedStatement.executeQuery() ;
	            
	              

	                while (resultSet.next()) {
	                  
	                    String CategoryName = resultSet.getString("categoryName");
	               

	                    // Create Task object and add to the list
	                    Category category = new Category( CategoryName);
	                    category.toString();
	                    categories.add(category);
	                }
	                }
	            }
	      
	            
	        catch (SQLException e) {
	            e.printStackTrace(); // Handle exceptions appropriately
	        }
	    
			return categories; 
			
     

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

}
     

