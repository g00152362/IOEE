package com.dot.pojo;

import java.util.Date;

public class TbUserSetting {
    private Integer id;

    private String userName;

    private String pageName;

    private String settingJson;

    private String settingJson2;

    private Date updatedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getSettingJson() {
        return settingJson;
    }

    public void setSettingJson(String settingJson) {
        this.settingJson = settingJson == null ? null : settingJson.trim();
    }

    public String getSettingJson2() {
        return settingJson2;
    }

    public void setSettingJson2(String settingJson2) {
        this.settingJson2 = settingJson2 == null ? null : settingJson2.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}