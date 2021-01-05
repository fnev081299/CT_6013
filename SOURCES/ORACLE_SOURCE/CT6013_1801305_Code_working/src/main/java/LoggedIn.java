/**
 * check if user is logged in
 */
public class LoggedIn {
    /**
     * login static value
     */
    private static Boolean logAuth;

    /**
     * Return the login check
     * @return login authentication
     */
    public boolean getLogAuth(){
        return this.logAuth;
    }

    /**
     * Set the login to true
     */
    public void setTrue(){
        this.logAuth = true;
    }

    /**
     * Set the login to false
     */
    public void setfalse(){
        this.logAuth = false;
    }
}
