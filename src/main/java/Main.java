import dao.DancesDao;
import dao.RepositoryUtil;
import entities.DanceEntity;
import entities.ManEntity;
import entities.WomanEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(final String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        //creating objects
        DancesDao dd = new DancesDao();
        ManEntity man = new ManEntity();
        WomanEntity woman = new WomanEntity();
        DanceEntity dance = new DanceEntity();

////        man.setName("George");
////        dd.addMan(man);
//       woman.setName("Christine");
//       dd.addWoman(woman);
//        dance.setManId(6);
//        dance.setWomanId(6);
//        dance.setStyle("Vals");
//        dd.addDance(dance);

//        ManEntity manToEdit = dd.getMan(8);
//        manToEdit.setName("Ion");
//        dd.editMan(manToEdit);

        //edit woman
//        WomanEntity womanToEdit = dd.getWoman(7);
//        womanToEdit.setName("Maria");
//        dd.editWoman(womanToEdit);

        //delete man
//        dd.deleteMan(9);
//        dd.deleteMan(11);
//        dd.deleteMan(12);

        //delete woman
        //dd.deleteWoman(8);
       List resultMan = dd.getListOfMen();

        System.out.println("List of men:");
        for (ManEntity m : (List<ManEntity>) resultMan)
            System.out.println(m.getId() + " " + m.getName());

        List resultWoman = dd.getListOfWomen();
        System.out.println("List of women:");
        for (WomanEntity w: (List<WomanEntity>) resultWoman)
            System.out.println(w.getId() + " " + w.getName());

        List resultDance = dd.getListOfDances();
        System.out.println("List of dances:");
//        String menNames = "SELECT name FROM ManEntity \n" +
//                "WHERE id = (SELECT manId FROM DanceEntity" +
//                "WHERE"
//        Query query =
        for (DanceEntity d : (List<DanceEntity>) resultDance)
            System.out.println(d.getManId() + " " + d.getWomanId() + " " + d.getStyle());

        System.out.print("Enter woman's id: ");
        int wId = input.nextInt();
        System.out.println("Her dancing partners were: ");
        System.out.println(dd.getAllHerPartners(wId));

        System.out.print("Enter man's id: ");
        int mId = input.nextInt();
        System.out.println("He danced: ");
        System.out.println(dd.getAllHisDances(mId));
    }
}