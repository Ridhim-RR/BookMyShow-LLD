package com.example.book_my_show.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import net.sf.jsqlparser.util.validation.Validation;

@Data
@Entity
public class Payments extends BaseModel{
    @ManyToOne
    private Ticket ticket;
    private String txn;
    private  double amt;
    @Enumerated(value = EnumType.ORDINAL)
    private paymentStatus status;
}
