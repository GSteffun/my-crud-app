package org.steffun.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.steffun.model.Role;
import org.steffun.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManagerFactory em) {
        this.em = em.createEntityManager();
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
        em.flush();
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
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

    @Override
    public User getByName(String username) {
        return em.createQuery("FROM User user WHERE user.username = : username ", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public Set<Role> getUserRoles(User user) {
        return em.find(User.class, user.getId()).getRoles();
    }

}