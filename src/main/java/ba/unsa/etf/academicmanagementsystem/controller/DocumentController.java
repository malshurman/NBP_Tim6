// File: src/main/java/ba/unsa/etf/academicmanagementsystem/controller/DocumentController.java
package ba.unsa.etf.academicmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
@Tag(name = "Document Controller", description = "Endpoints for managing course documents and materials")
public class DocumentController {

    @Operation(summary = "Upload Document", description = "Uploads a course document and stores its metadata")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam("courseId") String courseId) {
        return ResponseEntity.ok("Document uploaded for course: " + courseId);
    }

    @Operation(summary = "Get Document", description = "Retrieves details or the content of a specific document")
    @GetMapping("/{documentId}")
    public ResponseEntity<String> getDocument(@PathVariable Long documentId) {
        return ResponseEntity.ok("Document details for: " + documentId);
    }

    @Operation(summary = "Search Documents", description = "Searches documents based on a query parameter")
    @GetMapping("/search")
    public ResponseEntity<String> searchDocuments(@RequestParam("query") String query) {
        return ResponseEntity.ok("Search results for documents with query: " + query);
    }
}