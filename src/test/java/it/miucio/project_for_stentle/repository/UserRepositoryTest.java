package it.miucio.project_for_stentle.repository;

import it.miucio.project_for_stentle.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    final static String MOD = "mod73827";
    final static String EMAIL = "test@test.com";
    static private User user;

    @Autowired UserRepository userRepository;

    @BeforeAll
    static void init(@Autowired UserRepository userRepository) {
        user = new User(MOD,EMAIL,MOD);

        userRepository.save(user);
    }

    @Test
    void findByUsername() {
        assertThat(userRepository.findByUsername(user.getUsername()).get().getUsername()).isEqualTo(MOD);
    }

    @Test
    void existsByUsername() {
        assertThat(userRepository.existsByUsername(user.getUsername())).isTrue();
    }

    @Test
    void existsByEmail() {
        assertThat(userRepository.existsByEmail(user.getEmail())).isTrue();
    }
}