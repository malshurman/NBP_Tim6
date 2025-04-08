package ba.unsa.etf.academicmanagementsystem.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "NBP_DOCUMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FILE_NAME", length = 255, nullable = false)
    @NotNull(message = "File name cannot be null")
    @Size(max = 255, message = "File name must be at most 255 characters")
    private String fileName;

    @Column(name = "FILE_PATH", length = 500, nullable = false)
    @NotNull(message = "File path cannot be null")
    @Size(max = 500, message = "File path must be at most 500 characters")
    private String filePath;

    @Column(name = "UPLOAD_DATE", nullable = false)
    @NotNull(message = "Upload date cannot be null")
    private LocalDateTime uploadDate;

    @Column(name = "COURSE_ID", nullable = false)
    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @Column(name = "UPLOADED_BY", nullable = false)
    @NotNull(message = "Uploaded by cannot be null")
    private Long uploadedBy;
}