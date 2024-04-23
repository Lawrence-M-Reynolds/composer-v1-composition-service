package com.reynolds.composer.v1.compositionservice.dao;

import com.reynolds.composer.v1.api.core.composition.composition.Composition;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompositionRepository extends JpaRepository<Composition, Long> {
}
