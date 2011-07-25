package ch.ralscha.e4ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.ralscha.e4ds.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
