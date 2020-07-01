package models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AllScorecardsResponseModel extends ResponseModel {

    @JsonProperty(value = "scorecards")
    private ArrayList<ScorecardModel> SCORECARDS;
    
    @JsonCreator
    public AllScorecardsResponseModel(@JsonProperty(value = "resultCode", required = true) Integer resultCode, 
                                      @JsonProperty(value = "message", required = true) String message,
                                      @JsonProperty(value = "scorecards") ArrayList<ScorecardModel> scorecards) {
        super(resultCode, message);
        this.SCORECARDS = scorecards;
    } 

    @JsonProperty(value = "scorecards")
    public ArrayList<ScorecardModel> getScorecards() {
        return this.SCORECARDS;
    }
}