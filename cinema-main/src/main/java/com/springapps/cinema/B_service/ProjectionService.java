package com.springapps.cinema.B_service;

import com.springapps.cinema.C_repository.ProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectionService {
    private ProjectionRepository projectionRepository;
    @Autowired
    public ProjectionService(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }
}
