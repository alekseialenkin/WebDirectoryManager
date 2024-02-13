package ru.manager.web;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.manager.model.Profession;
import ru.manager.repository.ProfessionRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ProfessionController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ProfessionController {
    public static final String URL = "/professions";
    private static final Logger log = LoggerFactory.getLogger(ProfessionController.class);

    @Autowired
    private ProfessionRepository repository;

    @GetMapping
    public List<Profession> getAll() {
        log.info("get all professions");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Profession get(@PathVariable int id) {
        log.info("get profession {}", id);
        return repository.getExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profession> create(@Valid @RequestBody Profession profession) {
        log.info("create new profession {}", profession);
        Profession created = repository.save(profession);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Profession profession) {
        log.info("update {}", profession);
        repository.save(profession);
    }
}
