package com.alexander.app.hibernate;

import com.alexander.app.hibernate.entity.Animal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AnimalHelper {
    private SessionFactory sessionFactory;

    public AnimalHelper(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Animal addAnimal(Animal animal){
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(animal);
        session.getTransaction().commit();
        session.close();

        return animal;
    }
}
