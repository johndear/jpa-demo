package many2many;

import common.Helper;
import many2many.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] a) throws Exception {
        em.getTransaction().begin();


        // Bidirectional Many To Many Mapping
        Student3 student = new Student3();
        student.setName("Joe");
        em.persist(student);

        Student3 student1 = new Student3();
        student1.setName("Joe");
        em.persist(student1);

        Department3 dept = new Department3();
        dept.setName("dept11111");
        dept.addStudent(student);

        dept.addStudent(student1);
        em.persist(dept);

        em.flush();

        // Many To Many Mapping and Remove Operation
        // 可以删除
//        em.remove(student);
//        em.remove(student1);
        // 注释掉上面任一行，就不能删除成功，受限mappedby，哪边就是关系控制方
//        em.remove(dept);

        // Many To Many based on Collection
        Department3 dept2 = new Department3();
        dept2.setName("dept22222");
        em.persist(dept2);

        Department3 department3 = em.find(Department3.class, 2);
        student = em.find(Student3.class, 1);
        department3.getStudents().add(student);
        student.getDepartments().add(department3);

        Query query = em.createQuery("SELECT e FROM Student3 e");
        List<Student3> list = (List<Student3>) query.getResultList();
        System.out.println(list);

        query = em.createQuery("SELECT d FROM Department3 d");
        List<Department3> dList = (List<Department3>) query.getResultList();
        System.out.println(dList);

        em.getTransaction().commit();
        em.close();
        emf.close();

        Helper.checkData();
    }
}
