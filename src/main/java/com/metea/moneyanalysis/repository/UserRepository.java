package com.metea.moneyanalysis.repository;

import com.metea.moneyanalysis.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetail,Long> {
    UserDetail findByUsername(String username);
}
