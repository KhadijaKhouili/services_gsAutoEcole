package com.gsautoecole.servicedepenses.AuthenticationServiceFake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class User {
	
	private String password ;
	private Long autoEcoleId;
}
