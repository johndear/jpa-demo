package many2many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2016/11/15.
 */
@Entity
public class Department3 {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy="departments")
    private Collection<Student3> students;

    public Department3(){
        students = new ArrayList<Student3>();
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
    public void addStudent(Student3 student) {
        if (!getStudents().contains(student)) {
            getStudents().add(student);
        }
        if (!student.getDepartments().contains(this)) {
            student.getDepartments().add(this);
        }
    }
    public Collection<Student3> getStudents() {
        return students;
    }

    public void setStudent(Collection<Student3> students) {
        this.students = students;
    }

    public String toString() {
        return "Department id: " + getId() +
                ", name: " + getName();
    }
}
