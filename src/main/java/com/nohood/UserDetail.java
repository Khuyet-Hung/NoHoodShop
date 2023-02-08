package com.nohood;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.entity.ROLE;
import com.nohood.duantotnghiep.entity.ROLEACC;
import com.nohood.duantotnghiep.service.ACCOUNTSERVICE;
import com.nohood.duantotnghiep.service.ROLEACCSERVICE;
import com.nohood.duantotnghiep.service.ROLESERVICE;


@Service
public class UserDetail  implements UserDetailsService {
	@Autowired
	BCryptPasswordEncoder pe;
	@Autowired
	ACCOUNTSERVICE service;
	@Autowired
	ROLEACCSERVICE accservice;
	@Autowired
	ROLESERVICE roleservice;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			ACCOUNT acc = service.findone(username);
			String password = acc.getPASSWORD();
			String[] roles = acc.getRoleaccs().stream().map(au -> au.getRole().getROLEID())
					.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername(username).password(pe.encode(password)).roles(roles).build();
		}catch(Exception e) {
			System.out.println(e);
			throw new UsernameNotFoundException(username+" Not found");
		}
	
	}
	 
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		ACCOUNT account = new ACCOUNT();
		ROLEACC accrole = new ROLEACC();
		ROLE role = roleservice.findone("CUST");
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		account.setEMAIL(email);
		account.setPASSWORD(password);
		account.setUSERNAME(email);
		service.create(account);
		accrole.setRole(role);
		accrole.setAccount(account);
		accservice.create(accrole);
		UserDetails user = User.withUsername(email).password(pe.encode(password)).roles("CUST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
