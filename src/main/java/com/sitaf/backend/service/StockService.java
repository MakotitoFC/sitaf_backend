package com.sitaf.backend.service;

import com.sitaf.model.SismedItem;
import com.sitaf.backend.repository.SismedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private SismedItemRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    private final String AI_URL = "http://localhost:8000";

    public List<SismedItem> importSigaData() {
        return repository.findAll();
    }

    public Object getPredictStock(String sismedCode, String hospitalUnit) {
        Map<String, String> request = new HashMap<>();
        request.put("sismed_code", sismedCode);
        request.put("hospital_unit", hospitalUnit);

        ResponseEntity<Object> response = restTemplate.postForEntity(AI_URL + "/predict/stock", request, Object.class);
        return response.getBody();
    }

    public Object getExpirationRisk() {
        List<SismedItem> items = repository.findAll();
        
        List<Map<String, String>> requestItems = items.stream().map(item -> {
            Map<String, String> map = new HashMap<>();
            map.put("sismed_code", item.getCodigo());
            map.put("lote", "LOTE-" + item.getCodigo());
            map.put("expiration_date", item.getFechaVencimiento());
            return map;
        }).collect(Collectors.toList());

        Map<String, Object> request = new HashMap<>();
        request.put("items", requestItems);

        ResponseEntity<Object> response = restTemplate.postForEntity(AI_URL + "/predict/expiration_risk", request, Object.class);
        return response.getBody();
    }

    public Object detectAnomaly(Map<String, Object> request) {
        ResponseEntity<Object> response = restTemplate.postForEntity(AI_URL + "/detect/anomaly", request, Object.class);
        return response.getBody();
    }
}
