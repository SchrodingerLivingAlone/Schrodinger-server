package com.shrodinger.domain.diary.repository;

import com.shrodinger.domain.diary.entity.Diary;
import com.shrodinger.domain.diary.entity.DiaryHeart;
import com.shrodinger.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryHeartRepository extends JpaRepository<DiaryHeart,Long> {
    boolean existsByMemberAndDiary(Member member, Diary diary);

    void deleteByMemberAndDiary(Member member, Diary diary);
}
