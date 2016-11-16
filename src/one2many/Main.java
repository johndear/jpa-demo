package one2many;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import common.Helper;
import one2one.Department2;

public class Main {
  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");
  static EntityManager em = emf.createEntityManager();

  public static void main(String[] a) throws Exception {
    em.getTransaction().begin();
    
    
    Student student = new Student();
    student.setName("baaJoe");
    em.persist(student);

    Student student1 = new Student();
    student1.setName("abJoe");
    em.persist(student1);

    Department dept = new Department();
    dept.setName("dept name");

    dept.addStudent(student);
    dept.addStudent(student1);

    student.setDepartment(dept);
//    student1.setDepartment(dept);

//    em.persist(dept); // 设置了cascade=CascadeType.ALL，在student.save()的时候dept = new Dept(),student.setDept(dept),就不需要执行em.persist(dept); 会级联保存

    Phone phone = new Phone();
    phone.setNumber("123123123123");
    em.persist(phone);


    em.flush();

    Student emp = em.find(Student.class, 1);
    phone = em.find(Phone.class, 1);
    emp.addPhone(phone);

    // one2one

    Address address = new Address();
    address.setCity("gz");
    em.persist(address);

//    student.setAddress(address);

    Department2 dept22 = new Department2();
    dept22.setName("dept name");
    //address.setDepartment(dept22);



    Query query = em.createQuery("SELECT e FROM Student2 e");
    List<Student> list = (List<Student>) query.getResultList();
    System.out.println(list);

//    System.out.println(list.get(0).getAddress());

    query = em.createQuery("SELECT d FROM Department d");
    List<Department> dList = (List<Department>) query.getResultList();
    System.out.println(dList);

    query = em.createQuery("SELECT d FROM Phone d");
    List<Phone> phoneList = (List<Phone>) query.getResultList();
    System.out.println(phoneList);

    em.getTransaction().commit();
    em.close();
    emf.close();

    Helper.checkData();
  }
}
