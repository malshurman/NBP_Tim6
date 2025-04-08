package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT * FROM NBP_DOCUMENTS", nativeQuery = true)
    List<Document> findAllDocuments();

    @Query(value = "SELECT * FROM NBP_DOCUMENTS WHERE FILE_NAME = ?1", nativeQuery = true)
    Document findByFileName(String fileName);

    @Query(value = "SELECT * FROM NBP_DOCUMENTS WHERE ID = ?1", nativeQuery = true)
    Document findDocumentById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_DOCUMENTS WHERE ID = ?1", nativeQuery = true)
    void deleteDocumentById(Long id);
}