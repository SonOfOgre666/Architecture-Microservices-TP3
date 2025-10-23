# TP2 - Spring IoC et DI

L'objectif de ce laboratoire est de créer une application Java avec Spring, mettant en œuvre l'inversion de contrôle (IoC) et l'injection de dépendances (DI) via des annotations. Nous allons développer une interface DAO, une interface métier, et gérer l'injection des dépendances entre elles.

## Structure

- `src/main/java/dao` : `IDao`, `DaoImpl`, `DaoImpl2`
- `src/main/java/metier` : `IMetier`, `MetierImpl`
- `src/main/java/presentation` : `Presentation2`, `PresentationXML`

## Fonctionnalités

- Utilisation des annotations Spring pour déclarer les beans et injecter les dépendances.
- Gestion des profils Spring (`dev` et `prod`) pour activer différentes implémentations.
- Configuration alternative avec un fichier XML (`applicationContext.xml`).

## Exécution

### Avec les annotations
1. Exécutez la classe `Presentation2`.
2. Résultat attendu :
   - Profil `dev` actif : `Résultat = 300.0`
   - Profil `prod` actif : `Résultat = 200.0`

### Avec la configuration XML
1. Exécutez la classe `PresentationXML`.
2. Résultat attendu : `Résultat (XML) = 300.0`.

## Tests
- Les tests unitaires sont disponibles dans `src/test/java/metier/MetierImplTest.java`.
- Pour exécuter les tests :
  ```bash
  mvn test
  ```

## Prérequis
- Java 8 ou supérieur
- Maven 3.6+
- Spring Framework 6.1.2