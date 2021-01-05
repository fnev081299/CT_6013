import Models.ModuleModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * NOT USED
 */
@Stateless(name = "ModuleCreationEJB")
public class ModuleCreationBean {
    @EJB
    OracleClientProvideBean oracleClientProvideBeanBean;
    public ModuleCreationBean() {
    }

    public void createModule(ModuleModel mModel) {
        String insertModule = "INSERT INTO tblModules"
                + "(Module_ID, Module_Code, ModuleName, YearGroup)" + "VALUES ("
                + "module_id_seq.nextval, "
                + " '" + mModel.getModule_Code() + "', "
                + " '" + mModel.getModule_Name() + "', "
                + " " + mModel.getYearGroup() + ")";

        Statement stmt = null;
        try{
            Connection con = oracleClientProvideBeanBean.getOracleClient();
            stmt = con.createStatement();
            System.out.println(insertModule);

            // execute insert SQL statement
            stmt.executeUpdate(insertModule);

            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<ModuleModel> getModuleList(){
        String query = "Select * from tblModules";
        ArrayList module_list = new ArrayList();

        Statement stmt = null;
        try {
            Connection con = oracleClientProvideBeanBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                ModuleModel module = new ModuleModel();
                module.setModule_ID(rs.getLong("Module_ID"));
                module.setModule_Code(rs.getString("Module_Code"));
                module.setModule_Name(rs.getString("ModuleName"));
                module.setYearGroup(rs.getInt("YearGroup"));

                module_list.add(module);
            }
            stmt.close();

            return module_list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
