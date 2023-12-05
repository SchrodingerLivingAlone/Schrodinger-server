package com.shrodinger.domain.user.dto;

import com.shrodinger.domain.user.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponseDTO {
    private Long id;
    private String dong;
    private String nickname;
    private String profileImageUrl;

    public static UserProfileResponseDTO from(Member member) {
        return UserProfileResponseDTO.builder()
                .id(member.getId())
                .dong(member.getNeighborhood().getDong())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImage())
                .build();
    }
}
