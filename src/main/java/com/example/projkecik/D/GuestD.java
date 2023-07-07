package com.example.projkecik.D;

import com.example.projkecik.Constructor.Guest;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.ArrayList;
import java.util.List;
import com.example.projkecik.util.HibernateUtil;
/**

 GuestD class is used to perform CRUD operations on the Guest class.

 It includes methods for creating, updating, and deleting a guest, as well as retrieving a list of all guests.

 The class uses Hibernate to interact with the database.
 */
public class GuestD {
    /**
Creates a new guest and saves it to the database
@param guest Guest object to be created
@return true if the guest is successfully created and saved, false otherwise
*/
    public Boolean createGuest(Guest guest) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        session.saveOrUpdate(guest);
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

     Updates an existing guest in the database
     @param guest Guest object to be updated
     */
    public void updateGuest(Guest guest) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(guest);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
    /**

     Deletes an existing guest from the database
     @param guest Guest object to be deleted
     */
    public void deleteGuest(Guest guest) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(guest);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
    /**

     Retrieves a list of all guests from the database
     @return List of Guest objects
     */

    public List<Guest> getGuests() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Guest ", Guest.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
