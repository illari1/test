package io.overledger.springboottemplateservice.mongodb;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    private UUID id;
    private Boolean online;
    private String dlt;
    private List<String> addresses;

}
