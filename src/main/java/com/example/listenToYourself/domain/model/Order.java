package com.example.listenToYourself.domain.model;

import com.couchbase.client.java.repository.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.couchbase.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order {

    @Id
    private String orderId;
    private String customerId;
    private String fulfilmentGroupId;
    private String deliveryGroupId;
}
