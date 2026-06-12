package com.sitaf.service;

import com.sitaf.model.SismedItem;
import com.sitaf.backend.repository.SismedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SismedMockService {

    @Autowired
    private SismedItemRepository repository;

    public List<SismedItem> getCatalogAndStock() {
        return repository.findAll();
    }
}
