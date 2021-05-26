package business.users;

import java.io.Serial;

public class Admin extends User{
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    public Admin(int ID,String username, String password) {
        super(ID,username, password);
    }
}
