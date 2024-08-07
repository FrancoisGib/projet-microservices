package com.francoisgib.project_service.organizations;

import com.francoisgib.project_service.MessageService;
import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.organizations.models.OrganizationCreationForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/organizations")
public class OrganizationController {
	private final OrganizationService organizationService;

	private final MessageService messageService;

	@GetMapping
	public Flux<Organization> getAllOrganizations() {
		messageService.sendLogMessage("Retrieving all organizations");
		return organizationService.getAllOrganizations();
	}

	@GetMapping("/{organizationId}")
	public Mono<Organization> getOrganizationById(@PathVariable String organizationId) {
		messageService.sendLogMessage("Retrieving organization by id: " + organizationId);
		return organizationService.getOrganizationById(organizationId);
	}

	@GetMapping("/{organizationId}/name")
	public Mono<String> getOrganizationNameById(@PathVariable String organizationId) {
		return organizationService.getOrganizationById(organizationId).map(Organization::getName);
	}

	@DeleteMapping
	public Mono<Void> deleteAllOrganizations() {
		return organizationService.deleteAllOrganizations();
	}

	@PostMapping
	public Mono<Organization> createOrganization(@RequestBody OrganizationCreationForm organizationCreationForm) {
		return organizationService.createOrganization(organizationCreationForm);
	}
}
