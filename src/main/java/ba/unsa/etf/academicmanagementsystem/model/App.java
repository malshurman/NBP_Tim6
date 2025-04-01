package ba.unsa.etf.academicmanagementsystem.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "NBP_APPS")
@Data
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 10)
    private String appId;

    private Long managerId;

    private Date expiryDate;
}