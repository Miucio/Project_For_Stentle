package it.miucio.project_for_stentle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.miucio.project_for_stentle.dto.AlumniSummary;
import it.miucio.project_for_stentle.model.Address;
import it.miucio.project_for_stentle.model.Alumni;
import it.miucio.project_for_stentle.model.EUniversity;
import it.miucio.project_for_stentle.model.University;
import it.miucio.project_for_stentle.service.AlumniService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(AlumniController.class)
class AlumniControllerTest {
    static final EUniversity E_UNIVERSITY = EUniversity.PHD;
    static private Alumni alumni ;
    static private AlumniSummary alumniSummary;
    static private int size;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AlumniService alumniService;

    @BeforeAll
    static void init() {
        List<Alumni> alumniList = new ArrayList<>();
        alumni = new Alumni("poiu");
        Address address = new Address("streetname", "123", "country");
        University university = new University("university","2009");
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        Map<EUniversity,University> eUniversityUniversityMap = new HashMap<>();
        eUniversityUniversityMap.put(EUniversity.PHD,university);
        alumni.setAddresses(addresses);
        alumni.setEducation(eUniversityUniversityMap);
        alumniList.add(alumni);
        alumniList.add(alumni);
        alumniSummary = new AlumniSummary(2L, alumniList);
        size = 3;
    }

    @Test
    void getAlumniUnauthorized() throws Exception {

        when(alumniService.getAlumni(any(String.class),any(int.class), any(int.class)))
                .thenReturn(alumniSummary);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/ex-1/get?name=paperino&size=2")
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(request)
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    void getAlumniByName() throws Exception {

        when(alumniService.getAlumni(any(String.class),any(int.class), any(int.class))).thenReturn(alumniSummary);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/ex-1/get?name=" + alumni.getName() + "&size=" + size)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(alumniSummary)))
                .andReturn();
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    void getAlumniByEdu() throws Exception {

        when(alumniService.getAlumni(any(EUniversity.class),any(int.class), any(int.class)))
                .thenReturn(alumniSummary);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/ex-1/get?education=" + E_UNIVERSITY + "&size=" + size)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(alumniSummary)))
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}