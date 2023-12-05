package com.shrodinger.domain.diary.repository;

import com.shrodinger.domain.diary.entity.DiaryComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryCommentRepository extends JpaRepository<DiaryComment, Long> {
}
