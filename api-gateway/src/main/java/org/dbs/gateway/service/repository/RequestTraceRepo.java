package org.dbs.gateway.service.repository;

import org.dbs.gateway.service.entity.RequestTrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTraceRepo extends JpaRepository<RequestTrace, Integer> {

}
