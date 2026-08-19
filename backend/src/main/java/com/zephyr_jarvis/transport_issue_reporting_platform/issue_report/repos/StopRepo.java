package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepo extends JpaRepository<Stop, Long> {
}
