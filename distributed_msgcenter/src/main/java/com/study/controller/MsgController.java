package com.study.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {

	public static AtomicInteger count = new AtomicInteger(0);

	@RequestMapping("/msg")
	public ResponseEntity<String> sendMsg() {
		System.out.println("wang send a sms" + count.incrementAndGet());
		return new ResponseEntity<String>("okay", HttpStatus.OK);
	}
}
