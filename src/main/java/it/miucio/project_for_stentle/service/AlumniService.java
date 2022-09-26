package it.miucio.project_for_stentle.service;

import it.miucio.project_for_stentle.dto.AlumniSummary;
import it.miucio.project_for_stentle.model.Alumni;
import it.miucio.project_for_stentle.model.EUniversity;

import java.util.List;
import java.util.Map;

public interface AlumniService {

    Alumni addAlumni(String name, List addresses, Map education);

    AlumniSummary getAlumni(String name, int page, int size);

    AlumniSummary getAlumni(EUniversity education, int page, int size);

}
