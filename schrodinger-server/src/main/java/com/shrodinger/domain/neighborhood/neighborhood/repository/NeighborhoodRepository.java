package com.shrodinger.domain.neighborhood.neighborhood.repository;

import com.shrodinger.domain.neighborhood.neighborhood.entity.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {
    Neighborhood findByCityAndGuAndDong(String city, String gu, String dong);

    boolean existsByCityAndGuAndDong(String city, String gu, String dong);
}
