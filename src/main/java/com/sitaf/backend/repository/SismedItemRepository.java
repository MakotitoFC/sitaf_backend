package com.sitaf.backend.repository;

import com.sitaf.model.SismedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SismedItemRepository extends JpaRepository<SismedItem, String> {
}
