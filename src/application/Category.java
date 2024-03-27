package application;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
    private final StringProperty categoryNameProperty = new SimpleStringProperty();

    public Category(String categoryName) {
        this.categoryNameProperty.set(categoryName);
    }

    public String getCategoryName() {
        return categoryNameProperty.get();
    }

    public void setCategoryName(String categoryName) {
        this.categoryNameProperty.set(categoryName);
    }

    public StringProperty categoryNameProperty() {
        return categoryNameProperty;
    }
    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryNameProperty.get() + '\'' +
                '}';
    }
}
