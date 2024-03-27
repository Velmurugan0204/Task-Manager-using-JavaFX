package application;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private final IntegerProperty taskIdProperty = new SimpleIntegerProperty();
    private final StringProperty taskNameProperty = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> dueDateProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> dueTimeProperty = new SimpleObjectProperty<>();
    private final StringProperty categoryProperty = new SimpleStringProperty();
    private final StringProperty descriptionProperty = new SimpleStringProperty();
    private final StringProperty statusProperty = new SimpleStringProperty();
    
    public Task() {
    }

    public Task(int taskId, String taskName, LocalDate dueDate, LocalTime dueTime, String category, String description,String status) {
        this.taskIdProperty.set(taskId);
        this.taskNameProperty.set(taskName);
        this.dueDateProperty.set(dueDate);
        this.dueTimeProperty.set(dueTime);
        this.categoryProperty.set(category);
        this.descriptionProperty.set(description);
        this.statusProperty.set(status);
        
    }

    public int getTaskId() {
        return taskIdProperty.get();
    }

    public void setTaskId(int taskId) {
        this.taskIdProperty.set(taskId);
    }

    public IntegerProperty taskIdProperty() {
        return taskIdProperty;
    }

    public String getTaskName() {
        return taskNameProperty.get();
    }

    public void setTaskName(String taskName) {
        this.taskNameProperty.set(taskName);
    }

    public StringProperty taskNameProperty() {
        return taskNameProperty;
    }

    public LocalDate getDueDate() {
        return dueDateProperty.get();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDateProperty.set(dueDate);
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDateProperty;
    }

    public LocalTime getDueTime() {
        return dueTimeProperty.get();
    }

    public void setDueTime(LocalTime dueTime) {
        this.dueTimeProperty.set(dueTime);
    }

    public ObjectProperty<LocalTime> dueTimeProperty() {
        return dueTimeProperty;
    }

    public String getCategory() {
        return categoryProperty.get();
    }

    public void setCategory(String category) {
        this.categoryProperty.set(category);
    }

    public StringProperty categoryProperty() {
        return categoryProperty;
    }
    
    public void setStatus(String status) {
        this.statusProperty.set(status);
    }
    public String getStatus() {
    	return this.statusProperty.get();
    }
    public StringProperty statusProperty() {
        return statusProperty;
    }

    public String getDescription() {
        return descriptionProperty.get();
    }

    public void setDescription(String description) {
        this.descriptionProperty.set(description);
    }

    public StringProperty descriptionProperty() {
        return descriptionProperty;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskIdProperty.get() +
                ", taskName='" + taskNameProperty.get() + '\'' +
                ", dueDate=" + dueDateProperty.get() +
                ", dueTime=" + dueTimeProperty.get() +
                ", category='" + categoryProperty.get() + '\'' +
                ", description='" + descriptionProperty.get() + '\'' +
                ", status='"+statusProperty.get()+'\''+
                '}';
    }
}
