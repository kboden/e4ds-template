package ch.ralscha.e4ds.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ch.ralscha.e4ds.config.JpaUserDetails;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

@Service
public class SecurityService {
	
	@ExtDirectMethod
	public String getLoggedOnUser() {
		return ((JpaUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
	}
}
