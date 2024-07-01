package francoisgib.project_service.owners.organizations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table("organization")
public class Organization {
	@Id
	private Long id;

	@Column
	private String name;

	public Organization(String name) {
		this.name = name;
	}
}
