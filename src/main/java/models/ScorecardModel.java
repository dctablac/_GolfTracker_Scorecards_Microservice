package models;

public class ScorecardModel {
    private Integer ID;
    private String COURSENAME;
    private String PLAYDATE;
    private String EMAIL;

    public ScorecardModel(Integer id, String courseName, 
                          String playDate, String email) {
        this.ID = id;
        this.COURSENAME = courseName;
        this.PLAYDATE = playDate;
        this.EMAIL = email;
    }

    public Integer getID() {
        return this.ID;
    }

    public String getCourseName() {
        return this.COURSENAME;
    }

    public String getPlayDate() {
        return this.PLAYDATE;
    }

    public String getEmail() {
        return this.EMAIL;
    }
}