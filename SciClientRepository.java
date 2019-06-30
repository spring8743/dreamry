package com.sc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SciClientRepository extends JpaRepository<SciClient, Long>{

}
