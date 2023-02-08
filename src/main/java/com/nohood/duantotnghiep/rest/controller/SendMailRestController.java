package com.nohood.duantotnghiep.rest.controller;

import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.service.ACCOUNTSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/sendmail")
public class SendMailRestController {
	@Autowired
	JavaMailSender mailer;

	@PostMapping("{email}")
	public int getAll(@PathVariable("email") String email) {
		int code = Integer.parseInt(RandGeneratedStr(8));
		sendmail(email,code);
		return code;
	}
	
	
	private void sendmail(String email, int code) {
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom("SHOPNOHOOD", "sprologfile@gmail.com");
			helper.setTo(email);
			helper.setReplyTo("SHOPNOHOOD", "sprologfile@gmail.com");
			helper.setSubject("MÃ CODE XÁC NHẬN TÀI KHOẢN ");
			helper.setCc("sprologfile@gmail.com");
			helper.setText("MÃ CODE XÁC NHẬN CỦA TÀI KHOẢN " + email + ": " + code);
			mailer.send(mail);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String RandGeneratedStr(int l){
		String AlphaNumericString = "0123456789";
		StringBuilder s = new StringBuilder(l);
		int i;
		for (i = 0; i < l; i++) {
			int ch = (int) (AlphaNumericString.length() * Math.random());
			s.append(AlphaNumericString.charAt(ch));
		}
		return s.toString();
	}

}
