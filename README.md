# TP3 - Spring IoC & OCP

L'objectif de ce laboratoire est de mettre en place plusieurs stratégies d’injection et de sélection d’implémentations IDao sans changer la classe métier MetierImpl, afin de respecter le principe OCP (Open-Closed Principle). Les mécanismes abordés incluent les profils Spring, l'alias via @Bean, et la sélection par propriété externe.

## Structure du Projet

- `src/main/java/dao/` : Interfaces et implémentations DAO
  - `IDao.java` : Interface DAO
  - `DaoImpl.java` : Implémentation prod (100.0)
  - `DaoImpl2.java` : Implémentation dev (150.0)
  - `DaoFile.java` : Implémentation file (180.0)
  - `DaoApi.java` : Implémentation api (220.0)
- `src/main/java/metier/` : Logique métier
  - `IMetier.java` : Interface métier
  - `MetierImpl.java` : Implémentation métier (inchangée)
- `src/main/java/presentation/` : Point d'entrée
  - `Presentation2.java` : Configuration et exécution principale
- `src/main/java/config/` : Classes de configuration
  - `PropertyDrivenConfig.java` : Sélection par propriété
  - `DaoAliasConfig.java` : Alias pour api
- `src/main/resources/` : Ressources
  - `app.properties` : Configuration des propriétés
- `src/test/java/` : Tests
  - `OcpSelectionTest.java` : Tests de sélection OCP
  - `MetierImplTest.java` : Tests unitaires

## Fonctionnalités

- **Profils Spring** : Activation de profils (`prod`, `dev`, `file`, `api`) pour sélectionner l'implémentation DAO.
- **Sélection par Propriété** : Utilisation de `app.properties` pour choisir l'implémentation via `dao.target`.
- **Alias via @Bean** : Configuration d'alias pour l'implémentation api.
- **OCP Respecté** : `MetierImpl` reste inchangée ; basculement par configuration uniquement.

## Exécution

### 1. Sélection par Profils
Modifiez `Presentation2.java` pour décommenter le profil souhaité, puis exécutez :

```bash
mvn compile exec:java -Dexec.mainClass="presentation.Presentation2"
```

- **Profil `prod`** : `Résultat = 200.0` (DaoImpl)
- **Profil `dev`** : `Résultat = 300.0` (DaoImpl2)
- **Profil `file`** : `Résultat = 360.0` (DaoFile)
- **Profil `api`** : `Résultat = 440.0` (DaoApi via alias)

### 2. Sélection par Propriété
Laissez les profils commentés dans `Presentation2.java`. Modifiez `dao.target` dans `app.properties`, puis exécutez la commande ci-dessus.

- `dao.target=dao` : `Résultat = 200.0`
- `dao.target=dao2` : `Résultat = 300.0`
- `dao.target=daoFile` : `Résultat = 360.0`
- `dao.target=daoApi` : `Résultat = 440.0`

## Tests

- Tests unitaires et d'intégration dans `src/test/java/`.
- Pour exécuter tous les tests :
  ```bash
  mvn test
  ```
- Résultat attendu : Tous les tests passent (5/5), validant les sélections OCP.

## Prérequis

- Java 17+
- Maven 3.6+
- Spring Framework 5.3.x

## Dépendances

- `spring-context` : Pour IoC et DI.
- `junit` : Pour les tests.

## Notes

- `MetierImpl` n'est jamais modifiée pour changer d'implémentation.
- Basculement d'implémentation par configuration uniquement (profils ou propriétés).
- Valeurs retournées : 200.0 / 300.0 / 360.0 / 440.0 selon la source.