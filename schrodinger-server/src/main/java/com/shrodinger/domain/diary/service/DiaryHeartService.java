package com.shrodinger.domain.diary.service;

import com.shrodinger.common.exception.handler.NeighborhoodPostHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.diary.entity.Diary;
import com.shrodinger.domain.diary.entity.DiaryHeart;
import com.shrodinger.domain.diary.repository.DiaryHeartRepository;
import com.shrodinger.domain.diary.repository.DiaryRepository;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodHeart;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodHeartRepository;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodPostRepository;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryHeartService {
    private final DiaryHeartRepository diaryHeartRepository;
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    public boolean addLike(Long diaryId) {
        Member member = getMemberFromToken();
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(
                () -> new NeighborhoodPostHandler(ErrorStatus.DIARY_NOT_EXIST));
        if (!diaryHeartRepository.existsByMemberAndDiary(member, diary)) {
            diary.setLikeCount(diary.getLikeCount() + 1);
            diaryHeartRepository.save(new DiaryHeart(member, diary));
            return true;
        } else {
            diary.setLikeCount(diary.getLikeCount() - 1);
            diaryHeartRepository.deleteByMemberAndDiary(member, diary);
            return false;
        }
    }

    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }
}
