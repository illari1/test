package io.overledger.springboottemplateservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AddressTrackingRequestDetails {

    UUID id;
    String dlt;
    List<String> addresses;
}