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
import ru.manager.model.Employee;
import ru.manager.repository.EmployeeRepository;
import ru.manager.service.EmployeeService;
import ru.manager.to.EmployeeTo;
import ru.manager.to.To;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = EmployeeController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    public static final String URL = "/employees";

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<EmployeeTo> getAll() {
        log.info("get all employees");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeTo get(@PathVariable int id) {
        log.info("get employee {}", id);
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeTo employee) {
        log.info("create new employee {}", employee);
        System.out.println(employee);
        Employee created = service.save(To.get(employee), employee.getProfession(), employee.getDepartment());
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
    public void update(@Valid @RequestBody EmployeeTo employee) {
        log.info("update {}", employee);
        service.save(To.get(employee), employee.getProfession(), employee.getDepartment());
    }
}
