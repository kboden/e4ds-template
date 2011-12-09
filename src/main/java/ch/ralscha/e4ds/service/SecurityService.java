package ch.ralscha.e4ds.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ch.ralscha.e4ds.config.JpaUserDetails;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

@Service
public class SecurityService {

	@ExtDirectMethod
	@PreAuthorize("isAuthenticated()")
	public String getLoggedOnUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof JpaUserDetails) {
			return ((JpaUserDetails) principal).getFullName();
		}
		return principal.toString();
	}

}
