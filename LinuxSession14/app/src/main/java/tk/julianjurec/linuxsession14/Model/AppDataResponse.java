package tk.julianjurec.linuxsession14.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppDataResponse {

    @SerializedName("lectures")
    @Expose
    private List<Lecture> lectures = null;
    @SerializedName("speakers")
    @Expose
    private List<Speaker> speakers = null;
    @SerializedName("sponsors")
    @Expose
    private List<Sponsor> sponsors = null;
    @SerializedName("medias")
    @Expose
    private List<Media> medias = null;

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }
}

