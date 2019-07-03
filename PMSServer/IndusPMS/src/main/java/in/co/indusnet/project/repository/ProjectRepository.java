package in.co.indusnet.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.indusnet.project.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
