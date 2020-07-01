package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteRequestModel extends RequestModel {
    @JsonProperty(value = "id", required = true)
    private int ID;

    @JsonCreator
    public DeleteRequestModel(@JsonProperty(value = "email", required = true) String email,
                              @JsonProperty(value = "id", required = true) int id) {
        super(email);
        this.ID = id;
    }

    @JsonProperty(value = "id")
    public int getId() {
        return this.ID;
    }
}