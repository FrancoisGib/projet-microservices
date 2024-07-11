package com.francoisgib.project_service.organizations;

import com.francoisgib.project_service.config.MQConfig;
import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.organizations.models.OrganizationCreationForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/organizations")
@RestController
public class OrganizationController {
	private final OrganizationService organizationService;

	private final RabbitTemplate rabbitTemplate;

	@GetMapping
	public Flux<Organization> getAllOrganizations() {
		rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, "Retrieving all organizations");
		return organizationService.getAllOrganizations();
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
