package com.example.calendar.calendarscheduler;

import com.example.calendar.entities.Calendar;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Component
public class LockerEx {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    /*https://stackoverflow.com/questions/29765934/how-to-specify-lock-timeout-in-spring-data-jpa-query*/
    @Scheduled(fixedDelay = 2000)
    // @Transactional
    public void poll() {
        System.out.println("LOGGING BEGIN");
        EntityManager em = entityManagerFactory.createEntityManager();
        try {

            /*em.setProperty("javax.persistence.lock.timeout", 0);
            em.setProperty("javax.persistence.query.timeout", 1000);*/
            em.getTransaction().begin();

            TypedQuery<Calendar> q = (TypedQuery<Calendar>) em.createQuery("select c from Calendar c where c.status='in_pending'")
                    .setHint("javax.persistence.lock.timeout",0)
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE);
            List<Calendar> calendarList = q.getResultList();
            em.createQuery("update Calendar c set c.status = 'in_progress' where c.status = 'in_pending'").executeUpdate();
            System.out.println(calendarList);
            System.out.println(new Date().toString());
            System.out.println("LOGGING END");

        } catch (LockTimeoutException ex){
            System.out.println("LOCK TIMED OUT" + ex.getMessage());
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            em.getTransaction().commit();
            em.close();
        }
    }
}

/*
* select * from calendar.calendar

insert into calendar.calendar(id,date, message,status)
values(3,'2022-11-01','message', 'in_pending')

delete from calendar.calendar

begin;
select * from calendar.calendar where status = 'in_progress' for update;
update calendar.calendar set status='in_progress' where status = 'in_pending';
commit;

update calendar.calendar set status='in_pending' where status = 'in_pending';

select * from calendar.calendar where status = 'in_pending';



select * from calendar.calendar where status = 'in_progress' for update;
*
*
*
* https://stackoverflow.com/questions/53097329/select-json-column-using-jpa-native-query-with-memsql
* https://stackoverflow.com/questions/51276703/how-to-store-postgresql-jsonb-using-springboot-jpa
* https://stackoverflow.com/questions/17860696/not-allowed-to-create-transaction-on-shared-entitymanager-use-spring-transacti
* https://stackoverflow.com/questions/11173974/different-ways-of-getting-the-entitymanager
* https://allaroundjava.com/pessimistic-locking-hibernate/
* https://stackoverflow.com/questions/16204565/jpa-entity-specify-persistence-unit
* https://mobiarch.wordpress.com/2013/08/01/doing-select-for-update-in-jpa/
* https://stackoverflow.com/questions/6349421/how-to-use-jackson-to-deserialise-an-array-of-objects
* https://stackoverflow.com/questions/24572092/using-java-generics-for-jpa-findall-query-with-where-clause
* https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/pessimistic-lock.html
* https://stackoverflow.com/questions/1158159/no-persistence-provider-for-entitymanager-named
* https://www.baeldung.com/jpa-pessimistic-locking
* https://stackoverflow.com/questions/29765934/how-to-specify-lock-timeout-in-spring-data-jpa-query
* https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
* https://stackoverflow.com/questions/58546947/how-to-execute-multiple-jpa-queries-in-a-single-jdbc-connection
* https://asktom.oracle.com/pls/apex/f?p=100:11:0::::P11_QUESTION_ID:4070311835620
*/
