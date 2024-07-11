package com.francoisgib.project_service.organizations;

import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.organizations.models.OrganizationCreationForm;
import com.francoisgib.project_service.users.Role;
import com.francoisgib.project_service.users.User;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OrganizationService {
	private final OrganizationRepository organizationRepository;

	public Flux<Organization> getAllOrganizations() {
		return organizationRepository.findAll();
	}

	public Mono<Void> deleteAllOrganizations() {
		return organizationRepository.deleteAll();
	}

	public Mono<Organization> createOrganization(OrganizationCreationForm organizationCreationForm) {
		Organization organization = Organization.builder()
			.name(organizationCreationForm.getName())
			.users(Set.of(new User(organizationCreationForm.getOwnerId(), Role.ADMIN)))
			.build();
		return organizationRepository.save(organization);
	}
}
