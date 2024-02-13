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
import ru.manager.model.Department;
import ru.manager.model.ParentDepartment;
import ru.manager.repository.ParentDepartmentRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = DepartmentController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class DepartmentController {
    public static final String URL = "/departments";

    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private ParentDepartmentRepository repository;

    @GetMapping
    public List<ParentDepartment> getAll() {
        log.info("get all departments");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable int id) {
        log.info("get department {}", id);
        return repository.getExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParentDepartment> create(@Valid @RequestBody ParentDepartment department) {
        System.out.println(department.getName());
        log.info("create new department {}", department);
        ParentDepartment created = repository.save(department);
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
    public void update(@Valid @RequestBody ParentDepartment department) {
        log.info("update {}", department);
        repository.save(department);
    }
}
