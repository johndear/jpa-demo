package one2many;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;

import java.util.*;

import javax.persistence.*;

@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;

//    @OneToMany(mappedBy="department",targetEntity=Student2.class)
//    @BatchSize(size=1)
//    @Where(clause="DEL_FLAG=1")
//    @OrderBy("id desc")
//    private List<Student2> students;

    @OneToMany(mappedBy="department")
    @MapKey(name="name")
    private Map<String, Student> students;



//    private Department parent;
    
    public Department(){
//        students = new ArrayList<Student2>();
        students = new HashMap<String, Student>();
    }

    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String deptName) {
        this.name = deptName;
    }
    public void addStudent(Student student) {
//      if (!getStudents().contains(student)) {
//          getStudents().add(student);
//          if (student.getDepartment() != null) {
//              student.getDepartment().getStudents().remove(student);
//          }
//          student.setDepartment(this);
//      }

        if (!getStudents().containsKey(student.getName())) {
            getStudents().put(student.getName(), student);
            if (student.getDepartment() != null) {
                student.getDepartment().getStudents().remove(student.getName());
            }
            student.setDepartment(this);
        }
  }
    public Map<String, Student> getStudents() {
      return students;
    }

    public void setStudent(Map<String, Student> students) {
      this.students = students;
    }


    public String toString() {
        return "Department id: " + getId() + 
               ", name: " + getName() + " with " + students;
    }
}
