package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtility;
import java.util.List;

public class RepositoryUtil {
    public static List getList(String query)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction tx = null;
        List items = null;
        try {
            tx = session.beginTransaction();
            items = session.createQuery(query).list();
            tx.commit();
        }
        catch (HibernateException hibernateException) {
            if(tx != null)
                tx.rollback();

            hibernateException.printStackTrace();
        }
        finally {
            session.close();
        }
        return items;
    }

    public static int addEntity(Object entity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction tx = null;
        int entityId = 0;
        try {
            tx = session.beginTransaction();
            entityId = (Integer) session.save(entity);
            tx.commit();
        } catch (HibernateException hibernateException) {
            if (tx != null)
                tx.rollback();

            hibernateException.printStackTrace();
        } finally {
            session.close();

        }
       return entityId;
    }

    public static boolean deleteEntity(Class c, int entityId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction tx = null;
        boolean deleted = false;
        try {
            tx = session.beginTransaction();
            Object object = session.get(c, entityId);
            session.delete(object);
            tx.commit();
            deleted = true;
        }
        catch (HibernateException hibernateException){
            if(tx != null)
                tx.rollback();

            hibernateException.printStackTrace();
        }
        finally {
            session.close();
        }

        return deleted;
    }

    public static void editEntity(Object entity)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        }
        catch (HibernateException hibernateException){
            if (tx != null)
                tx.rollback();

            hibernateException.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static Object getEntityById(Class c, int entityId)
    {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction tx = null;
        Object entity = null;
        try
        {
            tx = session.beginTransaction();
            entity = session.get(c, entityId);
            tx.commit();
        }
        catch (HibernateException hibernateException)
        {
            if(tx != null)
                tx.rollback();

            hibernateException.printStackTrace();
        }
        finally {
            session.close();
        }

        return entity;
    }
}
