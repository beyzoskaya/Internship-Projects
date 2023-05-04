package com.beyza.spring.security.email.verification.appuser;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional(readOnly = "true")
public interface AppUserRepository {
    Optional<AppUser> findByEmail(String email);
}
