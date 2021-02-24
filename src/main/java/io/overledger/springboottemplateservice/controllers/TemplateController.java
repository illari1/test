package io.overledger.springboottemplateservice.controllers;

import io.overledger.springboottemplateservice.dto.AddressTrackingRequestDetails;
import io.overledger.springboottemplateservice.dto.AddressTrackingResponse;
import io.overledger.springboottemplateservice.mongodb.Subscription;
import io.overledger.springboottemplateservice.services.TemplateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping(TemplateController.RESOURCE_NAME)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TemplateController {

    static final String RESOURCE_NAME = "operations/v1";
    TemplateService templateService;

    @PostMapping(value = "/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AddressTrackingResponse> subscribe(@RequestBody @NotNull AddressTrackingRequestDetails addressTrackingRequestDetails) {
        return this.templateService.subscribe(addressTrackingRequestDetails);
    }

    @PatchMapping(value = "/unsubscribe/{subscriptionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void unsubscribe(@PathVariable (value = "subscriptionId") UUID subscriptionId) {
        templateService.unsubscribe(subscriptionId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Subscription> getSubscriptions() {
        return this.templateService.getSubscriptions();
    }


}
