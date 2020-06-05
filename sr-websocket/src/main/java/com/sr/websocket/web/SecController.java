package com.sr.websocket.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecController {

	@RequestMapping(value = "/view")
	public Map<String, Object> view() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", new Date());
		map.put("b", "String");
		return map;
	}

	@RequestMapping(value = "/secView")
	public Map<String, Object> secView() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("c", new Date());
		map.put("d", "ddddd");
		return map;
	}
}
