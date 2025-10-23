package presentation;

import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  // Indique que cette classe contient des configurations Spring
@ComponentScan(basePackages = {"dao", "metier"})  // Indique à Spring de scanner ces packages pour trouver des beans
public class Presentation2 {
    public static void main(String[] args) {
        // Création du contexte Spring basé sur les annotations
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles("dev");  // Active le profil "dev"
            context.register(Presentation2.class);
            context.refresh();

            // Récupération du bean IMetier depuis le conteneur Spring
            IMetier metier = context.getBean(IMetier.class);

            // Exécution de la méthode calcul() et affichage du résultat
            System.out.println("Résultat = " + metier.calcul());
        }
    }
}