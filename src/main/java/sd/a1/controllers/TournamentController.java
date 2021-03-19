package sd.a1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sd.a1.models.Tournament;
import sd.a1.repositories.TournamentRepository;

import java.sql.Date;

@RestController
@RequestMapping(path = "/tournament")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewTournament(@RequestParam String name, @RequestParam Date date){
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setDate(date);
        tournamentRepository.save(tournament);
        return "Saved new tournament";
    }

    @GetMapping(path = "/list")
    public @ResponseBody Iterable<Tournament> getAllTournaments(){
        return tournamentRepository.findAll();
    }

}
