package com.example.documentsTestQue.services;

import com.example.documentsTestQue.models.Document;
import com.example.documentsTestQue.models.DocumentOfProducts;
import com.example.documentsTestQue.models.DocumentOfService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;



@Component
public class DataInitializer {//класс который будет создавать по 15 документов-обьектов при запуске программы

    private final DocumentService documentService;

    public DataInitializer(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostConstruct
    public void initializeData() {
        Document[] doc = new Document[15]; //сомнительная конструкция, но не осуждайте
        for (int i = 0; i <= 14; i++) {
            doc[i] = new DocumentOfService();
            doc[i].setTitle("Реализация услуг");
            doc[i].setDescription("Услуги по доставке");
            doc[i].setPrice(200.0);
            doc[i].setAuthor("Автор");
            documentService.saveDocument((DocumentOfService) doc[i]);
            i++;

            doc[i] = new DocumentOfProducts();
            doc[i].setTitle("Реализация товаров");
            doc[i].setDescription("Продукты питания");
            doc[i].setPrice(100.0);
            doc[i].setAuthor("Автор");
            documentService.saveDocument((DocumentOfProducts) doc[i]);
            i++;

            doc[i] = new DocumentOfProducts();
            doc[i].setTitle("Реализация товаров и услуг");
            doc[i].setDescription("Продукты питания + доставка");
            doc[i].setPrice(200.0);
            doc[i].setAuthor("Автор");
            documentService.saveDocument((DocumentOfProducts) doc[i]);
        }
    }

}