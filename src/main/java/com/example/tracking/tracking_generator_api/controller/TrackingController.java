package com.example.tracking.tracking_generator_api.controller;


import com.example.tracking.tracking_generator_api.model.TrackingRequest;
import com.example.tracking.tracking_generator_api.model.TrackingResponse;
import com.example.tracking.tracking_generator_api.service.TrackingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tracking")
@Tag(name = "Tracking", description = "Tracking API endpoints")
public class TrackingController {

    private static final Logger log = LoggerFactory.getLogger(TrackingController.class);
    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping("/next-tracking-number")
    @Operation(
            summary = "Generate next tracking number",
            description = "Generates a new tracking number based on the request"
    )
    public Mono<TrackingResponse> getTrackingNumber(@Valid @RequestBody TrackingRequest trackingRequest) {
    log.info("Received tracking request: {}", trackingRequest);
        return trackingService.generateUniqueTrackingNumber(trackingRequest.origin_country_id(), trackingRequest.destination_country_id());
    }
}
