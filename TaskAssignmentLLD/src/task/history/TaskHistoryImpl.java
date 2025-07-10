package task.history;
import java.util.Date;

public class TaskHistoryImpl implements TaskHistory{
    private final String taskId;
    private final String fieldChanged;
    private final String oldValue;
    private final String newValue;
    private final String modifiedBy;
    private final Date modifiedAt;

    public TaskHistoryImpl(String taskId, String fieldChanged, String oldValue, String newValue, String modifiedBy) {
        this.taskId = taskId;
        this.fieldChanged = fieldChanged;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = new Date();
    }

    @Override
    public String getTaskId() {
        return taskId;
    }

    @Override
    public String getFieldChanged() {
        return fieldChanged;
    }

    @Override
    public String getOldValue() {
        return oldValue;
    }

    @Override
    public String getNewValue() {
        return newValue;
    }

    @Override
    public String getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public Date getModifiedAt() {
        return modifiedAt;
    }
}

