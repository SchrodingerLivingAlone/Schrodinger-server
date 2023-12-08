package com.shrodinger.domain.user.dto;

import com.shrodinger.domain.neighborhood.neighborhood.entity.Neighborhood;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class UpdateLocationResponseDTO {
    private String city;
    private String gu;
    private String dong;

    public static UpdateLocationResponseDTO from(Neighborhood neighborhood) {
        return UpdateLocationResponseDTO.builder()
                .city(neighborhood.getCity())
                .gu(neighborhood.getGu())
                .dong(neighborhood.getDong())
                .build();
    }
}
