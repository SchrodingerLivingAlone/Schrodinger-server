package com.shrodinger.domain.diary.repository;

import com.shrodinger.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByOrderByCreatedAtDesc();
    Optional<Diary> findById(Long id);

}
