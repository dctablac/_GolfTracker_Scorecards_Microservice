package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YardModel {
    @JsonProperty(value = "yard_front", required = true)
    private Integer YARDFRONT;
    @JsonProperty(value = "yard_back", required = true)
    private Integer YARDBACK;
    @JsonProperty(value = "yard_total", required = true)
    private Integer YARDTOTAL;
    @JsonProperty(value = "yard_handicap", required = true)
    private Integer YARDHANDICAP;
    @JsonProperty(value = "yard_list", required = true)
    private Integer[] YARDLIST; // list 0-17, holes 1-18

    @JsonCreator
    public YardModel(@JsonProperty(value = "yard_front", required = true) Integer yardfront, 
                     @JsonProperty(value = "yard_back", required = true) Integer yardback,
                     @JsonProperty(value = "yard_total", required = true) Integer yardtotal, 
                     @JsonProperty(value = "yard_handicap", required = true) Integer yardhandicap,
                     @JsonProperty(value = "yard_list", required = true) Integer[] yardlist) {
        this.YARDFRONT = yardfront;
        this.YARDBACK = yardback;
        this.YARDTOTAL = yardtotal;
        this.YARDHANDICAP = yardhandicap;
        this.YARDLIST = yardlist;
    }

    @JsonProperty(value = "yard_front")
    public Integer getYardFront() {
        return this.YARDFRONT;
    }

    @JsonProperty(value = "yard_back")
    public Integer getYardBack() {
        return this.YARDBACK;
    }

    @JsonProperty(value = "yard_total")
    public Integer getYardTotal() {
        return this.YARDTOTAL;
    }

    @JsonProperty(value = "yard_handicap")
    public Integer getYardHandicap() {
        return this.YARDHANDICAP;
    }

    @JsonProperty(value = "yard_list")
    public Integer[] getYardList() {
        return this.YARDLIST;
    }
}