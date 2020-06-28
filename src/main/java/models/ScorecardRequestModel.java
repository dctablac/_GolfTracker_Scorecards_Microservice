package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScorecardRequestModel extends RequestModel {
    @JsonProperty(value = "course_name", required = true)
    private String COURSENAME;
    @JsonProperty(value = "play_date", required = true)
    private String PLAYDATE;
    @JsonProperty(value = "yards", required = true)
    private YardModel YARDS;
    @JsonProperty(value = "pars", required = true)
    private ParModel PARS;
    @JsonProperty(value = "players", required = true)
    private PlayersModel PLAYERS;

    @JsonCreator
    public ScorecardRequestModel(@JsonProperty(value = "email", required = true) String email,
                                 @JsonProperty(value = "course_name", required = true) String courseName,
                                 @JsonProperty(value = "play_date", required = true) String playDate,
                                 @JsonProperty(value = "yards", required = true) YardModel yards,
                                 @JsonProperty(value = "pars", required = true) ParModel pars,
                                 @JsonProperty(value = "players", required = true) PlayersModel players) {
        super(email);
        this.COURSENAME = courseName;
        this.PLAYDATE = playDate;
        this.YARDS = yards;
        this.PARS = pars;
        this.PLAYERS = players;
    }

    @JsonProperty(value = "course_name")
    public String getCourseName() {
        return this.COURSENAME;
    }

    @JsonProperty(value = "play_date")
    public String getPlayDate() {
        return this.PLAYDATE;
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