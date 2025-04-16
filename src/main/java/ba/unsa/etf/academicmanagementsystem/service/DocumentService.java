package ba.unsa.etf.academicmanagementsystem.service;

import ba.unsa.etf.academicmanagementsystem.model.Document;
import ba.unsa.etf.academicmanagementsystem.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAllDocuments();
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findDocumentById(id);
    }

    public Document getDocumentByFileName(String fileName) {
        return documentRepository.findByFileName(fileName);
    }

    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteDocumentById(id);
    }
}