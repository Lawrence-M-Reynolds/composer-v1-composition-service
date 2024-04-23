package com.reynolds.compositionservice.dao;

import com.reynolds.api.core.composition.Composition;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompositionRepository extends JpaRepository<Composition, Long> {
}
