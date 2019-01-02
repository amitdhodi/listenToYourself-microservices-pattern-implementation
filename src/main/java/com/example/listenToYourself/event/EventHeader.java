package com.example.listenToYourself.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventHeader {
    private String id;
    private String traceId;
    private String type;
    private String timestamp;

    public EventHeader(String type){
        this.id = UUID.randomUUID().toString();
        this.traceId = UUID.randomUUID().toString();
        this.type = type;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.timestamp = dateFormat.format(Date.from(Instant.now()));
    }
}
