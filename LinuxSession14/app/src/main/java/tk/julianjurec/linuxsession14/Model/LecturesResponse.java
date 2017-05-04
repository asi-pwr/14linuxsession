package tk.julianjurec.linuxsession14.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LecturesResponse implements Serializable
{

    @SerializedName("lectures")
    @Expose
    private List<Lecture> lectures = null;
    public LecturesResponse() {
    }

    public LecturesResponse(List<Lecture> lectures) {
        super();
        this.lectures = lectures;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

}