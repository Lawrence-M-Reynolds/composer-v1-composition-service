package com.reynolds.composer.v1.compositionservice.service;

import com.reynolds.composer.v1.api.core.composition.composition.Composition;
import com.reynolds.composer.v1.compositionservice.dao.CompositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompositionService {
    private final CompositionRepository compositionRepository;

    public CompositionService(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @Transactional(readOnly = true)
    public List<Composition> findAllCompositions() {
        return compositionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Composition> findCompositionById(Long id) {
        return compositionRepository.findById(id);
    }

    public Composition saveComposition(Composition composition) {
        return compositionRepository.save(composition);
    }

    public void deleteProduct(Composition composition) {
        compositionRepository.delete(composition);
    }

    public void initializeDatabase() {
        
    }
}
