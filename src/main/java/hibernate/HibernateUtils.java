package hibernate;

import ecommerce.LaptopEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import robot.BatteryEntity;
import robot.InstructionEntity;
import robot.RobotEntity;

public class HibernateUtils {

    static SessionFactory hibernateSessionFactory;
    public static SessionFactory buildSessionFactory() {

        /* Telling hibernate where to connect to + what entities are supported */
        Configuration conf = new Configuration();

        conf.addAnnotatedClass(RobotEntity.class);
        conf.addAnnotatedClass(LaptopEntity.class);
        conf.addAnnotatedClass(BatteryEntity.class);
        conf.addAnnotatedClass(InstructionEntity.class);

        conf.configure("hibernate.cfg.xml");
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(
                        conf.getProperties()
                ).build();

        hibernateSessionFactory = conf.buildSessionFactory(registry);
        return hibernateSessionFactory;
    }

}
