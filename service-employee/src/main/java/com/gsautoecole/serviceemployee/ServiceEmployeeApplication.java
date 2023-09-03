package com.gsautoecole.serviceemployee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ServiceEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceEmployeeApplication.class, args);
		System.out.println("had l7ebs bien demarrer !");
	}
	
	// @Bean
	// CommandLineRunner start(ItypeEmploieService typeEmp,ICategorieService categorie) {
	// 	return args->{
	// 		typeEmp.addTypeEmp(new TypeEmploie(null,"Moniteur"));
	// 		typeEmp.addTypeEmp(new TypeEmploie(null,"Directeur"));
	// 		typeEmp.addTypeEmp(new TypeEmploie(null,"Secrétaire"));
	// 		categorie.addCategorie(new CategorieMonit(null, 30, 40, TypeCategorie.A,null));
	// 		categorie.addCategorie(new CategorieMonit(null, 36, 40, TypeCategorie.B,null));
	// 		categorie.addCategorie(new CategorieMonit(null, 32, 40, TypeCategorie.C,null));
	// 	};
	// }
	
//	@Scheduled(cron = "* 04 11 * * *")
//	void DosomeThing() {
//		 LocalDate date = LocalDate.now(); // Obtenez la date actuelle
//	     String formattedDate = DateUtils.formatDate(date);
//		if("01-08-2023".equals(formattedDate)) {
//			System.out.println("experee !!");
//		}else {
//			System.out.println("date is : "+date);
//			System.out.println("formattedDate is : "+formattedDate);
//		}
//		
//		System.out.println("Now is : "+formattedDate);
//		System.out.println("date is : "+date);
//	}
}
//@Configuration
//@EnableScheduling
//@ConditionalOnProperty(name = "scheduling.enabled",matchIfMissing = true)
//class SchedulingConfiguration{
//	
//}
