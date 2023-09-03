CREATE DATABASE IF NOT EXISTS `cours`;
GRANT ALL ON `cours`.* TO 'khadijakhouili'@'%';
CREATE DATABASE IF NOT EXISTS `gs_depenses`;
GRANT ALL ON `gs_depenses`.* TO 'khadijakhouili'@'%';
CREATE DATABASE IF NOT EXISTS `gs_employees`;
GRANT ALL ON `gs_employees`.* TO 'khadijakhouili'@'%';
CREATE DATABASE IF NOT EXISTS `condidats`;
GRANT ALL ON `condidats`.* TO 'khadijakhouili'@'%';
CREATE DATABASE IF NOT EXISTS `vehicule`;
GRANT ALL ON `vehicule`.* TO 'khadijakhouili'@'%';

USE vehicule;
-- sevice najwa ----

CREATE TABLE IF NOT EXISTS vehicules (
    id INT PRIMARY KEY AUTO_INCREMENT,
    autoecole_id INT NOT NULL,
    img_carte_grise VARCHAR(255),
    img_assurance VARCHAR(255),
    img_visite_tech VARCHAR(255),
    img_vignette VARCHAR(255),
    modele VARCHAR(255),
    matricule INT UNIQUE,
    fornisseur VARCHAR(255),
    marque VARCHAR(255),
    categorie_permis VARCHAR(255),
    date_p_visete_technique DATE,
    date_vidange DATE,
    date_p_vidange DATE,
    date_assurance DATE,
    date_e_assurance DATE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);



