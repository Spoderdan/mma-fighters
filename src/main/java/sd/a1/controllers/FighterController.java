package sd.a1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sd.a1.models.Fighter;
import sd.a1.models.TestResult;
import sd.a1.repositories.FighterRepository;
import sd.a1.repositories.TestResultsRepository;
import sd.a1.repositories.TournamentRepository;

import java.sql.Date;

@Controller
@RequestMapping(path="/fighter")
public class FighterController {

    private final FighterRepository fighterRepository;
    private final TournamentRepository tournamentRepository;
    private final TestResultsRepository testResultsRepository;

    @Autowired
    public FighterController(FighterRepository fighterRepository, TournamentRepository tournamentRepository, TestResultsRepository testResultsRepository) {
        this.fighterRepository = fighterRepository;
        this.tournamentRepository = tournamentRepository;
        this.testResultsRepository = testResultsRepository;
    }

    @GetMapping(path="/")
    public String getAllFighters(Model model) {
        model.addAttribute("fighters", fighterRepository.findAll());
        return "fighters";
    }

    @PostMapping(path="/add")
    public String newFighter (@RequestParam String firstName, @RequestParam String lastName,
                              @RequestParam String weightClass, Model model) {
        Fighter fighter = new Fighter();
        fighter.setLastName(lastName);
        fighter.setFirstName(firstName);
        fighter.setWeightClass(weightClass);
        fighter.test();
        fighterRepository.save(fighter);
        model.addAttribute("fighter", fighter);
        return "redirect:/fighter/find/" + fighter.getId();
    }

    @GetMapping(path="/find/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("fighter", fighterRepository.findFighterById(id));
        model.addAttribute("tournaments", tournamentRepository.findAll());
        return "fighter";
    }

    @GetMapping(path = "results/{id}")
    public String findTestResults(@PathVariable Integer id, Model model){
        model.addAttribute("results", testResultsRepository.findByFighterId(fighterRepository.findFighterById(id)));
        model.addAttribute("fighters", fighterRepository.findAll());
        return "test_results";
    }

    @RequestMapping(value={"/rez"}, method = RequestMethod.POST)
    public String redirectResults(@RequestParam Integer id){
        return "redirect:/fighter/results/" + id;
    }

    @PostMapping(path="/add/result")
    public String newTestResult(@RequestParam Integer id, @RequestParam String status,
                                @RequestParam Date date, Model model){
        TestResult testResult = new TestResult();
        testResult.setFighterId(fighterRepository.findFighterById(id));
        testResult.setStatus(status);
        testResult.setDate(date);
        testResultsRepository.save(testResult);
        model.addAttribute("results", testResult);
        return "redirect:/fighter/results/" + id;
    }
}
