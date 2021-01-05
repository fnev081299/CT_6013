package Models;

import java.io.Serializable;

/**
 * Basic module model
 */
public class ModuleModel implements Serializable {
    /**
     * Variables needed
     */
    private String module_Code;
    private Integer passes;

    /**
     * Return the pass value
     * @return pass
     */
    public Integer getPasses() {
        return passes;
    }

    /**
     * Set module passes
     * @param passes passes
     */
    public void setPasses(Integer passes) {
        this.passes = passes;
    }

    /**
     * Return the module code
     * @return module code
     */
    public String getModule_Code() {
        return module_Code;
    }

    /**
     * Set the module code
     * @param module_Code module code
     */
    public void setModule_Code(String module_Code) {
        this.module_Code = module_Code;
    }
}
