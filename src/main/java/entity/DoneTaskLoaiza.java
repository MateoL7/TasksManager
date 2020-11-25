package entity;

import java.util.Date;

public class DoneTaskLoaiza {
    private int id;
    private Date date;
    private String description;

    public DoneTaskLoaiza() {
    }

    public DoneTaskLoaiza(int id, Date date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
