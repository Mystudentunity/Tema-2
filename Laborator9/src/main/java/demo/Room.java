package demo;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="room_name", nullable = false)
    private String roomName;

    @Column(name="room_state")
    private Boolean roomState;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "room_management",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    private Set<Track> trackSet;

    public Room(String roomName, Boolean roomState) {
        this.roomName = roomName;
        this.roomState = roomState;
    }

    public Room(){

    }

    public long getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public Boolean getRoomState() {
        return roomState;
    }

    public Set<Track> getTrackSet(){return trackSet;}

    public void addTrack(Track track) {
        trackSet.add(track);
    }
    public void removeTrack(Track track) {
        trackSet.remove(track);
    }

    public void update(Room room) {
        this.roomName = room.getRoomName();
    }
}
