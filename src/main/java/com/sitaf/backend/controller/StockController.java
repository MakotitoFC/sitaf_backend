package com.sitaf.backend.controller;

import com.sitaf.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/siga/import")
    public ResponseEntity<?> importSigaData() {
        return ResponseEntity.ok(stockService.importSigaData());
    }

    @GetMapping("/prediccion/{codigo}")
    public ResponseEntity<?> getPredictStock(@PathVariable String codigo, @RequestParam(defaultValue = "General") String hospitalUnit) {
        return ResponseEntity.ok(stockService.getPredictStock(codigo, hospitalUnit));
    }

    @GetMapping("/alertas/caducidad")
    public ResponseEntity<?> getExpirationRisk() {
        return ResponseEntity.ok(stockService.getExpirationRisk());
    }

    @PostMapping("/anomalias")
    public ResponseEntity<?> detectAnomaly(@RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(stockService.detectAnomaly(request));
    }

    @PostMapping("/orden-compra")
    public ResponseEntity<?> sendPurchaseOrder(@RequestBody Map<String, Object> request) {
        // Mock sending purchase order to SIGA
        return ResponseEntity.ok(Map.of("status", "SUCCESS", "message", "Orden de compra enviada exitosamente a SIGA", "order", request));
    }
}
