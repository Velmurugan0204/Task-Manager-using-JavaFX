package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import application.LoginController;
import application.unp;

public class updateController implements Initializable{

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
	    private TableColumn<Task,String>statusColumn;

	  
	    // You can add more TableColumn fields for additional properties if needed
	    
	    @FXML
	    public Label welcomelabel;
	    
	    @FXML
	    public TextField tname;
	    @FXML
	    public DatePicker tdate;
	    @FXML
	    public TextField ttime;
	    @FXML
	    public ComboBox<String> tcategory;
	    @FXML
	    public TextArea tdescription;
	    @FXML
	    public ComboBox<String> tstatus;
	    @FXML
		  public Button updateButton;
	    @FXML
	    private void initializeTableView() {
	        List<Task> tasks = getTasks();
	        taskTableView.getItems().addAll(tasks);

	        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
	        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
	        dueTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dueTime"));
	        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
	        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

	        taskTableView.setOnMouseClicked(event -> {
	            if (event.getClickCount() == 1) {
	                Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
	                if (selectedTask != null) {
	                    tdate.setValue(selectedTask.getDueDate());
	                    ttime.setText(selectedTask.getDueTime().toString());
	                    tcategory.setValue(selectedTask.getCategory());
	                    tdescription.setText(selectedTask.getDescription());
	                    tname.setText(selectedTask.getTaskName());
	                    comboBoxClicked = true;
	                    tstatus.setValue(selectedTask.getStatus());
	                    comboBoxClicked = false;
	                    
	                   // tstatus.setValue(selectedTask.getStatus());
	                }
	            }
	        });
	    }

	    private List<Task> getTasks() {
	        List<Task> tasks = new ArrayList<>();
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/TaskManager", "root", "Murugan@02**")) {
	            int userId = unp.ID;
	            String query = "SELECT * FROM task WHERE UserId=? AND (DueDate > CURRENT_DATE()) AND Status='Incompleted' ORDER BY DueDate";

	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setInt(1, userId);
	                ResultSet resultSet = preparedStatement.executeQuery();

	                while (resultSet.next()) {
	                    int taskId = resultSet.getInt("taskId");
	                    String taskName = resultSet.getString("taskName");
	                    LocalDate dueDate = resultSet.getDate("dueDate").toLocalDate();
	                    LocalTime dueTime = resultSet.getTime("dueTime").toLocalTime();
	                    String category = resultSet.getString("category");
	                    String description = resultSet.getString("description");
	                    String status = resultSet.getString("Status");

	                    Task task = new Task(taskId, taskName, dueDate, dueTime, category, description, status);
	                    tasks.add(task);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return tasks;
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
	public void GotoAddTask(MouseEvent e) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Add_task.fxml"));
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
	public void GotoUpdateTasks(MouseEvent e) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("update.fxml"));
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

	
	
	private void populateCategoryComboBox() {
	    try {int c=unp.ID;
	        Class.forName("com.mysql.jdbc.Driver");
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/TaskManager", "root", "Murugan@02**")) {
	            String selectCategoryQuery = "SELECT CategoryName FROM category WHERE UserId=?";

	            try (PreparedStatement preparedStatement = connection.prepareStatement(selectCategoryQuery)) {
	            	preparedStatement.setInt(1,c);
	                ResultSet resultSet = preparedStatement.executeQuery();

	                // Clear existing items
	                tcategory.getItems().clear();

	                // Add new items from the result set
	                while (resultSet.next()) {
	                    String categoryName = resultSet.getString("CategoryName");
	                    tcategory.getItems().add(categoryName);
	                }
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	private boolean comboBoxClicked=false;
	private void populateStatusComboBox() {
	    tstatus.getItems().clear(); // Clear existing items
	    // Assuming you have a method to get the status value from the database based on the selected task
	    String statusFromDatabase = getStatusFromDatabase(); 
	    tstatus.getItems().addAll(statusFromDatabase, "Completed");
	}
	
	
	
	private String getStatusFromDatabase() {
	    // Implement the logic to retrieve the status value from the database based on the selected task
	    return "Incompleted"; // Replace this with the actual logic
	}
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
		 populateCategoryComboBox();
		  //  populateStatusComboBox();
		    initializeTableView();
		    
		    

		    tstatus.setOnAction(event -> {
		        // If the ComboBox is clicked, show the options "Completed" and "Incompleted"
		        if (comboBoxClicked) {
		            populateStatusComboBox();
		        }
		    });

		   
		    
		    updateButton.setOnMouseClicked(event -> {
		        Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
		        if (selectedTask != null) {
		            updateTaskStatus(selectedTask.getTaskId(), tstatus.getValue(),tcategory.getValue(),tname.getText(),tdate.getValue().toString(),ttime.getText(),tdescription.getText());
		            
		            // Refresh the TableView after the update
		            taskTableView.getItems().setAll(getTasks());
		        }
		    });
		}

		private void updateTaskStatus(int taskId, String newStatus,String newcategory,String newtname,String localDate,String newtime,String newdes) {
		    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/TaskManager", "root", "Murugan@02**")) {
		        String query = "UPDATE task SET Status=?,Category=?,DueTime=?,DueDate=?,Description=?,TaskName=? WHERE taskId=?";
		        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		           
		        	preparedStatement.setString(1, newStatus);
		        	preparedStatement.setString(2, newcategory);
		        	preparedStatement.setString(3, newtime);
		        	preparedStatement.setDate(4, Date.valueOf(localDate));
		        	preparedStatement.setString(5, newdes);
		        	preparedStatement.setString(6, newtname);
		            preparedStatement.setInt(7, taskId);
		            preparedStatement.executeUpdate();
		        }
		    } catch (SQLException e) {
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
}

//	}
//
//		
//		
	

	
	
	
	

