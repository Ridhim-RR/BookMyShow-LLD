package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class User extends BaseModel{
    String name;
    String email;
    Integer phone;

}
