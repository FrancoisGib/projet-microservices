package francoisgib.project_service.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Slf4j
@Configuration
class ApplicationConfiguration {
	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		final ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		initializer.setDatabasePopulator(populator);

		populator.addScript(new FileSystemResource("db/organization.sql"));
		return initializer;
	}
}