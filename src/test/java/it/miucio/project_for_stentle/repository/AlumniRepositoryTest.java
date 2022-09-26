package it.miucio.project_for_stentle.repository;

import it.miucio.project_for_stentle.model.Address;
import it.miucio.project_for_stentle.model.Alumni;
import it.miucio.project_for_stentle.model.EUniversity;
import it.miucio.project_for_stentle.model.University;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class AlumniRepositoryTest {

    static int page,size;
    static private Alumni alumni;

    @Autowired
    AlumniRepository alumniRepository;

    @BeforeAll
    static void init(@Autowired AlumniRepository alumniRepository) {
        alumni = new Alumni("paperino");
        Address address = new Address("streetname", "123", "country");
        University university = new University("university","2009");
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        Map<EUniversity,University> eUniversityUniversityMap = new HashMap<>();
        eUniversityUniversityMap.put(EUniversity.PHD,university);
        alumni.setAddresses(addresses);
        alumni.setEducation(eUniversityUniversityMap);
        page = 0;
        size = 3;

        alumniRepository.save(alumni);
    }

    @Test
    void findByName() {
        List<Alumni> alumnis = alumniRepository.findByName(alumni.getName(), PageRequest.of(page, size)).get().getContent();
        assertThat(alumnis.get(0).getName()).isEqualTo(alumni.getName());
    }

    @Test
    void findByEducation() {
        List<Alumni> alumni = alumniRepository.findByEducation(EUniversity.PHD, PageRequest.of(page, size)).get().getContent();
        assertThat(alumni.isEmpty()).isNotEqualTo(true);
    }
}