package com.example.projkecik.Constructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 *Guest class represents a guest in a hotel reservation system.
 */
@Entity
public class Guest {
    /**

Unique identifier for the guest
*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
First name of the guest
*/
    private String imie;
/**
Last name of the guest
*/
    private  String nazwisko;
    /**
    Phone number of the guest
    */
    private Long nrtelefonu;
    /**

     Set of reservations made by the guest
     */



    @OneToMany(mappedBy = "guest",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Reservation> reservationSet = new HashSet<>();
/**

    Default constructor for the Guest class
*/



    public Guest() {}
    /**

     Returns the id of the guest
     @return id of the guest
     */

    public Long getId() {
        return id;
    }
    /**

     Sets the id of the guest
     @param id id to be set for the guest
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**

     Returns the first name of the guest
     @return first name of the guest
     */
    public String getImie() {
        return imie;
    }
    /**

     Sets the first name of the guest
     @param imie first name to be set for the guest
     */
    public void setImie(String imie) {
        this.imie = imie;
    }
    /**

     Returns the last name of the guest
     @return last name of the guest
     */
    public String getNazwisko() {
        return nazwisko;
    }
    /**

     Sets the last name of the guest
     @param nazwisko last name to be set for the guest
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
/**

 Returns the phone number of the guest
 @return phone number of the guest
 */
    public Long getNrtelefonu() {
        return nrtelefonu;
    }
    /**

     Sets the phone number of the guest
     @param nrtelefonu phone number to be set for the guest
     */
    public void setNrtelefonu(Long nrtelefonu) {
        this.nrtelefonu = nrtelefonu;
    }


    /**

     Returns a string representation of the guest's first name and last name
     @return guest's first name and last name
     */


    @Override
    public String toString() {
        return imie +"  "+nazwisko;
    }

}
