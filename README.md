
# Authentification 2FA + JWT avec Spring Boot

Ce projet Spring Boot met en place une authentification sécurisée en deux étapes (2FA) combinée à la génération d’un token JWT. Il s'agit d'une première étape avant la sécurisation d'autres endpoints via Spring Security.

## Fonctionnalités

- ✅ Authentification avec identifiants `username + password`
- ✅ Génération d’un code de validation 2FA aléatoire
- ✅ Vérification du code 2FA (simulé dans la console pour l’instant)
- ✅ Génération d’un token JWT après validation du 2FA
- 🔒 Sécurisation de endpoints avec JWT (à venir)

## Endpoints exposés

- `POST /auth/login`  
  Permet à un utilisateur de s’authentifier avec ses identifiants.  
  En cas de succès, un code 2FA est généré et affiché dans la console.

- `POST /auth/verify-2fa`  
  Permet de valider le code 2FA. Si le code est correct, un token JWT est retourné dans la réponse.

## Comment tester avec Postman

### Étape 1 : Authentification

- URL : `http://localhost:8080/auth/login`
- Méthode : POST
- Body (JSON) :
  ```json
  {
    "username": "user",
    "password": "pass"
  }
  ```
- Résultat attendu : message `Login OK, code 2FA envoyé`
- Le code 2FA s’affiche dans la console (ex: `Code 2FA pour user : 123456`)

### Étape 2 : Validation du code 2FA

- URL : `http://localhost:8080/auth/verify-2fa`
- Méthode : POST
- Body (JSON) :
  ```json
  {
    "username": "user",
    "code2fa": "123456"
  }
  ```
- Résultat attendu : message `2FA OK, authentification complète. Token : ...`

## Exemple de réponse (JWT)

```text
2FA OK, authentification complète. Token : eyJhbGciOiJIUzI1NiIsInR...
```

## Points d’amélioration possibles

- 🔐 Intégrer Spring Security pour sécuriser les autres endpoints avec JWT
- 📩 Envoyer le code 2FA par email ou SMS au lieu de l’afficher dans la console
- 🧾 Stocker les utilisateurs et les codes dans une base de données
- 🔁 Ajouter une expiration au code 2FA
- 🔍 Ajouter Swagger pour tester les endpoints

## Prérequis

- Java 17
- Maven
- IDE comme IntelliJ ou VSCode
- Postman (ou Curl) pour tester les endpoints

## Démarrer le projet

```bash
git clone <repo>
cd projet
mvn clean install
mvn spring-boot:run
```

---

## Auteur

Cédric Macé  
Développeur Java Junior  
Projet personnel d'apprentissage de Spring Boot et JWT
