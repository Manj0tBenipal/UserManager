package ca.sheridancollege.sin13014.finalexam_manjot_singh.controllers;

import ca.sheridancollege.sin13014.finalexam_manjot_singh.beans.Programmer;
import ca.sheridancollege.sin13014.finalexam_manjot_singh.repos.ProgrammerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProgrammerRestController {
    @Autowired
    private ProgrammerRepo progRepo;
    @PostMapping(value = {"/programmers"}, headers={"Content-type=application/json"})
    public Programmer addProgrammer(@RequestBody Programmer programmer){

        progRepo.addProgrammer(programmer);
        return progRepo.getProgrammers().get(progRepo.getProgrammers().size() -1 );
    }
    @GetMapping(value={"/programmers"})
    public ArrayList<Programmer> home(){
        return progRepo.getProgrammers();
    }
    @GetMapping(value={"/programmers/{id}"})
    public Programmer home(@PathVariable int id){
        return progRepo.getProgrammerById(id);
    }
    @PutMapping(value={"/programmers/{id}"},headers={"Content-type=application/json"} )
    public double updateStudentCollection( @PathVariable int id){
        Programmer programmer = progRepo.getProgrammerById(id);
        double tax = 0;
        if(programmer.getSalary() < 40000){
            tax = ( programmer.getSalary() * 0.02);
        }else if(programmer.getSalary() > 40000 && programmer.getSalary() < 100000){
            tax = 8000.00 + ((programmer.getSalary()-40000) * 0.03);
        }else if(programmer.getSalary() > 100000 ){
            tax = 26000.00 + ((programmer.getSalary()-40000) * 0.04);
        }
        return Math.round(tax * 100 /100);
    }

}
