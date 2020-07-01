package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionResponseModel extends ResponseModel {

    @JsonProperty(value = "sessionId")
    private String SESSIONID;

    @JsonCreator
    public SessionResponseModel(@JsonProperty(value = "resultCode", required = true) Integer resultCode, 
                         @JsonProperty(value = "message", required = true) String message,
                         @JsonProperty(value = "sessionId") String sessionId) {
        super(resultCode, message);
        this.SESSIONID = sessionId;
    }

    @JsonProperty(value = "sessionId")
    public String getSessionId() {
        return this.SESSIONID;
    }
}