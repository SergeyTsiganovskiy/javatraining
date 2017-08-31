package ru.stqa.pft.soap.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.soap.model.User;

import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;
  private ApplicationManager app;

  public DbHelper(ApplicationManager app) {
    this.app = app;
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public List<User> users(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    return session.createQuery("from User").list();
  }

}
