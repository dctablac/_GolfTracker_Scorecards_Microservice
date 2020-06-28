import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import models.ResponseModel;
import models.ScorecardRequestModel;
import util.Utility;

@RestController
@EnableAutoConfiguration
@RequestMapping("scorecards")
public class Scorecard {

    @PostMapping("create")
    ResponseEntity<ResponseModel> newScorecard(@RequestBody ScorecardRequestModel requestModel) {
        return Utility.createScorecard(requestModel);
    }
    
    @RequestMapping("retrieve/{id}")
    String retrieveScorecard(@PathVariable("id") String id) {
        return String.format("Found scorecard %s", id);
    }

    @RequestMapping("retrieve")
    String allScorecards() {
        return "All scorecards";
    }

    @RequestMapping("update/{id}")
    String updateScorecard(@PathVariable("id") String id) {
        return String.format("Updating scorecard %s", id);
    }

    @RequestMapping("delete")
    String deleteScorecard() {
        return "Delete scorecard";
    }
    public static void main(String[] args) {
        SpringApplication.run(Scorecard.class, args);
    }

}
