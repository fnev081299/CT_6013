import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton(name = "OracleClientProvideBeanEJB")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
/**
 * Connection to the Database
 */
public class OracleClientProvideBean {
    public OracleClientProvideBean() {
    }
    private Connection oracleClient = null;
    @Lock(LockType.READ)
    public Connection getOracleClient(){return oracleClient;}

    @PostConstruct
    public void init() {
        try {
            // Get JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("-- Oracle JDBC missing! --");
            e.printStackTrace();
        }
        
        try {
            // connect to the DB using username and password
            oracleClient = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//oracle.glos.ac.uk:1521/orclpdb.chelt.local", "s1801305",
                    "26439Ash!");
            if(oracleClient != null){
                System.out.println("-- Connection Successful --");
            } else {
                System.out.println("-- Failed Connection --");
            }
        } catch (SQLException e) {
            System.out.println("-- Failed Connection, Check output console --");
            e.printStackTrace();
        }
    }
}
