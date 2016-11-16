package one2one;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Department2 {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne(mappedBy="department")
//    @PrimaryKeyJoinColumn//a primary key column that is used as a foreign key to join to another table
    private Address2 address2;


//    private Department parent;

    public Department2(){
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

    public Address2 getAddress2() {
        return address2;
    }

    public void setAddress2(Address2 address) {
        this.address2 = address;
    }

    public String toString() {
        return "Department id: " + getId() + 
               ", name: " + getName() + " with " ;
    }
}
