package com.shrodinger.domain.neighborhood.neighborhoodpost.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NeighborhoodPostCategory {
    RESTAURANT(0, "맛집"),
    FACILITY(1, "시설"),
    SHARE_INFORMATION(2, "정보공유"),
    TOGETHER(3, "같이해요"),
    COMMUNICATION(4, "소통해요"),
    ETC(5, "기타");

    private final Integer value;
    private final String category;

    public static NeighborhoodPostCategory valueOf(Integer value) {
        for (NeighborhoodPostCategory category : NeighborhoodPostCategory.values()) {
            if (category.getValue().equals(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid Category: " + value);
    }
}
