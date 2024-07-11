package com.francoisgib.project_service.organizations;

import com.francoisgib.project_service.organizations.models.Organization;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends ReactiveMongoRepository<Organization, String> {
}
