package Models;

import java.util.ArrayList;

/**
 * Modules currently available
 */
public class Modules {
    // to get the course for changing the grade

    /**
     * Get the module from the course code
     * @param Course_Code cousre code
     * @return module
     */
    public String getModule(String Course_Code){
        if(Course_Code.equals("CT6013")){
            return "Advanced_Databases";
        } else if(Course_Code.equals("CT6046")){
            return "Robotics";
        } else if(Course_Code.equals("CT6042")){
            return "Secure_Coding";
        } else if(Course_Code.equals("CT6027")){
            return "Advanced_Topics";
        } else if(Course_Code.equals("CT6038")){
            return "Dissertation_Proposal";
        } else if(Course_Code.equals("CT6039")){
            return "Dissertation";
        } else if(Course_Code.equals("CT5022")){
            return "Project_Management";
        } else if(Course_Code.equals("CT5038")){
            return "Agile";
        } else if(Course_Code.equals("CT5055")){
            return "Artificial_Intelligence";
        } else if(Course_Code.equals("CT5057")){
            return "Algorithms";
        } else if(Course_Code.equals("CT5025")){
            return "OOP";
        } else if(Course_Code.equals("CT4009")){
            return "Web_Development";
        } else if(Course_Code.equals("CT4021")){
            return "Intro_to_Programming";
        } else if(Course_Code.equals("CT4023")){
            return "System_Design";
        } else if(Course_Code.equals("CT4020")){
            return "Smart_Business";
        } else if(Course_Code.equals("CT4022")){
            return "Intro_to_Security";
        } else {
            return "None";
        }
    }

    /**
     * Get all the modules
     * @return list of modules
     */
    public ArrayList<String> allModules(){
        ArrayList modules = new ArrayList();

        modules.add("CT6013");
        modules.add("CT6046");
        modules.add("CT6042");
        modules.add("CT6027");
        modules.add("CT6038");
        modules.add("CT6039");

        modules.add("CT5022");
        modules.add("CT5038");
        modules.add("CT5055");
        modules.add("CT5057");
        modules.add("CT5025");

        modules.add("CT4009");
        modules.add("CT4021");
        modules.add("CT4023");
        modules.add("CT4020");
        modules.add("CT4022");


        return modules;
    }
}