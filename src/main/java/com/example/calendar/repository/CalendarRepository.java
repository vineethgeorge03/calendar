package com.example.calendar.repository;

import com.example.calendar.entities.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, String> {
    @Query(value = "select * from calendar c where status='in_pending' for update;update calendar set status='in_progress' where status = 'in_pending'", nativeQuery = true)
    List<Calendar> getRecords();
}
