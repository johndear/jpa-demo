package many2one;

import common.Helper;
import many2many.Department3;
import many2many.Student3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public class Main4 {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] a) throws Exception {
        em.getTransaction().begin();


        Student4 student = new Student4();
        student.setDob(Calendar.getInstance().getTime());
        student.setName("Joe");
        em.persist(student);

        Thread.sleep(5000);

        Student4 student2 = new Student4();
        student2.setDob(Calendar.getInstance().getTime());
        student2.setName("abc");
        em.persist(student2);

        Department4 dept = new Department4();
        dept.setName("dept444");

        student.setDepartment(dept);
        student2.setDepartment(dept);
        // em.remove(student);

        Query query = em.createQuery("SELECT e FROM Student4 e");
        List<Student4> list = (List<Student4>) query.getResultList();
        System.out.println(list);

        List<Student4> emps = em.createQuery("SELECT e FROM Student4 e").getResultList();
        for (Student4 emp : emps) {
            if (emp.getDepartment() != null) {
               System.out.print("------------"+emp.getDepartment().getName());
            }
        }

        em.flush();

        System.out.println(emps);

        em.getTransaction().commit();
        em.close();
        emf.close();

        Helper.checkData();
    }
}
