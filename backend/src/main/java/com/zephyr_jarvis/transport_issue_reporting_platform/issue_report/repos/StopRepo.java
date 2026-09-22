package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StopRepo extends JpaRepository<Stop, Long> {
    Optional<List<Stop>> findByStationId(Long id);
}
