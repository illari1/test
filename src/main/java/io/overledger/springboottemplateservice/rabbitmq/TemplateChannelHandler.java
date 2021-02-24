package io.overledger.springboottemplateservice.rabbitmq;

/*
@Configuration
@EnableBinding(TemplateOutputChannel.class)
@IntegrationComponentScan
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Slf4j
// Input Channel and comment out the rabbitmq.TemplateConfig class
public class TemplateChannelHandler {

    TemplateService templateService;
    ObjectMapper objectMapper;

    @StreamListener(TemplateOutputChannel.CHANNEL_NAME)
    public void stateChannelHandler(@Payload String payload, @Header("message") String message, @Header("processTime") Long processTime, @Header("type") String type) throws JsonProcessingException {
        TemplateRequest templateRequest = null;
        if (type.equals(TemplateRequest.class.getName())) {
            templateRequest = this.objectMapper.readValue(payload, TemplateRequest.class);
        }
        if (templateRequest != null) {
            log.info(String.format("Processing templateRequest: %s, templateField: %s, dispatch timestamp: %d, message type: %s.", templateRequest.getTemplateField(), message, processTime, type));
            this.templateService.saveToDatabase(templateRequest);
        }
    }


}
*/