package com.shrodinger.domain.diary.repository;

import com.shrodinger.domain.diary.entity.Diary;
import com.shrodinger.domain.diary.entity.DiaryComment;
import com.shrodinger.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryCommentRepository extends JpaRepository<DiaryComment, Long> {
    List<DiaryComment> findAllByDiary(Diary diary);

    List<DiaryComment> findAllByMember(Member member);
}
