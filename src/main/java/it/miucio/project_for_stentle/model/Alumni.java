package it.miucio.project_for_stentle.model;

import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "alumni")
public class Alumni {
    private String name;
    private List<Address> addresses;
    private Map<EUniversity, University> education;

    @Version
    int version = 0;

    public Alumni(String name) {
        this.name = name;
        addresses = new ArrayList<>();
        education = new HashMap<>();
    }
}
