package net.achraf.patientapp;

import net.achraf.patientapp.entities.Patient;
import net.achraf.patientapp.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientAppApplication.class, args);
    }
    //@Bean
    public CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            Patient p1=new Patient();
            p1.setNom("youssfi");
            p1.setPrenom("mohammed");
            p1.setDateNaissance(new Date());
            p1.setScore(550);

            Patient p2=new Patient(null,"Imane","Imane",new Date(),false,234);

            Patient p3=Patient.builder()
                    .nom("Achraf")
                    .prenom("Achraf")
                    .score(125)
                    .build();

            patientRepository.save(p1);
            patientRepository.save(p2);
            patientRepository.save(p3);

            patientRepository.findAll().forEach(patient -> {
                System.out.println(patient.toString());
            });
        };

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
