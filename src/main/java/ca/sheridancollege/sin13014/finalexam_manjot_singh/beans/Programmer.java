package ca.sheridancollege.sin13014.finalexam_manjot_singh.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programmer {
    private long id;
    private String name;
    private int yearsExperience;
    private double salary;



}