package com.gsautoecole.serviceemployee.AuthenticationServiceFake;

import org.springframework.stereotype.Service;

@Service
public class FakeAuthenticationService {
	
	 // Méthode de simulation d'authentification
    public User authenticate(String token) {
        // Ici, vous pouvez ajouter la logique pour valider le token et générer un objet User avec les informations nécessaires
        // Pour la simulation, nous allons simplement créer un objet User avec un ID d'auto-école prédéfini
        Long autoEcoleId = 2L;
        User user = new User();
        user.setAutoEcoleId(autoEcoleId);
        // Ajoutez d'autres informations si nécessaire

        return user;
    }

}
