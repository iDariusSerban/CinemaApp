package com.springapps.cinema.C_repository;

import com.springapps.cinema.D_model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection,Long> {
}
