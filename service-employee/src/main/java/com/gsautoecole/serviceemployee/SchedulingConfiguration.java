package com.gsautoecole.serviceemployee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.gsautoecole.serviceemployee.Entities.Moniteur;
import com.gsautoecole.serviceemployee.services.MoniteurService;
import com.gsautoecole.serviceemployee.utilsClasses.DateUtils;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled",matchIfMissing = true)
public class SchedulingConfiguration {
	
	private MoniteurService moniteurServ;

	@Autowired
	public SchedulingConfiguration(MoniteurService moniteur) {
		this.moniteurServ = moniteur;
	}
	@Scheduled(cron = "0 0 00 * * *")
	void DosomeThing() {
		 LocalDate date = LocalDate.now(); // Obtenez la date actuelle
	     String formattedDate = DateUtils.formatDate(date);
		if("01-08-2023".equals(formattedDate)) {
			System.out.println("experee !!");
		}else {
			System.out.println("date is : "+date);
			System.out.println("formattedDate is : "+formattedDate);
		}
		
		System.out.println("Now is : "+formattedDate);
		System.out.println("date is : "+date);
		System.out.println(CarteMoniteurExpiree());
	}
	
	@Scheduled(cron = "0 * 16 * * *")
	List<Moniteur> CarteMoniteurExpiree() {

		List<Moniteur> moniteurs = moniteurServ.getAllMoniteurs();
		List<Moniteur> moniteursExpires = new ArrayList<>();
		//Tester s'il existent des moniteurs ayant une carte expires, si oui il stockera 
		//
		moniteurs.forEach(moniteur->{
			if((LocalDate.now().toString()).equals(moniteur.getExpirCarteMonit().toString())) {
				moniteursExpires.add(moniteur);
			}
		});
		moniteursExpires.forEach(moniteur->{
			//Gestion des notifications pour les moniteurs ayant des cartes expirés
			System.out.println("Carte moniteur de : "+moniteur+" est expiree !!");
		});
		System.out.println("Carte moniteur de : "+moniteursExpires+" est expiree !!");
        return moniteursExpires;
	}
}
