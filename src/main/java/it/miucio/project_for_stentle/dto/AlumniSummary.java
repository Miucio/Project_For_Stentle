package it.miucio.project_for_stentle.dto;

import it.miucio.project_for_stentle.model.Alumni;
import lombok.Data;

import java.util.List;

@Data
public class AlumniSummary {
    private Long totalCount;
    private List<Alumni> data;

    public AlumniSummary(Long totalCount, List<Alumni> data) {
        this.totalCount = totalCount;
        this.data = data;
    }
}
