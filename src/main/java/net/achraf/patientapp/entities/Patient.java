package net.achraf.patientapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Table(name = "PATIENTS")
@NoArgsConstructor@AllArgsConstructor@Getter@Setter@Builder@ToString
public class Patient {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotEmpty
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  dateNaissance;
    private boolean malade;
    @DecimalMin("100")
    private int score;

}
