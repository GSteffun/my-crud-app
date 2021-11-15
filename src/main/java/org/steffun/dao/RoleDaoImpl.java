package org.steffun.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.steffun.model.Role;
import org.steffun.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public RoleDaoImpl(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    @Override
    public void add(Role role) {
        em.persist(role);
        em.flush();
    }

    @Override
    public void update(Role updatedRole) {
        em.merge(updatedRole);
    }

    @Override
    public Role getById(int id) {
        return em.find(Role.class, id);
    }

    @Override
    public void removeById(int id) {
        em.remove(getById(id));
        em.flush();
    }

    @Override
    public List<User> getListUser() {
        return em.createQuery("FROM Role").getResultList();
    }

    @Override
    public Role getByName(String roleName) {
        return em.createQuery("FROM Role role WHERE role.role = :role", Role.class)
                .setParameter("role", roleName)
                .getSingleResult();
    }

}
