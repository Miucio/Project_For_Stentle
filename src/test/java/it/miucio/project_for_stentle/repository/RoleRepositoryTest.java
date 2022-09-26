package it.miucio.project_for_stentle.repository;

import it.miucio.project_for_stentle.model.ERole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class RoleRepositoryTest {

    @Test
    void findByName(@Autowired RoleRepository roleRepository) {
        assertThat(roleRepository.findByName(ERole.ROLE_USER).get().getName()).isEqualTo(ERole.ROLE_USER);
    }
}