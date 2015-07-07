package managedbeans;

import controller.LearningAgreementController;
import controller.StudentController;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author joe
 */
@Named
@SessionScoped
public class LearningAgreementBean implements Serializable {

    @EJB
    private StudentController studentController;
    @EJB
    private LearningAgreementController learningAgreementController;
    
    /**
     * Creates a new instance of LearningAgreementBean
     */
    public LearningAgreementBean() {
    }
    
    
}
