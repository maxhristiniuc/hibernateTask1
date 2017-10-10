package utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



    public class HibernateUtility {
        private static SessionFactory sessionFactory;

        static
        {
            try
            {
                Configuration configuration = new Configuration().configure();
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }
            catch (HibernateException he)
            {
                System.err.println("Error creating Session: " + he);
                throw new ExceptionInInitializerError(he);
            }
        }

        public static SessionFactory getSessionFactory()
        {
            return sessionFactory;
        }
    }

