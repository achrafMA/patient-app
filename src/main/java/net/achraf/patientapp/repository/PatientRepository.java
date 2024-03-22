package net.achraf.patientapp.repository;

import net.achraf.patientapp.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(String nom,String prenom,Pageable pageable);
}
