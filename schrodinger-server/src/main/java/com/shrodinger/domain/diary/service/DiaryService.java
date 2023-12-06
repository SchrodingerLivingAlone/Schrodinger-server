package com.shrodinger.domain.diary.service;

import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.common.s3.AwsS3Service;
import com.shrodinger.domain.diary.dto.CreateDiaryRequestDTO;
import com.shrodinger.domain.diary.dto.CreateDiaryResponseDTO;
import com.shrodinger.domain.diary.dto.DiaryResponseDTO;
import com.shrodinger.domain.diary.entity.Diary;
import com.shrodinger.domain.diary.entity.DiaryImage;
import com.shrodinger.domain.diary.repository.DiaryHeartRepository;
import com.shrodinger.domain.diary.repository.DiaryImageRepository;
import com.shrodinger.domain.diary.repository.DiaryRepository;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryHeartRepository diaryHeartRepository;
    private final DiaryImageRepository diaryImageRepository;
    private final AwsS3Service awsS3Service;
    @Transactional
    public CreateDiaryResponseDTO createDiary(CreateDiaryRequestDTO createDiaryRequestDTO) {
        Member member = getMemberFromToken();
        Diary diary = createDiaryRequestDTO.toEntity(member);
        diaryRepository.save(diary);

        List<DiaryImage> diaryImages = new ArrayList<>();
        for (String imageUrl : awsS3Service.uploadImage(createDiaryRequestDTO.getImages())) {
            DiaryImage diaryImage = DiaryImage.builder()
                    .diary(diary)
                    .imageUrl(imageUrl)
                    .build();
            diaryImageRepository.save(diaryImage);
            diaryImages.add(diaryImage);
        }
        // 이미지 저장 후 다시 업데이트
        diary.setDiaryImages(diaryImages);
        diaryRepository.save(diary);

        return CreateDiaryResponseDTO.from(diary);
    }

    public List<DiaryResponseDTO> getAllDiary() {
        List<DiaryResponseDTO> diaryResponseDTOS = diaryRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(diary -> DiaryResponseDTO.from(diary, getMemberFromToken(), this))
                .collect(Collectors.toList());

        return diaryResponseDTOS;
    }


    public boolean isDiaryLikedByMember(Diary diary, Member member) {
        return diaryHeartRepository.existsByMemberAndDiary(member, diary);
    }
    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }
}
