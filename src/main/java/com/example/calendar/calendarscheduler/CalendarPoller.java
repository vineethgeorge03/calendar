package com.example.calendar.calendarscheduler;

import com.example.calendar.entities.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

@Component
@Persistent
public class CalendarPoller {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
//    @PersistenceContext
//    EntityManager entityManager;


//    @Scheduled(fixedDelay = 10000)
//    @Transactional
//    public void poll() {
//        System.out.println("LOGGING BEGIN");
//        // EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<Calendar> calendarList = new ArrayList();
//       // entityManager.getTransaction().begin();
//        calendarList = (List) entityManager.createNativeQuery("select * from calendar c where status='in_progress' for update").getResultList();
//        entityManager.createNativeQuery("update calendar set status='in_pending' where status = 'in_progress'").executeUpdate();
//
//        entityManager.getTransaction().commit();
//
//        // "select * from calendar c where status='in_pending' for update;update calendar set status='in_progress' where status = 'in_pending'"
//        // List calendarList =  entityManager.createNativeQuery("select * from calendar c where status='in_pending' for update;").getResultList();
//        System.out.println(calendarList);
//        System.out.println("LOGGING END");
//    }

    private EntityManager getEntityManager() {
        if(entityManager != null) {
            return entityManager;
        }
        return entityManagerFactory.createEntityManager();


    }
   /* @Scheduled(fixedDelay = 2000)
    public void poll() {
        System.out.println("LOGGING BEGIN");
        EntityManager entityManager = getEntityManager();
        boolean managingTransaction = !entityManager.getTransaction().isActive();
        List<Calendar> calendarList = new ArrayList();
        if (managingTransaction) {
            entityManager.getTransaction().begin();
            calendarList = (List) entityManager.createNativeQuery("select * from calendar c where status='in_pending' for update").getResultList();
            entityManager.createNativeQuery("update calendar set status='in_progress' where status = 'in_pending'").executeUpdate();
        }
        if (managingTransaction) {
            entityManager.getTransaction().commit();
        }

        // "select * from calendar c where status='in_pending' for update;update calendar set status='in_progress' where status = 'in_pending'"
        // List calendarList =  entityManager.createNativeQuery("select * from calendar c where status='in_pending' for update;").getResultList();
        System.out.println(calendarList);
        System.out.println("LOGGING END");
    }*/
}
