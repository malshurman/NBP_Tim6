package ba.unsa.etf.academicmanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull(message = "File name cannot be null")
    @Size(max = 255, message = "File name must be at most 255 characters")
    private String fileName;

    @NotNull(message = "File path cannot be null")
    @Size(max = 500, message = "File path must be at most 500 characters")
    private String filePath;

    @NotNull(message = "Upload date cannot be null")
    private LocalDateTime uploadDate;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Uploaded by cannot be null")
    private Long uploadedBy;
}