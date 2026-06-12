package com.sitaf.controller;

import com.sitaf.model.SismedItem;
import com.sitaf.service.SismedMockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/sismed")
public class SismedMockController {

    @Autowired
    private SismedMockService sismedMockService;

    @GetMapping("/stock")
    public List<SismedItem> getStock() {
        return sismedMockService.getCatalogAndStock();
    }

    @PostMapping("/orden-compra")
    public ResponseEntity<?> generarOrdenCompra(@RequestBody Map<String, Object> payload) {
        // Simulamos respuesta del SIGA devolviendo un nro de expediente
        String nroExpediente = "SIGA-EXP-" + (int)(Math.random() * 1000000);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Orden registrada exitosamente en SIGA");
        response.put("expediente", nroExpediente);
        return ResponseEntity.ok(response);
    }
}
