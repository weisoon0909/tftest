package com.tecforte.blog.web.rest;

import com.tecforte.blog.service.EntryService;
import com.tecforte.blog.web.rest.errors.BadRequestAlertException;
import com.tecforte.blog.service.dto.EntryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.tecforte.blog.domain.Entry}.
 */
@RestController
@RequestMapping("/api")
public class EntryResource {

    private final Logger log = LoggerFactory.getLogger(EntryResource.class);

    private static final String ENTITY_NAME = "entry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntryService entryService;

    public EntryResource(EntryService entryService) {
        this.entryService = entryService;
    }

    /**
     * {@code POST  /entries} : Create a new entry.
     *
     * @param entryDTO the entryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entryDTO, or with status {@code 400 (Bad Request)} if the entry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entries")
    public ResponseEntity<EntryDTO> createEntry(@Valid @RequestBody EntryDTO entryDTO) throws URISyntaxException {
        log.debug("REST request to save Entry : {}", entryDTO);
        if (entryDTO.getId() != null) {
            throw new BadRequestAlertException("A new entry cannot already have an ID", ENTITY_NAME, "idexists");
        }


        //MODIFIED BY CHEE WEI SOON -  START
        if (entryDTO.getEmoji().toString() == "HAHA" ||entryDTO.getEmoji().toString() == "LIKE"||entryDTO.getEmoji().toString() == "WOW"){
            log.debug("REST request to update Entry : {}", entryDTO);

            String entryTitle = entryDTO.getTitle().toLowerCase();
            String entryContent = entryDTO.getContent().toLowerCase();
            String sadKeyWord = ".*\\bsad\\b.*";
            String fearKeyWord = ".*\\bfear\\b.*";
            String lonelyKeyWord = ".*\\blonely\\b.*";

            if (entryTitle.matches(sadKeyWord) == true || entryTitle.matches(fearKeyWord) == true || entryTitle.matches(lonelyKeyWord) == true){
                throw new BadRequestAlertException("Invalid content", ENTITY_NAME, "invalidContent");
            }
            if (entryContent.matches(sadKeyWord) == true || entryContent.matches(fearKeyWord) == true || entryContent.matches(lonelyKeyWord) == true){
                throw new BadRequestAlertException("Invalid content", ENTITY_NAME, "invalidContent");
            }
        }else{
            String entryTitle = entryDTO.getTitle().toLowerCase();
            String entryContent = entryDTO.getContent().toLowerCase();
            String loveKeyWord = ".*\\blove\\b.*";
            String happyKeyWord = ".*\\bhappy\\b.*";
            String trustKeyWord = ".*\\btrust\\b.*";

            if (entryTitle.matches(loveKeyWord) == true || entryTitle.matches(happyKeyWord) == true || entryTitle.matches(trustKeyWord) == true){
                throw new BadRequestAlertException("Invalid content", ENTITY_NAME, "invalidContent");
            }
            if (entryContent.matches(loveKeyWord) == true || entryContent.matches(happyKeyWord) == true || entryContent.matches(trustKeyWord) == true){
                throw new BadRequestAlertException("Invalid content", ENTITY_NAME, "invalidContent");
            }
        }

        //MODIFIED BY CHEE WEI SOON -  END

        EntryDTO result = entryService.save(entryDTO);
        return ResponseEntity.created(new URI("/api/entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entries} : Updates an existing entry.
     *
     * @param entryDTO the entryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entryDTO,
     * or with status {@code 400 (Bad Request)} if the entryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entries")
    public ResponseEntity<EntryDTO> updateEntry(@Valid @RequestBody EntryDTO entryDTO) throws URISyntaxException {
        log.debug("REST request to update Entry : {}", entryDTO);
        if (entryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        //MODIFIED BY CHEE WEI SOON -  START
        if (entryDTO.getEmoji().toString() == "HAHA" ||entryDTO.getEmoji().toString() == "LIKE"||entryDTO.getEmoji().toString() == "WOW"){
            log.debug("REST request to update Entry : {}", entryDTO);

            String entryTitle = entryDTO.getTitle().toLowerCase();
            String entryContent = entryDTO.getContent().toLowerCase();
            String sadKeyWord = ".*\\bsad\\b.*";
            String fearKeyWord = ".*\\bfear\\b.*";
            String lonelyKeyWord = ".*\\blonely\\b.*";

            if (entryTitle.matches(sadKeyWord) == true || entryTitle.matches(fearKeyWord) == true || entryTitle.matches(lonelyKeyWord) == true){
                throw new BadRequestAlertException("Invalid content", ENTITY_NAME, "invalidContent");
            }
            if (entryContent.matches(sadKeyWord) == true || entryContent.matches(fearKeyWord) == true || entryContent.matches(lonelyKeyWord) == true){
                throw new BadRequestAlertException("Invalid content", ENTITY_NAME, "invalidContent");
            }
        }else{
            String entryTitle = entryDTO.getTitle().toLowerCase();
            String entryContent = entryDTO.getContent().toLowerCase();
            String loveKeyWord = ".*\\blove\\b.*";
            String happyKeyWord = ".*\\bhappy\\b.*";
            String trustKeyWord = ".*\\btrust\\b.*";

            if (entryTitle.matches(loveKeyWord) == true || entryTitle.matches(happyKeyWord) == true || entryTitle.matches(trustKeyWord) == true){
                throw new BadRequestAlertException("Invalid content", ENTITY_NAME, "invalidContent");
            }
            if (entryContent.matches(loveKeyWord) == true || entryContent.matches(happyKeyWord) == true || entryContent.matches(trustKeyWord) == true){
                throw new BadRequestAlertException("Invalid content", ENTITY_NAME, "invalidContent");
            }
        }
        //MODIFIED BY CHEE WEI SOON -  END

        EntryDTO result = entryService.save(entryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entries} : get all the entries.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entries in body.
     */
    @GetMapping("/entries")
    public ResponseEntity<List<EntryDTO>> getAllEntries(Pageable pageable) {
        log.debug("REST request to get a page of Entries");
        Page<EntryDTO> page = entryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /entries/:id} : get the "id" entry.
     *
     * @param id the id of the entryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entries/{id}")
    public ResponseEntity<EntryDTO> getEntry(@PathVariable Long id) {
        log.debug("REST request to get Entry : {}", id);
        Optional<EntryDTO> entryDTO = entryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entryDTO);
    }

    /**
     * {@code DELETE  /entries/:id} : delete the "id" entry.
     *
     * @param id the id of the entryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        log.debug("REST request to delete Entry : {}", id);
        entryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    //MODIFIED BY CHEE WEI SOON -  START (Tried my best but i couldnt solve this part 2 question)
    /**
     * {@code DELETE  /entries/:id} : delete the "id" entry.
     *
     * @param id the id of the entryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    // @DeleteMapping("/blogs/{id}/clean")
    // public ResponseEntity<EntryDTO> deleteEntryKeyword(@PathVariable Long id,@PathVariable String keyword) {
        
    //     log.debug("REST request to delete Entry : {}");
    //     Optional<EntryDTO> entryDTO = entryService.findOne(id);
        

        // String entryTitle = entryDTO.getTitle();
        // String entryContent = entryDTO.getContent();
        
        // String searchKeyWord = ".*\\b"+ keyword + "\\b.*";
        // if (entryTitle.matches(searchKeyWord)){
        //     entryService.delete(id);
        // }
        // else if (entryContent.matches(searchKeyWord)){
        //     entryService.delete(id);
        // }else{
        //     throw new BadRequestAlertException("Invalid Keyword or ID", ENTITY_NAME, "invalidKeywordORid");
        // }
    //     return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    // }
    //MODIFIED BY CHEE WEI SOON -  END
}
