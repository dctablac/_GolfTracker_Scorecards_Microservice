package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerModel {
    @JsonProperty(value = "name", required = true)
    private String NAME;
    @JsonProperty(value = "score_front", required = true)
    private Integer SCOREFRONT;
    @JsonProperty(value = "score_back", required = true)
    private Integer SCOREBACK;
    @JsonProperty(value = "score_total", required = true)
    private Integer SCORETOTAL;
    @JsonProperty(value = "score_handicap", required = true)
    private Integer SCOREHANDICAP;
    @JsonProperty(value = "strokes", required = true)
    private Integer[] STROKES;

    @JsonCreator
    public PlayerModel(@JsonProperty(value = "name", required = true) String name,
                       @JsonProperty(value = "score_front", required = true) Integer scorefront,
                       @JsonProperty(value = "score_back", required = true) Integer scoreback,
                       @JsonProperty(value = "score_total", required = true) Integer scoretotal,
                       @JsonProperty(value = "score_handicap", required = true) Integer scorehandicap,
                       @JsonProperty(value = "strokes", required = true) Integer[] strokes) {
        this.NAME = name;
        this.SCOREFRONT = scorefront;
        this.SCOREBACK = scoreback;
        this.SCORETOTAL = scoretotal;
        this.SCOREHANDICAP = scorehandicap;
        this.STROKES = strokes;
    }

    @JsonProperty(value = "name")
    public String getName() {
        return this.NAME;
    }

    @JsonProperty(value = "score_front")
    public Integer getScoreFront() {
        return this.SCOREFRONT;
    }

    @JsonProperty(value = "score_back")
    public Integer getScoreBack() {
        return this.SCOREBACK;
    }

    @JsonProperty(value = "score_total")
    public Integer getScoreTotal() {
        return this.SCORETOTAL;
    }

    @JsonProperty(value = "score_handicap")
    public Integer getScoreHandicap() {
        return this.SCOREHANDICAP;
    }

    @JsonProperty(value = "strokes")
    public Integer[] getStrokes() {
        return this.STROKES;
    }
}