package com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.repos;

import com.zephyr_jarvis.transport_issue_reporting_platform.issue_report.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepo extends JpaRepository<Route,Long> {
}
