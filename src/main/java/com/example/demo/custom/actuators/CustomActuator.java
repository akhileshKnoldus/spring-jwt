package com.example.demo.custom.actuators;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom-actuator")
public class CustomActuator {

	@ReadOperation
	public String getCurrentDBDetails() {
		return "Give the current db status";
	}
}
