package francoisgib.project_service.owners.organizations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrganizationService {
	private final OrganizationRepository organizationRepository;

	public Flux<Organization> getAllOrganizations() {
		return organizationRepository.findAll();
	}

	public Mono<Organization> getOrganizationByName(final String name) {
		return organizationRepository.findByName(name).orElseThrow();
	}

	public Mono<Organization> createOrganization(String organizationName) {
		return organizationRepository.save(new Organization(organizationName));
	}
}
