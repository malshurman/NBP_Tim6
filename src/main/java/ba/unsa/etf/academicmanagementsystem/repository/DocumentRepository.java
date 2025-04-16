package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class DocumentRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Document> findAllDocuments() {
        String sql = "SELECT * FROM NBP_DOCUMENTS";
        return jdbcTemplate.query(sql, new DocumentMapper());
    }

    public Document findDocumentById(Long id) {
        String sql = "SELECT * FROM NBP_DOCUMENTS WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new DocumentMapper(), id);
    }

    public Document findByFileName(String fileName) {
        String sql = "SELECT * FROM NBP_DOCUMENTS WHERE FILE_NAME = ?";
        return jdbcTemplate.queryForObject(sql, new DocumentMapper(), fileName);
    }

    @Transactional
    public Document save(Document document) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (document.getId() == null) {
            String sql = "INSERT INTO NBP_DOCUMENTS (FILE_NAME, FILE_PATH, UPLOAD_DATE, COURSE_ID, UPLOADED_BY) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, document.getFileName());
                ps.setString(2, document.getFilePath());
                ps.setTimestamp(3, java.sql.Timestamp.valueOf(document.getUploadDate()));
                ps.setLong(4, document.getCourseId());
                ps.setLong(5, document.getUploadedBy());
                return ps;
            }, keyHolder);
            document.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP_DOCUMENTS SET FILE_NAME = ?, FILE_PATH = ?, UPLOAD_DATE = ?, COURSE_ID = ?, UPLOADED_BY = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    document.getFileName(),
                    document.getFilePath(),
                    document.getUploadDate(),
                    document.getCourseId(),
                    document.getUploadedBy(),
                    document.getId());
        }
        return document;
    }

    @Transactional
    public void deleteDocumentById(Long id) {
        String sql = "DELETE FROM NBP_DOCUMENTS WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class DocumentMapper implements RowMapper<Document> {
        @Override
        public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
            Document document = new Document();
            document.setId(rs.getLong("ID"));
            document.setFileName(rs.getString("FILE_NAME"));
            document.setFilePath(rs.getString("FILE_PATH"));
            document.setUploadDate(rs.getTimestamp("UPLOAD_DATE").toLocalDateTime());
            document.setCourseId(rs.getLong("COURSE_ID"));
            document.setUploadedBy(rs.getLong("UPLOADED_BY"));
            return document;
        }

    }
}