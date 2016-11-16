package version;

import common.Helper;
import one2one.Address2;
import one2one.Student2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

//  基于version的乐观锁实现
//  解决办法
//  1、捕获StaleObjectStateException异常，提示数据过时已被修改，让用户重新提交
//  2、尽量从业务方面去减小事务块，事务块越大，由乐观锁引起的问题的概率就越大
public class Main {

  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");

  public static void main(String[] a) throws Exception {

    EntityManager em = emf.createEntityManager();
    EntityManager em2 = emf.createEntityManager();

    em.getTransaction().begin();
    em2.getTransaction().begin();

    Employee e = new Employee();
    em.persist(e);
    em.flush();

    // 当两个不同的事务同时读取到一条数据并进行修改时，这个时候程序就会抛出 org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)异常。
    Employee e1 = em.find(Employee.class, 0);
    Employee e2 = em2.find(Employee.class, 0);

    // 一种是两个不同的事务的情况
//    e1.setName("zs");
//    System.out.println("transantion 1 begin version:" + e1.getVersion());
//    em.getTransaction().commit();
//    System.out.println("transantion 1 submit version:" + e1.getVersion());

//    e2.setName("lisi");
//    System.out.println("transantion 2 begin version:" + e2.getVersion());
//    em2.getTransaction().commit();
//    System.out.println("transantion 2 submit version:" + e2.getVersion());

    // 第二种情况是子事务的情况，事务2被包裹在事务1里面
    e2.setName("lisi");
    System.out.println("transantion 2 begin version:" + e2.getVersion());
    em2.getTransaction().commit();
    System.out.println("transantion 2 submit version:" + e2.getVersion());

    e1.setName("zs");
    System.out.println("transantion 1 begin version:" + e1.getVersion());
    em.getTransaction().commit();
    System.out.println("transantion 1 submit version:" + e1.getVersion());


    Query query = em.createQuery("SELECT e FROM Employee e");
    List<Employee> list = (List<Employee>) query.getResultList();
    System.out.println(list);
    
    
    em.close();
    emf.close();
    
    Helper.checkData();
  }
}
