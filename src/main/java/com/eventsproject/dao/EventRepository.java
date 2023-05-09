package com.eventsproject.dao;

import com.eventsproject.dao.counter.ByIpCounter;
import com.eventsproject.dao.counter.ByNameCounter;
import com.eventsproject.dao.counter.ByStatusCounter;
import com.eventsproject.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Calculate count of events with given name and date grouped by name
     *
     * @param date Date to filter by
     * @param name Name to filter by
     * @return List with single counter or empty list
     */
    @Query("select new com.eventsproject.dao.counter.ByNameCounter(e.name, count(e.name)) from Event as e "
            + "where e.name = :name and e.date = :date group by e.name")
    List<ByNameCounter> countByName(@Param("date") LocalDate date, @Param("name") String name);

    /**
     * Calculate count of events with given name and date grouped by ip
     *
     * @param date Date to filter by
     * @param name Name to filter by
     * @return List with counts mapped to ip
     */
    @Query("select new com.eventsproject.dao.counter.ByIpCounter(e.ip, count(e.ip)) from Event as e "
            + "where e.name = :name and e.date = :date group by e.ip")
    List<ByIpCounter> countByIp(@Param("date") LocalDate date, @Param("name") String name);

    /**
     * Calculate count of events with given name and date grouped by status
     *
     * @param date Date to filter by
     * @param name Name to filter by
     * @return List with counts mapped to status
     */
    @Query("select new com.eventsproject.dao.counter.ByStatusCounter(e.isAuthorized, count(e.isAuthorized)) from Event as e "
            + "where e.name = :name and e.date = :date group by e.isAuthorized")
    List<ByStatusCounter> countByStatus(@Param("date") LocalDate date, @Param("name") String name);

}
