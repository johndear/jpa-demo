package many2many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2016/11/15.
 */
@Entity
public class Student3 {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(name="Student_Dept",
            joinColumns=@JoinColumn(name="Stut_ID"),
            inverseJoinColumns=@JoinColumn(name="DEPT_ID"))
    private Collection<Department3> departments;

    public Student3() {
        departments = new ArrayList<Department3>();
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

    public void setName(String name) {
        this.name = name;
    }
    public void addDepartment(Department3 department) {
        if (!getDepartments().contains(department)) {
            getDepartments().add(department);
        }
        if (!department.getStudents().contains(this)) {
            department.getStudents().add(this);
        }
    }


    public Collection<Department3> getDepartments() {
        return departments;
    }

    public void setDepartment(Collection<Department3> departments) {
        this.departments = departments;
    }

    public String toString() {
        return "\n\nID:" + id + "\nName:" + name ;
    }
}
