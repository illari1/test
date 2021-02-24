package io.overledger.springboottemplateservice.rabbitmq;

import io.overledger.springboottemplateservice.dto.AddressTrackingRequestDetails;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway
public interface TemplatePublishGateway {

    @Gateway(requestChannel = TemplateOutputChannel.CHANNEL_NAME)
    void templatePublishRequest(@Payload AddressTrackingRequestDetails addressTrackingRequestDetails, @Header("message") String message, @Header("processTime") Long processTime, @Header("type") String type);
}
