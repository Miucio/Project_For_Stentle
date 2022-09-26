package it.miucio.project_for_stentle.repository;

import it.miucio.project_for_stentle.model.Alumni;
import it.miucio.project_for_stentle.model.EUniversity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumniRepository extends MongoRepository<Alumni, String> {
    Optional<Page<Alumni>> findByName(String name, Pageable pageable);

    @Query("{\"education.?0\": {$exists : true}}")
    Optional<Page<Alumni>> findByEducation(EUniversity education, Pageable pageable);
}
