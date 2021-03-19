package sd.a1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sd.a1.models.Fighter;
import sd.a1.repositories.FighterRepository;

@Controller
@RequestMapping(path="/fighter")
public class FighterController {

    @Autowired
    private FighterRepository fighterRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewFighter (@RequestParam String first, @RequestParam String last, @RequestParam String weight) {
        Fighter fighter = new Fighter();
        fighter.setLastName(last);
        fighter.setFirstName(first);
        fighter.setWeightClass(weight);
        fighterRepository.save(fighter);
        return "Saved";
    }

    @GetMapping(path="/list")
    public @ResponseBody Iterable<Fighter> getAllFighters() {
        return fighterRepository.findAll();
    }

    @GetMapping(path="/find/{id}")
    public @ResponseBody Fighter findFighterById(@PathVariable Integer id) {
        return fighterRepository.findFighterById(id);
    }

    @GetMapping(path = "/findByName/{firstName}")
    public @ResponseBody Iterable<Fighter> findFighterByFirstName(@PathVariable String firstName){
        return fighterRepository.findFighterByFirstName(firstName);
    }
}
