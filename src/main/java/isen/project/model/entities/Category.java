package isen.project.model.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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
