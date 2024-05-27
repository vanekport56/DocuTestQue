package com.example.documentsTestQue.services;

import com.example.documentsTestQue.models.Document;
import com.example.documentsTestQue.models.DocumentOfProducts;
import com.example.documentsTestQue.models.DocumentOfService;
import com.example.documentsTestQue.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    // Метод возвращает список всех документов
    public List<Document> listAllDocuments() {
        return documentRepository.findAll();
    }
    // Метод ищет документ по id , возвращает null если не найдет
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }
    //на половину реализованный метод, должен был генерить id если пользователь не введет его на форме, а так только сохраняет новый док
    public void saveDocument(Document document) {
        if (document.getId() == null) {
            Long maxId = documentRepository.findAll().stream()
                    .mapToLong(Document::getId)
                    .max()
                    .orElse(0L);
            document.setId(maxId + 1);
        }
        documentRepository.save(document);
    }
    //дальше все интуитивно понятно (должно быть)
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return documentRepository.existsById(id);
    }

    public List<Document> listDocument(String title) {
        return documentRepository.findByTitle(title);
    }

    public void saveDocumentOfProducts(DocumentOfProducts document) {
        saveDocument(document);
    }

    public void saveDocumentOfService(DocumentOfService document) {
        saveDocument(document);
    }
}