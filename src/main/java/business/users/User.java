package business.users;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID=-2719078714048253502L;
    private int ID;
    protected final String username;
    public User(int ID,String username,String password){
        this.ID=ID;
        this.username=username;
        this.password=password;
    }
    public int getID() {
        return ID;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    protected final String password;
    public String getUserName(){ return this.username;}
    public void setID(int ID){ this.ID=ID;}
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
    @Override
    public int hashCode() {
        return username.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User))
            return false;
        return username.equals(((User)obj).username);
    }
}
