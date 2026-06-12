package com.sitaf.backend.config;

import com.sitaf.model.SismedItem;
import com.sitaf.backend.repository.SismedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private SismedItemRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.saveAll(Arrays.asList(
                new SismedItem("00123", "Paracetamol 500mg", 1500, "Almacén Central", "2026-12-31"),
                new SismedItem("00456", "Amoxicilina 500mg", 3000, "Farmacia Urgencias", "2026-07-15"),
                new SismedItem("00789", "Ibuprofeno 400mg", 800, "Almacén Secundario", "2028-05-10"),
                new SismedItem("01011", "Clonazepam 2mg", 120, "Armario Psicotrópicos", "2027-01-20"),
                new SismedItem("01213", "Omeprazol 20mg", 2500, "Almacén Central", "2026-06-30")
            ));
            System.out.println("Data Seeder: Inserted 5 initial records into PostgreSQL.");
        }
    }
}
