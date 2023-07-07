package com.example.projkecik.Constructor;

import javax.persistence.*;
import java.time.LocalDate;
/**

 Reservation class represents a reservation made in a hotel reservation system.

 */
@Entity
public class Reservation {
    /**

Unique identifier for the reservation
*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
Start date of the reservation
*/
    private LocalDate rezstart;
    /**
End date of the reservation
*/
    private LocalDate rezstop;
    /**
Room associated with the reservation
*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;
    /**

     Guest associated with the reservation
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_id")
    private Guest guest;
    /**

     Number of guests for the reservation
     */
    private Long iloscgosci;
    /**

     Default constructor for the Reservation class
     */
    public Reservation() {
    }
    /**

     Returns the id of the reservation
     @return id of the reservation
     */
    public Long getId() {
        return id;
    }
    /**

     Sets the id of the reservation
     @param id id to be set for the reservation
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**

     Returns the start date of the reservation
     @return start date of the reservation
     */
    public LocalDate getRezstart() {
        return rezstart;
    }
    /**

     Sets the start date of the reservation
     @param rezstart start date to be set for the reservation
     */
    public void setRezstart(LocalDate rezstart) {
        this.rezstart = rezstart;
    }
    /**

     Returns the end date of the reservation
     @return end date of the reservation
     */
    public LocalDate getRezstop() {
        return rezstop;
    }
    /**

     Sets the end date of the reservation
     @param rezstop end date to be set for the reservation
     */
    public void setRezstop(LocalDate rezstop) {
        this.rezstop = rezstop;
    }
    /**

     Returns the room associated with the reservation
     @return room associated with the reservation
     */
    public Room getRoom() {
        return room;
    }
    /**

     Sets the room associated with the reservation
     @param room room to be set for the reservation
     */
    public void setRoom(Room room) {
        this.room = room;
    }
    /**

     Returns the guest associated with the reservation
     @return guest associated with the reservation
     */
    public Guest getGuest() {
        return guest;
    }
    /**
     * Sets the guest associated with the reservation
     * @param guest guest to be set for the reservation
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    /**
     * Returns the number of guests for the reservation
     * @return number of guests for the reservation
     */
    public Long getIloscgosci() {
        return iloscgosci;
    }
    /**
     * Sets the number of guests for the reservation
     * @param iloscgosci number of guests to be set for the reservation
     */
    public void setIloscgosci(Long iloscgosci) {
        this.iloscgosci = iloscgosci;
    }
    /**
     * Returns a string representation of the reservation details
     * @return reservation details
     */
    @Override
    public String toString() {
        return "Rezerwacja{" +
                "id=" + id +
                ", rezstart=" + rezstart +
                ", rezstop=" + rezstop +
                ", room=" + room +
                ", guest=" + guest +
                '}';
    }
}
