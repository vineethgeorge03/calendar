package com.example.calendar.calendarscheduler;

import com.example.calendar.entities.Calendar;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LockPoller {
    @PersistenceContext
    private EntityManager entityManager;


    //@Scheduled(fixedDelay = 2000)
    //@Transactional
    public void poll() {
        System.out.println("LOGGING BEGIN");
        List calendarList = entityManager.createQuery("select c from Calendar c where c.status='in_progress'")
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                                .getResultList();
        System.out.println(calendarList);
        System.out.println("LOGGING END");
    }
}
