package io.overledger.springboottemplateservice.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TemplateRepository extends ReactiveMongoRepository<Subscription, UUID> {


    Mono<Subscription> findByOnline(Boolean online);
    Mono<Subscription> findByDlt(String dlt);

}
