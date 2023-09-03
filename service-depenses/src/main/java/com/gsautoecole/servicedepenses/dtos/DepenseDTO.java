package com.gsautoecole.servicedepenses.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class DepenseDTO {
	
    private List<Map<String, String>> depenses=new ArrayList<>();
    private int total;
    private String auto_name;
    private String tel;
    private String fax;
    private String email;
    private String logo_auto;
    private String adresse;
}
