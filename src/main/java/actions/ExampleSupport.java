/*
 * $Id: ExampleSupport.java 739661 2009-02-01 00:06:00Z davenewton $
 * 
 */

package actions;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Base Action class for the Tutorial package.
 */
public class ExampleSupport extends ActionSupport {

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
    
    
}
