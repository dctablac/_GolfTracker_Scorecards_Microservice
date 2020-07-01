package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModel {
    
    @JsonProperty(value = "resultCode", required = true)
    private Integer RESULTCODE;
    @JsonProperty(value = "message", required = true)
    private String MESSAGE;

    @JsonCreator
    public ResponseModel(@JsonProperty(value = "resultCode", required = true) Integer resultCode,
                         @JsonProperty(value = "message", required = true) String message) {
        this.RESULTCODE = resultCode;
        this.MESSAGE = message;
    }

    @JsonProperty(value = "resultCode")
    public Integer getResultCode() {
        return this.RESULTCODE;
    }

    @JsonProperty(value = "message")
    public String getMessage() {
        return this.MESSAGE;
    }
}