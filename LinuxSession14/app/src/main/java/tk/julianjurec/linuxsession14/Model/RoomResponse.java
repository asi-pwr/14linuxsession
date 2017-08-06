package tk.julianjurec.linuxsession14.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RoomResponse implements Serializable{
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;
    public RoomResponse() {
    }

    public RoomResponse(List<Room> rooms) {
        super();
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
