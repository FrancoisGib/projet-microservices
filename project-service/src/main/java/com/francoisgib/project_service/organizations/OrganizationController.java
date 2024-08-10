package com.francoisgib.project_service.organizations;

import com.francoisgib.project_service.MessageService;
import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.organizations.models.OrganizationCreationForm;
import com.francoisgib.project_service.organizations.models.OrganizationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/organizations")
public class OrganizationController {
	private final OrganizationService organizationService;

	private final MessageService messageService;

	@GetMapping
	public ResponseEntity<List<OrganizationDTO>> getAllOrganizations() {
		messageService.sendLogMessage("Retrieving all organizations");
		return ResponseEntity.ok(OrganizationMapper.INSTANCE.toDTO(organizationService.getAllOrganizations()));
	}

	@GetMapping("/{organizationId}")
	public ResponseEntity<Organization> getOrganizationById(@PathVariable int organizationId) {
		messageService.sendLogMessage("Retrieving organization by id: " + organizationId);
		return ResponseEntity.ok(organizationService.getOrganizationById(organizationId));
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAllOrganizations() {
		organizationService.deleteAllOrganizations();
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping
	public ResponseEntity<Organization> createOrganization(@Valid @RequestBody OrganizationCreationForm organizationCreationForm) {
		return ResponseEntity.ok(organizationService.createOrganization(organizationCreationForm));
	}
}
