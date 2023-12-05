package com.shrodinger.domain.diary.service;

import com.shrodinger.common.exception.handler.DiaryHandler;
import com.shrodinger.common.exception.handler.NeighborhoodPostHandler;
import com.shrodinger.common.exception.handler.UserHandler;
import com.shrodinger.common.jwt.SecurityUtil;
import com.shrodinger.common.response.status.ErrorStatus;
import com.shrodinger.domain.diary.dto.DiaryCommentResponseDTO;
import com.shrodinger.domain.diary.dto.DiaryResponseDTO;
import com.shrodinger.domain.diary.entity.Diary;
import com.shrodinger.domain.diary.entity.DiaryComment;
import com.shrodinger.domain.diary.repository.DiaryCommentRepository;
import com.shrodinger.domain.diary.repository.DiaryRepository;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CommentResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodComment.CreateCommentRequestDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.dto.NeighborhoodPost.NeighborhoodPostResponseDTO;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodComment;
import com.shrodinger.domain.neighborhood.neighborhoodpost.entity.NeighborhoodPost;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodCommentRepository;
import com.shrodinger.domain.neighborhood.neighborhoodpost.repository.NeighborhoodPostRepository;
import com.shrodinger.domain.user.entity.Member;
import com.shrodinger.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryCommentService {
    private final DiaryCommentRepository diaryCommentRepository;
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;


    @Transactional(readOnly = true)
    public List<DiaryCommentResponseDTO> getAllCommentsByDiaryId(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(()->new DiaryHandler(ErrorStatus.DIARY_NOT_EXIST));
        List<DiaryCommentResponseDTO> diaryCommentResponseDTOS = diaryCommentRepository.findAllByDiary(diary)
                .stream().map(DiaryCommentResponseDTO::from).collect(
                        Collectors.toList());

        return diaryCommentResponseDTOS;
    }

    public DiaryCommentResponseDTO createComment(CreateCommentRequestDTO createCommentRequestDTO,
                                            Long diaryId) {
        Member member = getMemberFromToken();
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(
                () -> new NeighborhoodPostHandler(ErrorStatus.DIARY_NOT_EXIST));
        DiaryComment comment = DiaryComment.builder()
                .content(createCommentRequestDTO.getComment())
                .isDeleted(false)
                .diary(diary)
                .member(member)
                .build();
        diary.upCommentCount();
        diaryRepository.save(diary);
        diaryCommentRepository.save(comment);
        DiaryCommentResponseDTO diaryCommentResponseDTO = DiaryCommentResponseDTO.from(comment);
        return diaryCommentResponseDTO;
    }
    /*
    댓글 달린 자취 일기 넘겨주기
     */
    public List<DiaryResponseDTO> getAllCommentedDiary(){
        Member member = getMemberFromToken();
        List<DiaryComment> diaryComments = diaryCommentRepository.findAllByMember(member);
        return diaryComments.stream()
                .map(DiaryComment :: getDiary)
                .map(DiaryResponseDTO::from)
                .collect(Collectors.toList());
    }
    private Member getMemberFromToken() {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserHandler(ErrorStatus._UNAUTHORIZED));
        return member;
    }
}
