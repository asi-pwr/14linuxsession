package tk.julianjurec.linuxsession14.Model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Lecture extends SugarRecord implements Serializable
{

    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("uId")
    @Expose
    private Integer uId;
    @SerializedName("speaker_id")
    @Expose
    private Integer speakerId;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("title")
    @Expose
    private String title;
    public Lecture() {
    }

    public Lecture(Integer day, String description, String endTime, Integer uId, Integer speakerId, String startTime, String title) {
        super();
        this.day = day;
        this.description = description;
        this.endTime = endTime;
        this.uId = uId;
        this.speakerId = speakerId;
        this.startTime = startTime;
        this.title = title;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public Integer getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}