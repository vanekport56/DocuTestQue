package com.example.documentsTestQue.repositories;

import com.example.documentsTestQue.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("SELECT COUNT(d) FROM Document d")//не успел реализовать, удалять страшно, на отладку нет времени
    long countDocuments();
    List<Document> findByTitle(String title);
    Optional<Document> findTopByOrderByIdDesc();

}
