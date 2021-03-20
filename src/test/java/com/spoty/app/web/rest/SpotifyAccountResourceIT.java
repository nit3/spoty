package com.spoty.app.web.rest;

import com.spoty.app.SpotyApp;
import com.spoty.app.domain.SpotifyAccount;
import com.spoty.app.repository.SpotifyAccountRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SpotifyAccountResource} REST controller.
 */
@SpringBootTest(classes = SpotyApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SpotifyAccountResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    @Autowired
    private SpotifyAccountRepository spotifyAccountRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpotifyAccountMockMvc;

    private SpotifyAccount spotifyAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SpotifyAccount createEntity(EntityManager em) {
        SpotifyAccount spotifyAccount = new SpotifyAccount()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD);
        return spotifyAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SpotifyAccount createUpdatedEntity(EntityManager em) {
        SpotifyAccount spotifyAccount = new SpotifyAccount()
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD);
        return spotifyAccount;
    }

    @BeforeEach
    public void initTest() {
        spotifyAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpotifyAccount() throws Exception {
        int databaseSizeBeforeCreate = spotifyAccountRepository.findAll().size();
        // Create the SpotifyAccount
        restSpotifyAccountMockMvc.perform(post("/api/spotify-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(spotifyAccount)))
            .andExpect(status().isCreated());

        // Validate the SpotifyAccount in the database
        List<SpotifyAccount> spotifyAccountList = spotifyAccountRepository.findAll();
        assertThat(spotifyAccountList).hasSize(databaseSizeBeforeCreate + 1);
        SpotifyAccount testSpotifyAccount = spotifyAccountList.get(spotifyAccountList.size() - 1);
        assertThat(testSpotifyAccount.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testSpotifyAccount.getPassword()).isEqualTo(DEFAULT_PASSWORD);
    }

    @Test
    @Transactional
    public void createSpotifyAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = spotifyAccountRepository.findAll().size();

        // Create the SpotifyAccount with an existing ID
        spotifyAccount.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpotifyAccountMockMvc.perform(post("/api/spotify-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(spotifyAccount)))
            .andExpect(status().isBadRequest());

        // Validate the SpotifyAccount in the database
        List<SpotifyAccount> spotifyAccountList = spotifyAccountRepository.findAll();
        assertThat(spotifyAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSpotifyAccounts() throws Exception {
        // Initialize the database
        spotifyAccountRepository.saveAndFlush(spotifyAccount);

        // Get all the spotifyAccountList
        restSpotifyAccountMockMvc.perform(get("/api/spotify-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(spotifyAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)));
    }
    
    @Test
    @Transactional
    public void getSpotifyAccount() throws Exception {
        // Initialize the database
        spotifyAccountRepository.saveAndFlush(spotifyAccount);

        // Get the spotifyAccount
        restSpotifyAccountMockMvc.perform(get("/api/spotify-accounts/{id}", spotifyAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(spotifyAccount.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD));
    }
    @Test
    @Transactional
    public void getNonExistingSpotifyAccount() throws Exception {
        // Get the spotifyAccount
        restSpotifyAccountMockMvc.perform(get("/api/spotify-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpotifyAccount() throws Exception {
        // Initialize the database
        spotifyAccountRepository.saveAndFlush(spotifyAccount);

        int databaseSizeBeforeUpdate = spotifyAccountRepository.findAll().size();

        // Update the spotifyAccount
        SpotifyAccount updatedSpotifyAccount = spotifyAccountRepository.findById(spotifyAccount.getId()).get();
        // Disconnect from session so that the updates on updatedSpotifyAccount are not directly saved in db
        em.detach(updatedSpotifyAccount);
        updatedSpotifyAccount
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD);

        restSpotifyAccountMockMvc.perform(put("/api/spotify-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSpotifyAccount)))
            .andExpect(status().isOk());

        // Validate the SpotifyAccount in the database
        List<SpotifyAccount> spotifyAccountList = spotifyAccountRepository.findAll();
        assertThat(spotifyAccountList).hasSize(databaseSizeBeforeUpdate);
        SpotifyAccount testSpotifyAccount = spotifyAccountList.get(spotifyAccountList.size() - 1);
        assertThat(testSpotifyAccount.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testSpotifyAccount.getPassword()).isEqualTo(UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    public void updateNonExistingSpotifyAccount() throws Exception {
        int databaseSizeBeforeUpdate = spotifyAccountRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpotifyAccountMockMvc.perform(put("/api/spotify-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(spotifyAccount)))
            .andExpect(status().isBadRequest());

        // Validate the SpotifyAccount in the database
        List<SpotifyAccount> spotifyAccountList = spotifyAccountRepository.findAll();
        assertThat(spotifyAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpotifyAccount() throws Exception {
        // Initialize the database
        spotifyAccountRepository.saveAndFlush(spotifyAccount);

        int databaseSizeBeforeDelete = spotifyAccountRepository.findAll().size();

        // Delete the spotifyAccount
        restSpotifyAccountMockMvc.perform(delete("/api/spotify-accounts/{id}", spotifyAccount.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SpotifyAccount> spotifyAccountList = spotifyAccountRepository.findAll();
        assertThat(spotifyAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
