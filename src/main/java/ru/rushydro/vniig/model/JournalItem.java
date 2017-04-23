package ru.rushydro.vniig.model;

//import ru.rushydro.vniig.storage.entry.SignSysStorage;

import java.util.Date;

/**
 * Created by nikolay on 08.11.15.
 */
public class JournalItem {

    Integer type;

    Date date;

    Date time;

    String objMonitor;

    String name;

    String text;

    String status;

    Date kvintDate;

    Date kvintTime;

//    public JournalItem(PassportParamSys passportParamSys) {
//        type = passportParamSys.getType();
//        date = passportParamSys.getSignSys().getDateSign();
//        time = passportParamSys.getSignSys().getTimeSign();
//
//        objMonitor = passportParamSys.getObjMonitor();
//        name = passportParamSys.getName();
//        text = passportParamSys.getText();
//
//        if ((type==2 || type==3) && passportParamSys.isKvint()) {
//            status = "Квит.";
//        } else if ((type == 2 || type == 3)) {
//            status = "Не квит.";
//        } else if (type==1) {
//            status = "Норма";
//        } else if (type==4) {
//            status = "Отключен";
//        }
//
//        kvintDate = passportParamSys.getSignSys().getDateKvint();
//        kvintTime = passportParamSys.getSignSys().getTimeKvint();
//    }

//    public JournalItem(SignSysStorage signSysStorage) {
//        type = signSysStorage.getType();
//        date = signSysStorage.getDateSign();
//        time = signSysStorage.getTimeSign();
//
//        objMonitor = signSysStorage.getPassportParamSys().getObjMonitor();
//        name = signSysStorage.getPassportParamSys().getName();
//        text = signSysStorage.getText();
//
//        if ((type==2 || type==3) && signSysStorage.isKvint()) {
//            status = "Квит.";
//        } else if ((type == 2 || type == 3)) {
//            status = "Не квит.";
//        } else if (type==1) {
//            status = "Норма";
//        } else if (type==4) {
//            status = "Отключен";
//        }
//
//    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getObjMonitor() {
        return objMonitor;
    }

    public void setObjMonitor(String objMonitor) {
        this.objMonitor = objMonitor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getKvintDate() {
        return kvintDate;
    }

    public void setKvintDate(Date kvintDate) {
        this.kvintDate = kvintDate;
    }

    public Date getKvintTime() {
        return kvintTime;
    }

    public void setKvintTime(Date kvintTime) {
        this.kvintTime = kvintTime;
    }
}
