package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayersModel {
    @JsonProperty(value = "player_1", required = true)
    private PlayerModel PLAYER_1;
    @JsonProperty(value = "player_2")
    private PlayerModel PLAYER_2;
    @JsonProperty(value = "player_3")
    private PlayerModel PLAYER_3;
    @JsonProperty(value = "player_4")
    private PlayerModel PLAYER_4;

    @JsonCreator
    public PlayersModel(@JsonProperty(value = "player_1") PlayerModel player_1,
                        @JsonProperty(value = "player_2") PlayerModel player_2,
                        @JsonProperty(value = "player_3") PlayerModel player_3,
                        @JsonProperty(value = "player_4") PlayerModel player_4) {
        this.PLAYER_1 = player_1;
        this.PLAYER_2 = player_2;
        this.PLAYER_3 = player_3;
        this.PLAYER_4 = player_4;
    }

    @JsonProperty(value = "player_1")
    public PlayerModel getPlayer1() {
        return this.PLAYER_1;
    }

    @JsonProperty(value = "player_2")
    public PlayerModel getPlayer2() {
        return this.PLAYER_2;
    }    

    @JsonProperty(value = "player_3")
    public PlayerModel getPlayer3() {
        return this.PLAYER_3;
    }

    @JsonProperty(value = "player_4")
    public PlayerModel getPlayer4() {
        return this.PLAYER_4;
    }
}