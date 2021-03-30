package sd.a1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sd.a1.models.Tournament;
import sd.a1.repositories.TournamentRepository;

import java.sql.Date;

@Controller
@RequestMapping(path = "/tournament")
public class TournamentController {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentController(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @GetMapping(path = "/")
    public String getAllTournaments(Model model){
        model.addAttribute("tournaments", tournamentRepository.findAll());
        return "tournaments";
    }

    @PostMapping(path = "/add")
    public String newTournament(@RequestParam String name, @RequestParam Date date, Model model){
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setDate(date);
        tournament.setFilled("No");
        tournamentRepository.save(tournament);
        model.addAttribute("tournament", tournament);
        return "redirect:/tournament/find/" + tournament.getId();
    }

    @GetMapping(path="/find/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("tournament", tournamentRepository.findTournamentById(id));
        return "tournament";
    }

}
