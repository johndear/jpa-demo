package version;

import common.Helper;
import one2one.Address2;
import one2one.Student2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

//  ����version���ֹ���ʵ��
//  ����취
//  1������StaleObjectStateException�쳣����ʾ���ݹ�ʱ�ѱ��޸ģ����û������ύ
//  2��������ҵ����ȥ��С����飬�����Խ�����ֹ������������ĸ��ʾ�Խ��
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

    // ��������ͬ������ͬʱ��ȡ��һ�����ݲ������޸�ʱ�����ʱ�����ͻ��׳� org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)�쳣��
    Employee e1 = em.find(Employee.class, 0);
    Employee e2 = em2.find(Employee.class, 0);

    // һ����������ͬ����������
//    e1.setName("zs");
//    System.out.println("transantion 1 begin version:" + e1.getVersion());
//    em.getTransaction().commit();
//    System.out.println("transantion 1 submit version:" + e1.getVersion());

//    e2.setName("lisi");
//    System.out.println("transantion 2 begin version:" + e2.getVersion());
//    em2.getTransaction().commit();
//    System.out.println("transantion 2 submit version:" + e2.getVersion());

    // �ڶ������������������������2������������1����
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
