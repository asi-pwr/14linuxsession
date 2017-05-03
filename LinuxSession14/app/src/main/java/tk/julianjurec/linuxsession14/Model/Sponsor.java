package tk.julianjurec.linuxsession14.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sponsor {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;

    public Sponsor() {
        //No args constructor for use in serialization
    }

    public Sponsor(String category, Integer id, String imgUrl, String link, String name) {
        super();
        this.category = category;
        this.id = id;
        this.imgUrl = imgUrl;
        this.link = link;
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}