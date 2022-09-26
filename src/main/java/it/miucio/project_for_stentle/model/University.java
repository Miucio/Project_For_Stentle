package it.miucio.project_for_stentle.model;

import lombok.Data;

@Data
public class University {
    private String university;
    private String year;

    public University(String university, String year) {
        this.university = university;
        this.year = year;
    }
}
