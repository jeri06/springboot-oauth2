package com.myforce.auth.repository;

import java.util.Optional;

import com.myforce.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myforce.auth.*;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(final String username);
}
