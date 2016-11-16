package one2one;

import common.Helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {
  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");
  static EntityManager em = emf.createEntityManager();

  public static void main(String[] a) throws Exception {
    em.getTransaction().begin();
    
    
    Student2 student = new Student2();
    student.setName("Joe");
    Address2 address = new Address2();
    address.setCity("city");
    address.setStudent(student);
  //em.persist(address);
    
    student.setAddress(address);
      
    em.persist(student);
    em.flush();
    em.getTransaction().commit();

    
    
    
    Query query = em.createQuery("SELECT e FROM Address2 e");
    List<Address2> list = (List<Address2>) query.getResultList();
    System.out.println(list.get(0).getStudent());
    
    
    em.close();
    emf.close();
    
    Helper.checkData();
  }
}
