package io.overledger.springboottemplateservice.services;

import io.overledger.springboottemplateservice.dto.AddressTrackingRequestDetails;
import io.overledger.springboottemplateservice.dto.AddressTrackingResponse;
import io.overledger.springboottemplateservice.exceptions.TemplateException;
import io.overledger.springboottemplateservice.mongodb.Subscription;
import io.overledger.springboottemplateservice.mongodb.TemplateRepository;
import io.overledger.springboottemplateservice.rabbitmq.TemplatePublishGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TemplateService {

    TemplateRepository templateRepository;
    TemplatePublishGateway templatePublishGateway;

    public Mono<AddressTrackingResponse> subscribe(AddressTrackingRequestDetails addressTrackingRequestDetails) {

        log.info(String.format("Validating request: %s.", addressTrackingRequestDetails.toString()));
        saveToDatabase(addressTrackingRequestDetails, true);
        AddressTrackingResponse addressTrackingResponse = AddressTrackingResponse.builder()
                .addresses(addressTrackingRequestDetails.getAddresses())
                .dlt(addressTrackingRequestDetails.getDlt())
                .online(true)
                .build();


        return Mono.just(addressTrackingResponse);
    }

    public Flux<Subscription> getSubscriptions() {
        return this.templateRepository
                .findAll();
    }
/*
    private void publishToQueue(TemplateRequest templateRequest) {
        log.info("Publishing the request to the queue.");
        this.templatePublishGateway
                .templatePublishRequest(
                        templateRequest,
                        templateRequest.getId(),
                        System.currentTimeMillis(),
                        templateRequest.getClass().getName()
                );
    }
    */


    public void saveToDatabase(AddressTrackingRequestDetails addressTrackingRequestDetails, boolean subscribe) {
        log.info("Saving the subscription to the database.");
        this.templateRepository
                .save(new Subscription(UUID.randomUUID(), subscribe, addressTrackingRequestDetails.getDlt(),
                        addressTrackingRequestDetails.getAddresses()))
                .subscribe(result -> log.info(String.valueOf(result)));
    }

    public void unsubscribe(UUID subscriptionId) {

        Mono<Subscription> subscription = templateRepository.findById(subscriptionId)
                .switchIfEmpty((Mono.error(new TemplateException("The document was not found."))));

        log.info("updating the subscription to the database.");
        this.templateRepository.save(new Subscription(subscriptionId, false, subscription.block().getDlt(),
                    subscription.block().getAddresses()))
                    .subscribe(result -> log.info(String.valueOf(result)));
    }

}
