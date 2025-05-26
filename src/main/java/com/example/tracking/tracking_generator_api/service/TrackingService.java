package com.example.tracking.tracking_generator_api.service;


import com.example.tracking.tracking_generator_api.model.TrackingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TrackingService {


    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();
    private static final Set<String> generatedNumbers = ConcurrentHashMap.newKeySet();


    public Mono<TrackingResponse> generateUniqueTrackingNumber(String origin, String destination) {
        StringBuilder sb = new StringBuilder();
        sb.append(safePad(origin, 2));
        sb.append(safePad(destination, 2));

        int remaining = 16 - sb.length();
        for (int i = 0; i < remaining; i++) {
            sb.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }

        String now = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        log.info("Generated tracking number: {} at {}", sb.toString(), now);
        return Mono.just(new TrackingResponse(sb.toString(), now));

    }

    private String safePad(String input, int length) {
        if (input == null) return "XX";
        return input.toUpperCase().replaceAll("[^A-Z]", "X").substring(0, Math.min(length, input.length()));
    }
}
