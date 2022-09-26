package it.miucio.project_for_stentle.service;

import it.miucio.project_for_stentle.dto.AlumniSummary;
import it.miucio.project_for_stentle.model.Alumni;
import it.miucio.project_for_stentle.model.EUniversity;
import it.miucio.project_for_stentle.repository.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AlumniServiceImpl implements AlumniService{

    @Autowired
    AlumniRepository alumniRepository;

    @CacheEvict(cacheNames = {"alumniListByNameCache", "alumniListByEduCache"}, allEntries = true)
    public Alumni addAlumni(String name, List addresses, Map education) {
        Alumni alumni = new Alumni(name);
        alumni.setAddresses(addresses);
        alumni.setEducation(education);
        return alumniRepository.save(alumni);
    }

    @Cacheable(cacheNames = {"alumniListByNameCache"}, key = "#name")
    public AlumniSummary getAlumni(String name, int page, int size) {
        List<Alumni> alumni = alumniRepository.findByName(name, PageRequest.of(page, size)).get().getContent();
        return new AlumniSummary((long) alumni.size(), alumni);
    }

    @Cacheable(cacheNames = {"alumniListByEduCache"}, key = "#education")
    public AlumniSummary getAlumni(EUniversity education, int page, int size) {
        List<Alumni> alumni = alumniRepository.findByEducation(education, PageRequest.of(page, size)).get().getContent();
        return new AlumniSummary((long) alumni.size(), alumni);
    }
}
