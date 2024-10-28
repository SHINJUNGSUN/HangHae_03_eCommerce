package io.hhplus.ecommerce.product.infrastructure.persistence;

import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT p
        FROM ProductEntity p
         WHERE p.id = :id
    """)
    Optional<ProductEntity> findByIdForUpdate(long id);
}
