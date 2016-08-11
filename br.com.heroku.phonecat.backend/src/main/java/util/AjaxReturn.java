package util;

import java.util.List;

public class AjaxReturn {

    private boolean success;
    private String alerts;
    private String errors;
    private String returnValue;

    public AjaxReturn() {
        success = true;
        alerts = "";
        errors = "";
        returnValue="";
    }

    public void addAlert(String alert){
        alerts += (alert);
    }

    public void addAlerts(List<String> alerts){
        for (int i = 0; i < alerts.size(); i++) {
            addError(alerts.get(i));
        }
    }

    public void addError(String error){
        errors += (error);
    }

    public void addErrors(List<String> errors){
        for (int i = 0; i < errors.size(); i++) {
            addError(errors.get(i));
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSucess(boolean sucess) {
        this.success = sucess;
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlert(String alert) {
        this.alerts = alert;
    }

    public String getError() {
        return errors;
    }

    public void setError(String erros) {
        this.errors = erros;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

}
