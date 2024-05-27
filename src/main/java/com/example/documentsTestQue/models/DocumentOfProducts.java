package com.example.documentsTestQue.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Products")
public class DocumentOfProducts extends Document {// Реализация товаров/Реализация товаров и услуг

}