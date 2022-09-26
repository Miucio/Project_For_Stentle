package it.miucio.project_for_stentle.controller;

import io.swagger.annotations.ApiOperation;
import it.miucio.project_for_stentle.dto.AlumniSummary;
import it.miucio.project_for_stentle.model.Alumni;
import it.miucio.project_for_stentle.model.EUniversity;
import it.miucio.project_for_stentle.payload.request.AddAlumniRequest;
import it.miucio.project_for_stentle.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ex-1")
public class AlumniController {
    @Autowired
    private final AlumniService alumniService;

    @Autowired
    public AlumniController(AlumniService alumniService) {
        this.alumniService = alumniService;
    }

    @ApiOperation(value = "Insert alum", notes = "Insert new alum.")
    @PostMapping("/alumni")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Alumni> addAlumni(@Valid @RequestBody AddAlumniRequest addAlumniRequest) {
        return new ResponseEntity<>(alumniService.addAlumni(addAlumniRequest.getName(), addAlumniRequest.getAddresses(),
                addAlumniRequest.getEducation()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get alum.s", notes = "Get number of alumni and a list of them")
    @GetMapping("/get")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<AlumniSummary> getAlumni(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "education", required = false) EUniversity education,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "3") int size) {

        AlumniSummary alumniSummary = (name == null) ?
                alumniService.getAlumni(education, page, size) : alumniService.getAlumni(name, page, size);

        return alumniSummary.getTotalCount() != 0 ?
                new ResponseEntity<>(alumniSummary, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}