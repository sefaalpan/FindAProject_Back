package be.ros.FindAProject.init;

import be.ros.FindAProject.models.entities.*;
import be.ros.FindAProject.repositories.CategorieRepository;
import be.ros.FindAProject.repositories.ProjetRepository;
import be.ros.FindAProject.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Slf4j
public class InitDatabase implements InitializingBean {

    private final ProjetRepository projetRepository;
    private final CategorieRepository categorieRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public InitDatabase(ProjetRepository projetRepository, CategorieRepository categorieRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.projetRepository = projetRepository;
        this.categorieRepository = categorieRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public static void main(String[] args) {
        System.out.println("[argssssssssssss]");
        for (String arg : args) {
            System.out.print("arg : ");
            System.out.println(arg);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(">>>>>>>>>>> INITIALISATION DE LA DB <<<<<<<<<<<");

        InitDatabase.main(new String[]{"argument 1", "argument 2", "argument 3", "argument 4"});

        Adresse adresse = Adresse.builder()
                .rue("rue des freelancers")
                .numero("4")
                .ville("Charleroi")
                .codePostal("6000")
                .pays("Belgique")
                .build();

        Categorie categorie = Categorie.builder()
                .label("Développement Java")
                .build();

        Categorie cat = Categorie.builder()
                .label("Développement C#")
                .build();

        Categorie cat2 = Categorie.builder()
                .label("Développement Python")
                .build();

        String st = new String("kikou");
        String st2 = st.concat("bipbip");
        System.out.println(st);
        System.out.println(st2);

        User client = new User("mich", "mich", LocalDate.of(1986, 10, 2),
                "mich@email", "mich", encoder.encode("Mich1980!"), List.of("ROLE_CLIENT"), adresse, true, true, true, true);

        User client2 = new User("mich", "mich", LocalDate.of(1986, 10, 2),
                "mich2@email", "mich2", encoder.encode("Mich1980!"), List.of("ROLE_CLIENT"), adresse, true, true, true, true);

        Projet projet = Projet.builder()
                .categorie(categorie)
                .date(LocalDate.of(2021, 12, 31))
                .prix(200.0)
                .titre("Site de freelance en Springboot et Angular")
                .resume("Bonjour,\n" +
                        "\n" +
                        "Nous recherchons un développeur Springboot / Angular afin de déployer les smartcontract d’un fork OHM et debugger les potentielles erreurs. Le projet est sur AVAX.\n" +
                        "\n" +
                        "Nous avons une deadline de 4j pour cette mission et proposons une rémunération de 10 000 USDT payés lors de la vente de pre-sales.\n" +
                        "\n" +
                        "Le projet est hype et nous sommes convaincu que nous allons remplir les allocations en moins d’une journée. Nous avons l’habitude de travailler sur ce genre de projets et seront potentiellement intéressés de travailler avec vous par la suite si le travail demandé est respecté" +
                        "\n" +
                        "Si la mission peut potentiellement vous intéresser, nous vous donnerons plus de détails par la suite.\n" +
                        "\n" +
                        "Amicalement,\n" +
                        "\n" +
                        "Léo")
                .user(client)
                .reserve(true)
                .build();

        Projet ppp = Projet.builder()
                .categorie(categorie)
                .date(LocalDate.of(2021, 1, 4))
                .prix(2000.0)
                .titre("Création logiciel")
                .resume("En tant qu'entreprise intervenant dans le secteur de l'aménagement/décoration d'intérieur, nous recherchons un développeur de logiciel/interface web afin de créer une solution comprenant différentes fonctionnalités :\n" +
                        "\n" +
                        "- plan 3D\n" +
                        "- conception modélisation 3D,\n" +
                        "- ajout d'éléments 3D (meubles).\n" +
                        "\n" +
                        "A titre d'exemple, le logiciel devra permettre à l'utilisateur de pouvoir renseigner les mesures d'une pièce de leur logement et de l'aménager grâce aux différents équipements disponibles sur le logiciel, de pouvoir naviguer entre différentes vues. Nous souhaitons également qu'il soit ergonomique (UX) et intuitif afin de faciliter son utilisation au maximum.")
                .user(client2)
                .reserve(true)
                .build();

        Freelance freelance = new Freelance("Franck", "Kamenan", LocalDate.of(1986, 10, 2),
                "franck@email", "franck", encoder.encode("Franck1980!"), List.of("ROLE_FREELANCE"), adresse, true, true, true, true,
                List.of(projet));


        User f = new Freelance("Franky", "Kamenany", LocalDate.of(1986, 10, 2),
                "franky@email", "franky", encoder.encode("Franky1980!"), List.of("ROLE_FREELANCE"), adresse, true, true, true, true);


        User c = new User("client", "client", LocalDate.of(1986, 10, 2),
                "client@email", "client", encoder.encode("Client1980!"), List.of("ROLE_CLIENT"), adresse, true, true, true, true);

        Projet p = Projet.builder()
                .categorie(categorie)
                .date(LocalDate.of(2021, 12, 20))
                .prix(500.0)
                .titre("Web app en Springboot/JAVAEE et framework front au choix")
                .resume("Hello, nous recherchons un Freelance si possible habitant en France (+ simple pour communiquer) pour développer notre concept d'une appli de rencontres (qui sera payante) d'un nouveau genre sur iOS et Google Play + la création d'un site web (sous wordpress) avec une page vitrine pour l'app mais avec plusieurs onglets comme par exemple un onglet blog et un onglet bon plan qui permettra d'être redirigé vers le site marchand en cliquant sur le bon plan concerné.\n" +
                        "Nous souhaitons quelqu'un prêt a collaborer avec nous sur du long terme, en s'occupant notamment des bugs que pourra rencontrer notre appli quand elle sera lancée. Nous sommes à l'étape de RECHERCHE et la CONCEPTION aura lieu quand les fonds seront disponibles. (nous espérons Printemps 2022)\n" +
                        "\n" +
                        "Pour finir, nous aurons également des questions à vous poser afin de savoir si nos souhaits sont réalisables (en lien avec l'app et le site internet). Merci beaucoup !")
                .user(c)
                .reserve(false)
                .build();

        Adresse address = Adresse.builder()
                .rue("rue des admins")
                .numero("10")
                .codePostal("6000")
                .ville("Charleroi")
                .pays("Belgique")
                .build();

        User admin = new User("admin", "admin", LocalDate.of(1996, 10, 4),
                "admin@email.com", "admin", encoder.encode("Admin1996!"), List.of("ROLE_ADMIN"), address, true, true, true, true);


        /** SEQUENCE DE CASTING **/
        User u = new Freelance("Test", "Test", LocalDate.of(1986, 10, 2),
                "test@email", "test", encoder.encode("Test1980!"), List.of("ROLE_FREELANCE"), adresse, true, true, true, true);
        Freelance fff = (Freelance) u;
        fff.setProjets(List.of(ppp));
        /** SEQUENCE DE CASTING **/

        userRepository.save(admin);
        categorieRepository.save(categorie);
        categorieRepository.save(cat);
        categorieRepository.save(cat2);
        userRepository.save(client);
        userRepository.save(client2);
        projetRepository.save(projet);
        userRepository.save(c);
        projetRepository.save(p);
        userRepository.save(freelance);
        userRepository.save(f);
        projetRepository.save(ppp);
        userRepository.save(fff);



//                userRepository.save(client);
//                userRepository.save(freelance);


    }
}
