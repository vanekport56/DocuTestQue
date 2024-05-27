package com.example.documentsTestQue.models;

import jakarta.persistence.*;



@Entity
@DiscriminatorValue("Service")
public class DocumentOfService extends Document {//Реализация услуг

}