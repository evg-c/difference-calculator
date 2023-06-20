package hexlet.code;
public final class ElementOfDiff {
    private String keyDiff;
    private String statusDiff;
    private Object oldValue;
    private Object newValue;
    ElementOfDiff(String key, String status, Object oldvalueDiff, Object newvalueDiff) {
        keyDiff = key;
        statusDiff = status;
        oldValue = oldvalueDiff;
        newValue = newvalueDiff;
    }
    public String getKeyDiff() {
        return this.keyDiff;
    }
    public String getStatusDiff() {
        return this.statusDiff;
    }
    public Object getOldValue() {
        return this.oldValue;
    }
    public Object getNewValue() {
        return this.newValue;
    }
}
