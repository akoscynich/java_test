package appmanager;

import data.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public Contacts contactById(int contactId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where id ='" + contactId + "'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public ContactsInGroups contactsInGroup(int contactId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactsInGroupsData> result = session.createQuery("from ContactsInGroupsData where not id = '" + contactId + "'").list();
        session.getTransaction().commit();
        session.close();
        return new ContactsInGroups(result);
    }

    /*public Contacts valid(int validId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Contacts> result = session.createQuery("from ContactData where contact_id = '" + validId + "'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }*/

}
