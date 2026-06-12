package com.sitaf.backend.controller;

import com.sitaf.backend.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trazabilidad")
public class TrazabilidadController {

    @Autowired
    private BlockchainService blockchainService;

    @GetMapping("/lote/{lote}")
    public ResponseEntity<?> getHistorialLote(@PathVariable String lote) {
        try {
            String jsonHistorial = blockchainService.consultarHistorial(lote);
            return ResponseEntity.ok(jsonHistorial);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error consultando blockchain: " + e.getMessage());
        }
    }

    @PostMapping("/defecto/{lote}")
    public ResponseEntity<?> marcarLoteDefectuoso(@PathVariable String lote) {
        try {
            blockchainService.marcarLoteDefectuoso(lote);
            return ResponseEntity.ok("Lote marcado como defectuoso exitosamente en la blockchain.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al marcar lote: " + e.getMessage());
        }
    }

    @GetMapping("/pacientes-afectados/{lote}")
    public ResponseEntity<?> rastrearPacientesAfectados(@PathVariable String lote) {
        try {
            String jsonPacientes = blockchainService.rastrearPacientesAfectados(lote);
            return ResponseEntity.ok(jsonPacientes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error consultando pacientes afectados: " + e.getMessage());
        }
    }
}
