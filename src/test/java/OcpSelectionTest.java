import dao.DaoApi;
import dao.DaoFile;
import dao.DaoImpl;
import dao.DaoImpl2;
import metier.IMetier;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import presentation.Presentation2;
import config.PropertyDrivenConfig;

import static org.junit.Assert.assertEquals;

public class OcpSelectionTest {

    @Test
    public void devProfile_choisitDao2_300() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");     // DaoImpl2 (150)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(300.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void prodProfile_choisitDao_200() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("prod");    // DaoImpl (100)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(200.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void propertyDriven_selectsDaoApi_440() {
        // Make sure system property overrides app.properties
        System.setProperty("dao.target", "daoApi");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // Do NOT set profiles so component-scanned DAO beans with @Profile won't be active.
        // Register a test configuration that defines all dao beans (no profiles) so PropertyDrivenConfig can pick one.
        ctx.register(TestDaoBeans.class, PropertyDrivenConfig.class);
        ctx.register(Presentation2.class);
        ctx.refresh();

        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(440.0, m.calcul(), 1e-6);
        ctx.close();

        // cleanup
        System.clearProperty("dao.target");
    }

    // Test-only configuration that provides dao beans without profiles so PropertyDrivenConfig can choose among them
    public static class TestDaoBeans {
        public DaoImpl dao() { return new DaoImpl(); }
        public DaoImpl2 dao2() { return new DaoImpl2(); }
        public DaoFile daoFile() { return new DaoFile(); }
        public DaoApi daoApi() { return new DaoApi(); }

        @org.springframework.context.annotation.Bean(name = "dao")
        public DaoImpl beanDao() { return dao(); }

        @org.springframework.context.annotation.Bean(name = "dao2")
        public DaoImpl2 beanDao2() { return dao2(); }

        @org.springframework.context.annotation.Bean(name = "daoFile")
        public DaoFile beanDaoFile() { return daoFile(); }

        @org.springframework.context.annotation.Bean(name = "daoApi")
        public DaoApi beanDaoApi() { return daoApi(); }
    }
}
