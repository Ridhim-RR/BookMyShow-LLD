package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class Show extends BaseModel{
    Date StartTime;
    Date EndTime;

}
