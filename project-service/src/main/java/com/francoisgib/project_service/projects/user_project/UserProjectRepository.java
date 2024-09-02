package com.francoisgib.project_service.projects.user_project;

import com.francoisgib.project_service.projects.models.Project;
import com.francoisgib.project_service.users.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectId>, JpaSpecificationExecutor<UserProject> {

    //@Query(value = "SELECT up FROM UserProject up WHERE up.userProjectId.user.id = :userId AND up.userProjectId.project.name ILIKE :nameFilter%")
    //Page<UserProject> findByUserIdAndFilterName(long userId, String nameFilter, Pageable page);

    static Specification<UserProject> userIdEquals(Long userId) {
        return (root, query, builder) -> builder.equal(root.<UserProjectId>get("userProjectId").<User>get("user").<Long>get("id"), userId);
    }

    static Specification<UserProject> projectNameStartsWith(String nameFilter) {
        return (root, query, builder) ->
                builder.like(
                        builder.lower(root.<UserProjectId>get("userProjectId")
                                .<Project>get("project")
                                .get("name")), nameFilter.toLowerCase() + "%");
    }
}
