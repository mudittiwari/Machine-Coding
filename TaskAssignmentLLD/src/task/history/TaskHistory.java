package task.history;

import java.util.Date;

public interface TaskHistory {

    public String getTaskId() ;

    public String getFieldChanged() ;

    public String getOldValue();

    public String getNewValue() ;

    public String getModifiedBy() ;

    public Date getModifiedAt() ;
}
