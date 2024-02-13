package ru.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.manager.exceptions.NotFoundException;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    int delete(int id);

    //  https://stackoverflow.com/a/60695301/548473 (existed delete code 204, not existed: 404)
    @SuppressWarnings("all") // transaction invoked
    default void deleteExisted(int id) {
        if (delete(id) == 0) {
            throw new NotFoundException("Entity with id=" + id + " not found");
        }
    }
    @Query("SELECT p FROM #{#entityName} p WHERE p.name=:name")
    Optional<T> getByName(String name);

    default T getByNameExists(String name) {
        return getByName(name).orElseThrow(()-> new NotFoundException("Entity with name " + name + " not found."));
    }
    default T getExisted(int id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Entity with id=" + id + " not found"));
    }
}
