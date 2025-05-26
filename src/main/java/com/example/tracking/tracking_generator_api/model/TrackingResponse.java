package com.example.tracking.tracking_generator_api.model;

public record TrackingResponse(
        String tracking_number
        , String created_at) {
}