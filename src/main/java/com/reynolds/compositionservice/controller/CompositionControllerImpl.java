package com.reynolds.compositionservice.controller;

import com.reynolds.api.core.composition.Composition;
import com.reynolds.api.core.composition.CompositionController;
import com.reynolds.compositionservice.service.CompositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
public class CompositionControllerImpl implements CompositionController {

    private final CompositionService compositionService;

    public CompositionControllerImpl(CompositionService compositionService) {
        this.compositionService = compositionService;
    }

    public String testEndpoint() {
        return "Composition Service";
    }

    @Override
    public ResponseEntity<Composition> save(Composition composition) throws IOException {
        composition = compositionService.saveComposition(composition);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/compositions/{id}")
                .buildAndExpand(composition.getId())
                .toUri();

        return ResponseEntity.created(location).body(composition);
    }

    @Override
    public List<Composition> getCompositions() {
        return compositionService.findAllCompositions();
    }

}
