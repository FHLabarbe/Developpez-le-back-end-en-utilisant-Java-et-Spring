# Développer le backend Java avec Spring - François-Hugues Labarbe


## Documentation détaillée

### 1. Procédure pour installer et lancer le projet

#### Prérequis :

	•	Java 17 ou supérieur
	•	Maven
	•	MySQL ou un autre serveur de base de données compatible
	•	Git

#### Étapes :

	1.	Clonez le repository :

    git clone https://github.com/ton-repository/backend-project.git
    cd backend-project

    2.	Installez les dépendances Maven :

    mvn clean install

    3.	Configurez le fichier application.properties dans le dossier src/main/resources. Voici un exemple de configuration pour MySQL :

    spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_la_base_de_donnees
    spring.datasource.username=ton_nom_d_utilisateur
    spring.datasource.password=ton_mot_de_passe
    spring.jpa.hibernate.ddl-auto=update

    4.	Lancez l’application :

    mvn spring-boot:run

### 2. URL du Swagger

Une fois l’application lancée, accédez à la documentation Swagger via :
http://localhost:3001/swagger-ui/index.html
 