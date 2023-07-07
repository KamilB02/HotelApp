package com.example.projkecik.D;
import com.example.projkecik.Constructor.Guest;
import com.example.projkecik.Constructor.Room;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.ArrayList;
import java.util.List;
import com.example.projkecik.util.HibernateUtil;
/**

 RoomD class is used to perform CRUD operations on the Room class.

 It includes methods for creating a new room, and retrieving a list of all rooms.

 The class uses Hibernate to interact with the database.
 */
public class RoomD {
    /**

Creates a new room and saves it to the database
@param room Room object to be created
@return true if the room is successfully created and saved, false otherwise
*/
    public Boolean CreateRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(room);
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

     Updates an existing room in the database
     @param room Room object to be updated
     */
    public void updateRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    /**

     Retrieves a list of all rooms from the database
     @return List of Room objects
     */
    public List<Room> GetRooms() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Room ", Room.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
