package many2one;

import one2many.Department;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/15.
 */
@Entity
public class Student4 {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dob;

    @Basic
    private Date createTime = new Date();

    @ManyToOne(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="DEPT_ID")
    private Department4 department;

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



    public Department4 getDepartment() {
        return department;
    }

    public void setDepartment(Department4 department) {
        this.department = department;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return "\n\nID:" + id + "\nName:" + name + "\n\n" + department;
    }

}
