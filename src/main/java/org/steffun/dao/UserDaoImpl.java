package org.steffun.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public void saveUser(User user) {
        em.persist(user);
        em.flush();
    }

    @Transactional
    @Override
    public User show(long id) {
        User user = (User) em.createQuery("FROM User WHERE id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return user;
    }

    @Transactional
    @Override
    public void update(User userUpdated, long id) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(userUpdated.getName());
        userToBeUpdated.setLastName(userUpdated.getLastName());
        userToBeUpdated.setAge(userUpdated.getAge());
        em.persist(userToBeUpdated);
        em.flush();
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        em.remove(show(id));
        em.flush();
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return em.createQuery("FROM User").getResultList();
    }

}