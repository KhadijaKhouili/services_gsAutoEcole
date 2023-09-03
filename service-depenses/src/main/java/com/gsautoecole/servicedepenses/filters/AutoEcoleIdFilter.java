package com.gsautoecole.servicedepenses.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.gsautoecole.servicedepenses.AuthenticationServiceFake.FakeAuthenticationService;
import com.gsautoecole.servicedepenses.AuthenticationServiceFake.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
// import jakarta.servlet.http.HttpServletRequest;
@Component
public class AutoEcoleIdFilter implements Filter {

    @Autowired
    private FakeAuthenticationService fakeAuthenticationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("khdam lfilter");
        //HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpHeaders header =  new HttpHeaders();
        header.set("Authorization", "Bearer my_token");
        String authorizationHeader = header.getFirst("Authorization");
        //String authorizationHeader = httpRequest.getHeader("Authorization");
        //System.out.println("Hewder "+authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Pour supprimer le préfixe "Bearer "
            System.out.println("Autho");
            // Utiliser le FakeAuthenticationService pour obtenir les informations de l'utilisateur
            User user = fakeAuthenticationService.authenticate(token);
            System.out.println("User");
            if (user != null) {
                // Récupérer l'ID de l'auto-école depuis les informations de l'utilisateur
                Long autoEcoleId = user.getAutoEcoleId();

                // Ajouter l'ID de l'auto-école comme attribut de requête pour utilisation ultérieure dans les contrôleurs
                request.setAttribute("auto_Id", autoEcoleId);
                System.out.println(request.getAttribute("auto_Id")); 
            }
        }

        // Continuer la chaîne de filtres
        chain.doFilter(request, response);
    }
}
//public class AutoEcoleIdFilter implements Filter {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Value("${authentication.service.url}")
//    private String authenticationServiceUrl; // URL du service d'authentification
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String authorizationHeader = httpRequest.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7); // Pour supprimer le préfixe "Bearer "
//
//            // Appeler le service d'authentification pour obtenir les informations de l'utilisateur
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + token);
//            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//            ResponseEntity<User> responseEntity = restTemplate.exchange(authenticationServiceUrl,
//                    HttpMethod.GET, requestEntity, User.class);
//
//            if (responseEntity.getStatusCode() == HttpStatus.OK) {
//                User user = responseEntity.getBody();
//                // Récupérer l'ID de l'auto-école depuis les informations de l'utilisateur
//                Long autoEcoleId = user.getAutoEcoleId();
//
//                // Ajouter l'ID de l'auto-école comme attribut de requête pour utilisation ultérieure dans les contrôleurs
//                request.setAttribute("autoEcoleId", autoEcoleId);
//            }
//        }
//
//        // Continuer la chaîne de filtres
//        chain.doFilter(request, response);
//    }
//}
