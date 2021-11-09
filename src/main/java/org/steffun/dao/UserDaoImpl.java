package org.steffun.dao;

import org.springframework.stereotype.Repository;
import org.steffun.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager em;

    public UserDaoImpl(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
        em.flush();
    }

    @Override
    public User getUserById(long id) {
        User user = (User) em.createQuery("FROM User WHERE id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return user;
    }

    @Override
    public void update(User userUpdated) {
        em.merge(userUpdated);
    }

    @Override
    public void removeUserById(long id) {
        em.remove(getUserById(id));
        em.flush();
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("FROM User").getResultList();
    }

}