package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Theatres extends BaseModel {
private String name;
private String address;
@OneToMany(mappedBy = "theatre")
private List<Screen> screen;
@ManyToOne
private City city;
}
