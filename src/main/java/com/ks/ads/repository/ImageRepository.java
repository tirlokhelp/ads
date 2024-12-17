package com.ks.ads.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ks.ads.entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    // Spring Data JPA method
    Optional<ImageEntity> findTopByOrderByIdDesc();
    Optional<ImageEntity> findFirstByOrderByIdAsc();
    // Custom JPQL query
    @Query("SELECT i FROM ImageEntity i ORDER BY i.id DESC")
    Optional<ImageEntity> findLatestImage();

    // Custom Native SQL query
    @Query(value = "SELECT * FROM image_entity ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<ImageEntity> findLatestImageNative();
}
