package com.boiledcoffee.sjwatch.model.entity;

import java.util.Date;

public class Repair {
    private Long repairId;

    private String code;

    private String cover;

    private String wxName;

    private Date createTime;

    private Date modifyTime;

    private Date repaireTime;

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName == null ? null : wxName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getRepaireTime() {
        return repaireTime;
    }

    public void setRepaireTime(Date repaireTime) {
        this.repaireTime = repaireTime;
    }
}