package dao;

import entities.ManEntity;
import entities.WomanEntity;
import org.hibernate.*;
import utils.HibernateUtility;
import java.util.List;


public class DancesDao {
    SessionFactory sessionFactory;

    public DancesDao() {
        sessionFactory = HibernateUtility.getSessionFactory();
    }

    public ManEntity getMan(int id) {
        return (ManEntity) RepositoryUtil.getEntityById(ManEntity.class, id);
    }

    public WomanEntity getWoman(int id){
        return (WomanEntity) RepositoryUtil.getEntityById(WomanEntity.class, id);
    }

    public List getListOfMen() {
        return RepositoryUtil.getList("FROM ManEntity");
    }

    public List getListOfWomen() {
        return RepositoryUtil.getList("FROM WomanEntity");
    }

    public List getListOfDances()
    {
        return RepositoryUtil.getList("FROM DanceEntity");
    }

    public int addMan(Object man)
    {
        return RepositoryUtil.addEntity(man);
    }

    public int addWoman(Object woman)
    {
        return RepositoryUtil.addEntity(woman);
    }

    public int addDance(Object dance)
    {
        return RepositoryUtil.addEntity(dance);
    }

    public void editMan(Object man)
    {
        RepositoryUtil.editEntity(man);
    }

    public void editWoman(Object woman){
        RepositoryUtil.editEntity(woman);
    }

    public boolean deleteMan(int manId) {
        return RepositoryUtil.deleteEntity(ManEntity.class, manId);
    }

    public boolean deleteWoman(int womanId){
        return RepositoryUtil.deleteEntity(WomanEntity.class, womanId);
    }


    public List<String> getAllHerPartners(int womanId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<String> allHerPartners = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT m.name FROM ManEntity as m \n" +
            "WHERE m.id IN(SELECT manId FROM DanceEntity as d \n" +
                    "WHERE d.womanId = ( SELECT w.id FROM WomanEntity as w \n" +
                    "WHERE w.id = :wId))";
            Query query = session.createQuery(hql);
            query.setParameter("wId", womanId);
            allHerPartners = query.list();  // executa sql si returneaza rezultatele ca lista java
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

        return allHerPartners;
    }

    public List<String> getAllHisDances(int manId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<String> allHisDances = null;

            try {
                tx = session.beginTransaction();
                String hql = "SELECT d.style FROM DanceEntity as d\n" +
                        "WHERE d.manId IN (SELECT m.id FROM ManEntity as m \n" +
                        "WHERE m.id = :mId)";
                Query query = session.createQuery(hql);
                query.setParameter("mId", manId);
                allHisDances = query.list();
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

        return allHisDances;

    }
}
