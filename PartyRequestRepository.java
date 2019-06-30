package com.sc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRequestRepository extends JpaRepository<PartyRequest, Long>{

}
