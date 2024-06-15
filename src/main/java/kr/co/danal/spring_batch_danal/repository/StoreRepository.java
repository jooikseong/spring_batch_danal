package kr.co.danal.spring_batch_danal.repository;

import kr.co.danal.spring_batch_danal.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
