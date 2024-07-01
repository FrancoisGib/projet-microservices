package francoisgib.project_service.owners.organizations;

import francoisgib.project_service.owners.organizations.models.CreateOrganizationForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/organization")
public class OrganizationController {
	private final OrganizationService organizationService;

	@GetMapping
	public Flux<Organization> getAllOrganizations() {
		log.info("Retrieving all agencies");
		return organizationService.getAllOrganizations();
	}

	@GetMapping("/{name}")
	public Mono<Organization> getOrganizationByName(@PathVariable final String name) {
		return organizationService.getOrganizationByName(name);
	}

	@PostMapping
	public Mono<ResponseEntity<Organization>> createOrganization(@RequestBody CreateOrganizationForm createOrganizationForm) {
		return organizationService.createOrganization(createOrganizationForm.getName())
			.map(organization -> ResponseEntity.ok().body(organization))
			.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}