package com.reynolds.composer.v1.compositionservice.service;

import com.reynolds.composer.v1.api.core.composition.composition.Composition;
import com.reynolds.composer.v1.compositionservice.dao.CompositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Service
@Transactional
public class CompositionService {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final Scheduler jdbcScheduler;

    private final CompositionRepository compositionRepository;

    public CompositionService(@Qualifier("jdbcScheduler") Scheduler jdbcScheduler, CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
        this.jdbcScheduler = jdbcScheduler;
    }

    @Transactional(readOnly = true)
    public Flux<Composition> findAllCompositions() {
        return Mono.fromCallable(() -> compositionRepository.findAll())
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(jdbcScheduler);
    }

    @SuppressWarnings("BlockingMethodInNonBlockingContext")
    @Transactional(readOnly = true)
    public Mono<Composition> findCompositionById(Long id) {
        return Mono.fromCallable(() -> compositionRepository.findById(id))
                .flatMap(Mono::justOrEmpty)
                .subscribeOn(jdbcScheduler);
    }

    @SuppressWarnings("BlockingMethodInNonBlockingContext")
    public Mono<Composition> saveComposition(Composition composition) {
        return Mono.fromCallable(() -> compositionRepository.save(composition))
                .log(logger.getName(), Level.FINE)
                .subscribeOn(jdbcScheduler);
    }
}
