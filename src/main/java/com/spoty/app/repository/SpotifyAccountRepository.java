package com.spoty.app.repository;

import com.spoty.app.domain.SpotifyAccount;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SpotifyAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpotifyAccountRepository extends JpaRepository<SpotifyAccount, Long> {
}
