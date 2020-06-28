package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerModel {
    @JsonProperty(value = "name", required = true)
    private String NAME;
    @JsonProperty(value = "strokes", required = true)
    private Integer[] STROKES;

    @JsonCreator
    public PlayerModel(@JsonProperty(value = "name", required = true) String name,
                       @JsonProperty(value = "strokes", required = true) Integer[] strokes) {
        this.NAME = name;
        this.STROKES = strokes;
    }

    @JsonProperty(value = "name")
    public String getName() {
        return this.NAME;
    }

    @JsonProperty(value = "strokes")
    public Integer[] getStrokes() {
        return this.STROKES;
    }
}