package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScorecardResponseModel extends ResponseModel {
    
    @JsonProperty(value = "yards", required = true)
    private YardModel YARDS;
    @JsonProperty(value = "pars", required = true)
    private ParModel PARS;
    @JsonProperty(value = "players", required = true)
    private PlayersModel PLAYERS;


    @JsonCreator
    public ScorecardResponseModel(@JsonProperty(value = "resultCode", required = true) int resultCode,
                                  @JsonProperty(value = "message", required = true) String message,
                                  @JsonProperty(value = "yards", required = true) YardModel yards,
                                  @JsonProperty(value = "pars", required = true) ParModel pars,
                                  @JsonProperty(value = "players", required = true) PlayersModel players) {
        super(resultCode, message);
        this.YARDS = yards;
        this.PARS = pars;
        this.PLAYERS = players;
    }

    @JsonProperty(value = "yards")
    public YardModel getYards() {
        return this.YARDS;
    }

    @JsonProperty(value = "pars")
    public ParModel getPars() {
        return this.PARS;
    }

    @JsonProperty(value = "players")
    public PlayersModel getPlayers() {
        return this.PLAYERS;
    }
}
