package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParModel {
    @JsonProperty(value = "par_front", required = true)
    private Integer PARFRONT;
    @JsonProperty(value = "par_back", required = true)
    private Integer PARBACK;
    @JsonProperty(value = "par_total", required = true)
    private Integer PARTOTAL;
    @JsonProperty(value = "par_handicap", required = true)
    private Integer PARHANDICAP;
    @JsonProperty(value = "par_list", required = true)
    private Integer[] PARLIST; // list 0-17, holes 1-18

    @JsonCreator
    public ParModel(@JsonProperty(value = "par_front", required = true) Integer parfront, 
                     @JsonProperty(value = "par_back", required = true) Integer parback,
                     @JsonProperty(value = "par_total", required = true) Integer partotal, 
                     @JsonProperty(value = "par_handicap", required = true) Integer parhandicap,
                     @JsonProperty(value = "par_list", required = true) Integer[] parlist) {
        this.PARFRONT = parfront;
        this.PARBACK = parback;
        this.PARTOTAL = partotal;
        this.PARHANDICAP = parhandicap;
        this.PARLIST = parlist;
    }

    @JsonProperty(value = "par_front")
    public Integer getParFront() {
        return this.PARFRONT;
    }

    @JsonProperty(value = "par_back")
    public Integer getParBack() {
        return this.PARBACK;
    }

    @JsonProperty(value = "par_total")
    public Integer getParTotal() {
        return this.PARTOTAL;
    }

    @JsonProperty(value = "par_handicap")
    public Integer getParHandicap() {
        return this.PARHANDICAP;
    }

    @JsonProperty(value = "par_list")
    public Integer[] getParList() {
        return this.PARLIST;
    }
}