package business.users;

import java.io.Serial;

public class Employee extends User{
    @Serial
    private static final long serialVersionUID = 6529685098267697690L;
    public Employee(int ID,String username, String password) {
        super(ID,username, password);
    }
}
