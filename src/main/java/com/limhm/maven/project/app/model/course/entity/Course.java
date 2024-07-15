package com.limhm.maven.project.app.model.course.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class Course {

  private long id;

  private String name;

  private String category;

  @Min(value = 1, message = "A course should have a minimum of 1 rating")
  @Max(value = 5, message = "A course should have a maximum of 5 rating")
  private int rating;

  private String description;

  public Course() {
  }

  public Course(long id, String name, String category, int rating, String description) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.rating = rating;
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public int getRating() {
    return rating;
  }

  public String getDescription() {
    return description;
  }
}
