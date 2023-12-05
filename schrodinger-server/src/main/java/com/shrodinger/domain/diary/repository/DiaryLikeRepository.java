package com.shrodinger.domain.diary.repository;

import com.shrodinger.domain.diary.entity.DiaryHeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryLikeRepository extends JpaRepository<DiaryHeart,Long> {
}
