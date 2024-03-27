package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class taskHistoryController {

	// Event Listener on Button.onMouseClicked
	
	
	
	 @FXML
	    private TableView<Task> taskTableView;

	    @FXML
	    private TableColumn<Task, Integer> taskIdColumn;

	    @FXML
	    private TableColumn<Task, String> taskNameColumn;

	    @FXML
	    private TableColumn<Task, LocalDate> dueDateColumn;

	    @FXML
	    private TableColumn<Task, LocalTime> dueTimeColumn;

	    @FXML
	    private TableColumn<Task, String> categoryColumn;

	    @FXML
	    private TableColumn<Task, String> descriptionColumn;
	    
	    @FXML
	    private TableColumn<Task,String> statusColumn;

	  
	    // You can add more TableColumn fields for additional properties if needed
	    
	    @FXML
	    public Label welcomelabel;
	    @FXML
	    public Label tname;
	    @FXML
	    public DatePicker tdate;
	    @FXML
	    public TextField ttime;
	    @FXML
	    public Label tcategory;
	    @FXML
	    public TextArea tdescription;
	    @FXML
	    public String tstatus;
	    @FXML
	    private void initialize() {
	  
	    	
	    	
			
	    	
	    	
	    	
	        
	        List<Task> tasks = getTasks(); // Updated to use the new method
	        taskTableView.getItems().addAll(tasks);
	        LocalDate currentDate = LocalDate.now();
	        tasks.removeIf(task -> task.getDueDate().isBefore(currentDate));

	        
	      //  taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().taskIdProperty().asObject());
	        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());
	        dueDateColumn.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty());
	        dueTimeColumn.setCellValueFactory(cellData -> cellData.getValue().dueTimeProperty()); // Assuming dueTimeProperty exists in your Task class
	        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty()); // Assuming categoryProperty exists in your Task class
	    //   descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty()); 
	       statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
	      
	    
	    taskTableView.setOnMouseClicked(event->{
	    	 if(event.getClickCount()==1) {
	    		 Task selectedTask=taskTableView.getSelectionModel().getSelectedItem();
	    		 if(selectedTask!=null) {
	    			    
	    	            tdate.setValue(selectedTask.getDueDate());
	    	            ttime.setText(selectedTask.getDueTime().toString());
	    	            tcategory.setText(selectedTask.getCategory());
	    	            tdescription.setText(selectedTask.getDescription());
	    	            tname.setText(selectedTask.getTaskName().toString());
	    		 }
	    	 }
	    });
	    }

	    private List<Task> getTasks() {
//	        // You should implement a method to retrieve tasks from your data source (e.g., database)
//	        List<Task> tasks = new ArrayList<>();
//	        tasks.add(new Task(1, "Task 1", LocalDate.now(), LocalTime.now(), "Category 1", "Description 1"));
//	        tasks.add(new Task(2, "Task 2", LocalDate.now(), LocalTime.now(), "Category 2", "Description 2"));
//	        System.out.println(tasks);
//	        return tasks;
	        
	    	 List<Task> tasks = new ArrayList<>();
	        
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/TaskManager","root","Murugan@02**")) {

	        	int b=unp.ID;
	        	System.out.println(b);
	            // Assuming you have a table named 'tasks' with columns similar to Task class properties
	            //String query = "SELECT * FROM task where UserId=? order by DueDate";
	        	 String query = "SELECT * FROM task WHERE UserId=? AND (DueDate < CURRENT_DATE() OR Status='Completed') ORDER BY DueDate";
	        	
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
	            		preparedStatement.setInt(1,b);
	            		ResultSet resultSet = preparedStatement.executeQuery() ;

	                while (resultSet.next()) {
	                    int taskId = resultSet.getInt("taskId");
	                    String taskName = resultSet.getString("taskName");
	                    LocalDate dueDate = resultSet.getDate("dueDate").toLocalDate();
	                    LocalTime dueTime = resultSet.getTime("dueTime").toLocalTime();
	                    String category = resultSet.getString("category");
	                    String description = resultSet.getString("description");
                     String status=resultSet.getString("Status");
	                  //  int categid=resultSet.getString("CategoryId");
	                    
	                    // Create Task object and add to the list
	                    Task task = new Task(taskId,taskName, dueDate, dueTime, category, description,status);
	                    tasks.add(task);
	                }
	            
	        }  
	        
	        System.out.println(tasks.size());
	        String s=tasks.toString();
	        System.out.println(s);
	        return tasks;
	    } catch (SQLException e) {
         e.printStackTrace(); // Handle exceptions appropriately
     }
			return tasks; 
	    
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
    	
	// Event Listener on Button.onMouseClicked
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
		public void GotoLogout(MouseEvent e) throws IOException {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("scene1.fxml"));
			Parent root=loader.load();
			
			Scene scene=new Scene(root);
			Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}

	
}
