package it.miucio.project_for_stentle.repository;

import it.miucio.project_for_stentle.model.ERole;
import it.miucio.project_for_stentle.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
