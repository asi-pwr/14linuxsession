package tk.julianjurec.linuxsession14.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MediaResponse {

    @SerializedName("medias")
    @Expose
    private List<Media> medias = null;

    public MediaResponse() {
        //No args constructor for use in serialization
    }

    public MediaResponse(List<Media> medias) {
        super();
        this.medias = medias;
    }

    public List<Media> getMedias() {
        return medias;
    }
}
