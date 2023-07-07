package com.example.projkecik.D;

import com.example.projkecik.Constructor.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import com.example.projkecik.util.HibernateUtil;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**

 ReservationD class is used to perform CRUD operations on the Reservation class.

 It includes methods for creating, and deleting a reservation, as well as retrieving a list of all reservations and actual reservations.

 The class uses Hibernate to interact with the database.
 */
public class ReservationD {
    /**

Creates a new reservation and saves it to the database
@param reservation Reservation object to be created
@return true if the reservation is successfully created and saved, false otherwise
*/
    public boolean createReservation(Reservation reservation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
            return transaction.getStatus() == TransactionStatus.COMMITTED;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return false;
    }
    /**

     Deletes an existing reservation from the database
     @param reservation Reservation object to be deleted
     */

    public void deleteReservation(Reservation reservation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(reservation);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
    /**

     Retrieves a list of all reservations from the database
     @return List of Reservation objects
     */


    public List<Reservation> getReservations() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Reservation ", Reservation.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
    /**

     Retrieves a list of all actual reservations from the database
     @return List of Reservation objects that are currently active
     */
    public List<Reservation> getActualReservation() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Reservation> query = session.createQuery("SELECT c FROM Reservation c WHERE :date  BETWEEN rezstart AND rezstop", Reservation.class );
            query.setParameter("date", LocalDate.now());
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }




}
