package ru.platiza.service.calendar.error;

public enum  CalendarErrorEnum {

    GOV_DATA_NOT_FOUND("данные на госуслугах не найден")
    ;


    private String desc;

    public String getDesc() {
        return desc;
    }

    CalendarErrorEnum(String desc){
        this.desc = desc;
    }
}
