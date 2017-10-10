package entities;

/**
 * Created by mhristiniuc on 10/7/2017.
 */
public class DanceEntity {
    private int id;
    private int manId;
    private int womanId;
    private String style;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManId() {
        return manId;
    }

    public void setManId(int manId) {
        this.manId = manId;
    }

    public int getWomanId() {
        return womanId;
    }

    public void setWomanId(int womanId) {
        this.womanId = womanId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DanceEntity that = (DanceEntity) o;

        if (id != that.id) return false;
        if (manId != that.manId) return false;
        if (womanId != that.womanId) return false;
        if (style != null ? !style.equals(that.style) : that.style != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + manId;
        result = 31 * result + womanId;
        result = 31 * result + (style != null ? style.hashCode() : 0);
        return result;
    }
}
