package francoisgib.project_service.owners.organizations;

import java.util.Optional;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface OrganizationRepository extends ReactiveCrudRepository<Organization, Long> {
	Optional<Mono<Organization>> findByName(final String name);
}
