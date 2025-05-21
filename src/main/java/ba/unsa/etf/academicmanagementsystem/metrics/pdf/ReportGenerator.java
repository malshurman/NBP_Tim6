package ba.unsa.etf.academicmanagementsystem.metrics.pdf;

import ba.unsa.etf.academicmanagementsystem.metrics.dto.StudentCourseMetricDto;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class ReportGenerator {

    public static byte[] exportMetricsToPdf(List<StudentCourseMetricDto> metrics) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        // Add Title
        Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Paragraph title = new Paragraph("Student Course Metrics Report", titleFont);
        document.add(title);
        document.add(Chunk.NEWLINE);

        // Table setup: 11 columns
        PdfPTable table = new PdfPTable(11);
        table.setWidthPercentage(100);

        // Set equal column widths (or customize)
        table.setWidths(new float[]{2, 3, 3, 2, 5, 2, 2, 2, 2, 2, 2});

        // Header styling
        Font headerFont = new Font(Font.HELVETICA, 10, Font.BOLD, Color.WHITE);
        Color headerBg = new Color(60, 90, 170);

        String[] headers = {
                "Student ID", "First Name", "Last Name", "Course ID", "Course Name",
                "Credits", "Grades", "Total", "Average", "Min", "Max"
        };
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(headerBg);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        table.setHeaderRows(1);

        // Data
        Font dataFont = new Font(Font.HELVETICA, 9, Font.NORMAL);
        for (StudentCourseMetricDto m : metrics) {
            table.addCell(new Phrase(String.valueOf(m.getStudentId()), dataFont));
            table.addCell(new Phrase(nvl(m.getFirstName()), dataFont));
            table.addCell(new Phrase(nvl(m.getLastName()), dataFont));
            table.addCell(new Phrase(String.valueOf(m.getCourseId()), dataFont));
            table.addCell(new Phrase(nvl(m.getCourseName()), dataFont));
            table.addCell(new Phrase(String.valueOf(m.getCredits()), dataFont));
            table.addCell(new Phrase(String.valueOf(m.getNumGrades()), dataFont));
            table.addCell(new Phrase(m.getTotalGrade() == null ? "" : String.format("%.2f", m.getTotalGrade()), dataFont));
            table.addCell(new Phrase(m.getAvgGrade() == null ? "" : String.format("%.2f", m.getAvgGrade()), dataFont));
            table.addCell(new Phrase(m.getMinGrade() == null ? "" : String.format("%.2f", m.getMinGrade()), dataFont));
            table.addCell(new Phrase(m.getMaxGrade() == null ? "" : String.format("%.2f", m.getMaxGrade()), dataFont));
        }

        document.add(table);
        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    // Null value utility
    private static String nvl(String s) {
        return s == null ? "" : s;
    }
}