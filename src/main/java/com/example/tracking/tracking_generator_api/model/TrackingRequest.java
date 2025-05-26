package com.example.tracking.tracking_generator_api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;

public record TrackingRequest(
        @Schema(description = "Origin country ISO 3166-1 alpha-2", example = "MY", defaultValue = "MY")
        @NotBlank String origin_country_id,

        @Schema(description = "Destination country ISO 3166-1 alpha-2", example = "ID", defaultValue = "ID")
        @NotBlank String destination_country_id,

        @Schema(description = "Weight in kilograms", example = "1.234", defaultValue = "1.234")
        @Positive double weight,

        @Schema(description = "Creation timestamp in RFC 3339 format", example = "2024-05-26T10:00:00+08:00", defaultValue = "2024-05-26T10:00:00+08:00")
        @NotBlank String created_at,

        @Schema(description = "Customer UUID", example = "de619854-b59b-425e-9db4-943979e1bd49", defaultValue = "de619854-b59b-425e-9db4-943979e1bd49")
        @NotBlank String customer_id,

        @Schema(description = "Customer name", example = "RedBox Logistics", defaultValue = "RedBox Logistics")
        @NotBlank String customer_name,

        @Schema(description = "Customer slug", example = "redbox-logistics", defaultValue = "redbox-logistics")
        @NotBlank String customer_slug
) {}