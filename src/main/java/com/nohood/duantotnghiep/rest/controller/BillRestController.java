package com.nohood.duantotnghiep.rest.controller;

import com.nohood.duantotnghiep.entity.BILL;
import com.nohood.duantotnghiep.entity.chart.TIME;
import com.nohood.duantotnghiep.service.BILLSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bill")
public class BillRestController {
	@Autowired
	BILLSERVICE service;
	@Autowired
	JavaMailSender mailer;

	@GetMapping
	public List<BILL> getAll() {
		return service.findall();
	}

	@PutMapping("{id}")
	public BILL update(@PathVariable("id") Integer id, @RequestBody BILL bill) {
		if (bill.getSTATUS() == 2) {
			try { 
				MimeMessage mail = mailer.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
				helper.setFrom("sprologfile@gmail.com", "sprologfile@gmail.com");
				helper.setTo(bill.getAccount().getEMAIL());
				helper.setReplyTo("SHOPNOHOOD", "sprologfile@gmail.com");
				helper.setSubject("Thông tin về đơn hàng " + bill.getBILLID());
				helper.setCc("sprologfile@gmail.com");
				Date date = new Date();
				helper.setText(
						"Đơn hàng " + bill.getBILLID() + " của bạn đang được giao bắt đầu từ " + date);
				mailer.send(mail);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		if (bill.getSTATUS() == 4) {
			try { 
				MimeMessage mail = mailer.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
				helper.setFrom("sprologfile@gmail.com", "sprologfile@gmail.com");
				helper.setTo(bill.getAccount().getEMAIL());
				helper.setReplyTo("SHOPNOHOOD", "sprologfile@gmail.com");
				helper.setSubject("Thông tin về đơn hàng " + bill.getBILLID());
				helper.setCc("sprologfile@gmail.com");
				Date date = new Date();
				helper.setText(
						"Đơn hàng " + bill.getBILLID() + " của bạn đã bị hủy vào ngày " + date);
				mailer.send(mail);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return service.create(bill);
	}

	@PutMapping("cancel/{id}")
	public int cancel(@PathVariable("id") Integer id, @RequestBody BILL bill) {
		return service.UpdateSl(bill.getSTATUS(), id);
	}

	@PutMapping("pre/{id}")
	public int per(@PathVariable("id") long id, @RequestBody BILL bill) {
		Date dt = new Date();
		return service.Updatepre(bill.getSTATUS(), dt, id);
	}

	@GetMapping("nam")
	public List<TIME> getYear() {
		return service.getYear();
	}

	@GetMapping("timthang/{year}")
	public List<TIME> getMonth(@PathVariable("year") int year) {
		return service.getMonth(year);
	}
	@GetMapping("status/{id}")
	public List<BILL> getStatus(@PathVariable("id") int id) {
		return service.findStatus(id);
	}
}
