package com.francoisgib.project_service.organizations;

import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.organizations.models.OrganizationCreationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class OrganizationService {
	private final OrganizationRepository organizationRepository;

	public List<Organization> getAllOrganizations() {
		return organizationRepository.findAll();
	}

	public Organization getOrganizationById(int id) {
		return organizationRepository.findById(id).orElseThrow();
	}

	public void deleteAllOrganizations() {
		organizationRepository.deleteAll();
	}

	public Organization createOrganization(OrganizationCreationForm organizationCreationForm) {
		Organization organization = Organization.builder()
			.name(organizationCreationForm.getOrganizationName())
			.users(Set.of())
			.projects(Set.of())
			.build();
		return organizationRepository.save(organization);
	}
}
