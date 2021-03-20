package com.spoty.app.web.rest;

import com.spoty.app.domain.SpotifyAccount;
import com.spoty.app.repository.SpotifyAccountRepository;
import com.spoty.app.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.spoty.app.domain.SpotifyAccount}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SpotifyAccountResource {

    private final Logger log = LoggerFactory.getLogger(SpotifyAccountResource.class);

    private static final String ENTITY_NAME = "spotifyAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpotifyAccountRepository spotifyAccountRepository;

    public SpotifyAccountResource(SpotifyAccountRepository spotifyAccountRepository) {
        this.spotifyAccountRepository = spotifyAccountRepository;
    }

    /**
     * {@code POST  /spotify-accounts} : Create a new spotifyAccount.
     *
     * @param spotifyAccount the spotifyAccount to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new spotifyAccount, or with status {@code 400 (Bad Request)} if the spotifyAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/spotify-accounts")
    public ResponseEntity<SpotifyAccount> createSpotifyAccount(@RequestBody SpotifyAccount spotifyAccount) throws URISyntaxException {
        log.debug("REST request to save SpotifyAccount : {}", spotifyAccount);
        if (spotifyAccount.getId() != null) {
            throw new BadRequestAlertException("A new spotifyAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpotifyAccount result = spotifyAccountRepository.save(spotifyAccount);
        return ResponseEntity.created(new URI("/api/spotify-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /spotify-accounts} : Updates an existing spotifyAccount.
     *
     * @param spotifyAccount the spotifyAccount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated spotifyAccount,
     * or with status {@code 400 (Bad Request)} if the spotifyAccount is not valid,
     * or with status {@code 500 (Internal Server Error)} if the spotifyAccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/spotify-accounts")
    public ResponseEntity<SpotifyAccount> updateSpotifyAccount(@RequestBody SpotifyAccount spotifyAccount) throws URISyntaxException {
        log.debug("REST request to update SpotifyAccount : {}", spotifyAccount);
        if (spotifyAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpotifyAccount result = spotifyAccountRepository.save(spotifyAccount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, spotifyAccount.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /spotify-accounts} : get all the spotifyAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of spotifyAccounts in body.
     */
    @GetMapping("/spotify-accounts")
    public List<SpotifyAccount> getAllSpotifyAccounts() {
        log.debug("REST request to get all SpotifyAccounts");
        return spotifyAccountRepository.findAll();
    }

    /**
     * {@code GET  /spotify-accounts/:id} : get the "id" spotifyAccount.
     *
     * @param id the id of the spotifyAccount to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the spotifyAccount, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/spotify-accounts/{id}")
    public ResponseEntity<SpotifyAccount> getSpotifyAccount(@PathVariable Long id) {
        log.debug("REST request to get SpotifyAccount : {}", id);
        Optional<SpotifyAccount> spotifyAccount = spotifyAccountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(spotifyAccount);
    }

    /**
     * {@code DELETE  /spotify-accounts/:id} : delete the "id" spotifyAccount.
     *
     * @param id the id of the spotifyAccount to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/spotify-accounts/{id}")
    public ResponseEntity<Void> deleteSpotifyAccount(@PathVariable Long id) {
        log.debug("REST request to delete SpotifyAccount : {}", id);
        spotifyAccountRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
