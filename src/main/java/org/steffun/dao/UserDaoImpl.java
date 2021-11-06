package org.steffun.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.steffun.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    @Override
    public void saveUser(String name, String lastName, int age) {
        try (Session session = entityManagerFactory.createEntityManager()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        try (Session session = entityManagerFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void setUserName(long id, String name) {
        try (Session session = entityManagerFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("UPDATE User SET name = :name WHERE id = :id")
                    .setParameter("id", id)
                    .setParameter("name", name)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void setUserLastName(long id, String lastName) {
        try (Session session = entityManagerFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("UPDATE User SET lastName = :lastName WHERE id = :id")
                    .setParameter("id", id)
                    .setParameter("lastName", lastName)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void setUserAge(long id, int age) {
        try (Session session = entityManagerFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("UPDATE User SET age = :age WHERE id = :id")
                    .setParameter("id", id)
                    .setParameter("age", age)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = entityManagerFactory.openSession().createQuery("from User");
        return query.getResultList();
    }

}
