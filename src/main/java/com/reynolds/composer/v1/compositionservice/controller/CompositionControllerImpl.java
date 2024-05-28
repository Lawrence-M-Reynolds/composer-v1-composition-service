package com.reynolds.composer.v1.compositionservice.controller;

import com.reynolds.composer.v1.api.core.composition.composition.Composition;
import com.reynolds.composer.v1.api.core.composition.composition.CompositionController;
import com.reynolds.composer.v1.compositionservice.service.CompositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
public class CompositionControllerImpl implements CompositionController {

    private final CompositionService compositionService;

    public CompositionControllerImpl(CompositionService compositionService) {
        this.compositionService = compositionService;
    }

    @Override
    public Mono<Composition> save(Composition composition) throws IOException {
        Mono<Composition> compositionMono = compositionService.saveComposition(composition);
        return compositionMono;
    }

    @Override
    public Flux<Composition> getCompositions() {
        return compositionService.findAllCompositions();
    }

    @Override
    public Mono<Composition> getComposition(long compositionId) {
        return compositionService.findCompositionById(compositionId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No composition with the ID exists: " + compositionId)));
    }
}
