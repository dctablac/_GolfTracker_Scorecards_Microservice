import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import models.AllScorecardsResponseModel;
import models.DeleteRequestModel;
import models.ResponseModel;
import models.SessionResponseModel;
import models.ScorecardRequestModel;
import models.ScorecardResponseModel;
import util.Utility;

@RestController
@EnableAutoConfiguration
@RequestMapping("scorecards")
public class Scorecard {

    @PostMapping("create")
    ResponseEntity<SessionResponseModel> newScorecard(@RequestBody ScorecardRequestModel requestModel) {
        return Utility.createScorecard(requestModel);
    }
    
    @RequestMapping("retrieve/{id}")
    ResponseEntity<ScorecardResponseModel> retrieveScorecard(@PathVariable("id") int id, 
                                                             @RequestHeader("email") String email) {
        return Utility.scorecardDetails(id, email);
    }

    @GetMapping("retrieve")
    ResponseEntity<AllScorecardsResponseModel> allScorecards(@RequestHeader String email) {
        return Utility.allScorecards(email);
    }

    
    @PostMapping("update/{id}")
    ResponseEntity<ResponseModel> updateScorecard(@PathVariable("id") int id, 
                                                  @RequestBody ScorecardRequestModel requestModel) {
        return Utility.updateScorecard(id, requestModel);
    }

    @PostMapping("delete")
    ResponseEntity<ResponseModel> deleteScorecard(@RequestBody DeleteRequestModel requestModel) {
        return Utility.deleteScorecard(requestModel.getId());
    }
    public static void main(String[] args) {
        SpringApplication.run(Scorecard.class, args);
    }

}
