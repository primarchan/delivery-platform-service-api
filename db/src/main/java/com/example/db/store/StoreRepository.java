package com.example.db.store;

import com.example.db.store.enums.StoreCategory;
import com.example.db.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    // select * from store where id = ? and status = ? order by id desc limit 1
    Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus storeStatus);

    // select * from store where status = ? order by id desc;
    List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus storeStatus);

    // select * from where status = ? and category = ? order by star desc;
    List<StoreEntity> findAllByStatusAndCategoryOrderByStarDesc(StoreStatus storeStatus, StoreCategory storeCategory);



}
