package it.miucio.project_for_stentle.payload.request;

import it.miucio.project_for_stentle.model.Address;
import it.miucio.project_for_stentle.model.EUniversity;
import it.miucio.project_for_stentle.model.University;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Data
public class AddAlumniRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    @Valid
    private String name;

    @Valid
    @NotEmpty
    private List<Address> addresses;

    @Valid
    private Map<EUniversity, University> education;
}
