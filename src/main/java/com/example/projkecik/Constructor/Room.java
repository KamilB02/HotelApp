package com.example.projkecik.Constructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**

 The Room class represents a room in a hotel .


 */
@Entity
public class Room {
    /**
     The unique identifier for a Room object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     The name of the room.
     */
    private String RoomName;
    /**

     The type of the room (e.g. deluxe,superior etc.).
     */
    private String RoomType;
    /**

     The maximum number of people that the room can accommodate.
     */
    private Long MaxPerson;
    /**

     A brief description of the room.
     */
    private String Description;

    /**

     A set of reservations associated with the room.
     */

    @OneToMany(mappedBy = "room",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Reservation> reservationSet = new HashSet<>();
    /**

     Constructs an empty Room object.
     */
    public Room() {

    }
    /**

     Constructs a Room object with the specified name, type, maximum capacity, and description.
     @param RoomName the name of the room
     @param RoomType the type of the room
     @param MaxPerson the maximum number of people the room can accommodate
     @param Description a brief description of the room
     */
    public Room(String RoomName, String RoomType, Long MaxPerson, String Description ) {
       this.RoomName = RoomName;
       this.RoomType = RoomType;
       this.MaxPerson = MaxPerson;
       this.Description = Description;
    }
    /**

     Returns the unique identifier of the Room object.
     @return the id of the Room object
     */


    public Long getId() {
        return id;
    }
    /**

     Sets the unique identifier of the Room object.
     @param id the new id for the Room object
     */

    public void setId(Long id) {
        this.id = id;
    }
    /**

     Returns the name of the room.
     @return the name of the room
     */

    public String getRoomName() {
        return RoomName;
    }
    /**

     Sets the name of the room.
     @param roomName the new name of the room
     */
    public void setRoomName(String roomName) {
        RoomName = roomName;
    }
    /**

     Returns the type of the room.
     @return the type of the room
     */
    public String getRoomType() {
        return RoomType;
    }
    /**

     Sets the type of the room.
     @param roomType the new type of the room
     */
    public void setRoomType(String roomType) {
        RoomType = roomType;
    }
    /**

     Returns the maximum number of people that can occupy the room
     @return Long value of the room's maximum occupancy
     */
    public Long getMaxPerson() {
        return MaxPerson;
    }
    /**
     * Sets the maximum number of people that can occupy the room
     *
     * @param maxPerson Long value of the room's maximum occupancy
     */
    public void setMaxPerson(Long maxPerson) {
        MaxPerson = maxPerson;
    }
    /**
     * Returns the description of the room's amenities and features
     *
     * @return String value of the room's description
     */
    public String getDescription() {
        return Description;
    }
    /**
     * Sets the description of the room's amenities and features
     *
     * @param description String value of the room's description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Returns a string representation of the room's name, type, and maximum occupancy
     *
     * @return String representation of the room
     */


    @Override
    public String toString() {
        return
                  RoomName + " " + RoomType+"\n Maksymalna ilośc osób: "+MaxPerson ;
    }
}
