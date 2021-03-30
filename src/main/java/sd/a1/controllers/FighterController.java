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
        fighter.setQuarantineStatus("Yes");
        fighter.setMatched("No");
        fighterRepository.save(fighter);
        TestResult testResult = new TestResult();
        testResult.setFighterId(fighter);
        testResult.setStatus(fighter.test());
        testResult.setDate(new Date(1616623200000L)); // 25-Mar-2021
        testResultsRepository.save(testResult);
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
        model.addAttribute("fighter", fighterRepository.findFighterById(id));
        return "test_results";
    }

    @RequestMapping(value={"/results/r"}, method = RequestMethod.POST)
    public String redirectResults(@RequestParam String firstName, @RequestParam String lastName){
        Fighter fighter = fighterRepository.findFighterByFirstNameAndLastName(firstName, lastName);
        return "redirect:/fighter/results/" + fighter.getId();
    }

//    @PostMapping(path="/add/result")
//    public String newTestResult(@RequestParam Integer id, @RequestParam String status,
//                                @RequestParam Date date, Model model){
//        Fighter fighter = fighterRepository.findFighterById(id);
//        fighter.test();
//        fighterRepository.save(fighter);
//        TestResult testResult = new TestResult();
//        testResult.setFighterId(fighter);
//        testResult.setStatus(status);
//        testResult.setDate(date);
//        testResultsRepository.save(testResult);
//        model.addAttribute("results", testResult);
//        return "redirect:/fighter/results/" + id;
//    }

    @PostMapping(path="/add/result")
    public String newTestResult(@RequestParam Integer id, Model model){
        Fighter fighter = fighterRepository.findFighterById(id);
        TestResult testResult = new TestResult();
        testResult.setFighterId(fighter);
        testResult.setStatus(fighter.test());
        long time = testResultsRepository.findMaxDate(fighter).getTime() + 604800000;
        testResult.setDate(new Date(time));
        testResultsRepository.save(testResult);
        model.addAttribute("results", testResult);
        return "redirect:/fighter/results/" + id;
    }

    @GetMapping(path="/bubble")
    public String bubble(Model model) {
        model.addAttribute("fighters", fighterRepository.findAll());
        return "bubble";
    }

}
