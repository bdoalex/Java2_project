package isen.project.model.entities;

import java.util.Objects;

/**
 * Category of person (Work, family, friends ...)
 */
public class Category {

    private int id;
    private String name;


    /**
     * Instantiates a new Category.
     */
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

    /**
     * Instantiates a new Category.
     *
     * @param category_id   the category id
     * @param category_name the category name
     */
    public Category(int category_id, String category_name) {
        this.id = category_id;
        this.name = category_name;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
