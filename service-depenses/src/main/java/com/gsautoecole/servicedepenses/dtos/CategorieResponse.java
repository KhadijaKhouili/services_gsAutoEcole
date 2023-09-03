package com.gsautoecole.servicedepenses.dtos;


import com.gsautoecole.servicedepenses.enums.TypeDepense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor @AllArgsConstructor
public class CategorieResponse {

	private Long id;
	private TypeDepense typeDep;
	private String categName;
	
	// private List<Depense> depenses = new ArrayList<>();
}
