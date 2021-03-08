package isen.project.model.entities;

/**
 * Category of person (Work, family, friends ...)
 */
public class Category {

    private int id;
    private String name;


    public Category() {
        this.id = 1;
        this.name = "Unknown";
    }

    public Category(int categoryId) {
    }

    public Category(int category_id, String category_name) {
        this.id = category_id;
        this.name = category_name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
