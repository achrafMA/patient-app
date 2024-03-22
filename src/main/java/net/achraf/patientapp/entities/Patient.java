package net.achraf.patientapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Table(name = "PATIENTS")
@NoArgsConstructor@AllArgsConstructor@Getter@Setter@Builder@ToString
public class Patient {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nom;
    private String prenom;
    private Date  dateNaissance;
    private boolean malade;
    private int score;

}
