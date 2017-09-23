package com.dot.pojo;

public class TbDeviceGroup {
    private Integer id;

    private String name;

    private String description;

    private Integer onlinenumber;

    private Integer offlinenumber;
    
    private Integer unregeisterednumber;    

    public Integer getUnregeisterednumber() {
		return unregeisterednumber;
	}

	public void setUnregeisterednumber(Integer unregeisterednumber) {
		this.unregeisterednumber = unregeisterednumber;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getOnlinenumber() {
        return onlinenumber;
    }

    public void setOnlinenumber(Integer onlinenumber) {
        this.onlinenumber = onlinenumber;
    }

    public Integer getOfflinenumber() {
        return offlinenumber;
    }

    public void setOfflinenumber(Integer offlinenumber) {
        this.offlinenumber = offlinenumber;
    }
}