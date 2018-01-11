package com.boiledcoffee.sjwatch.model;

public class User {
    private Long id;

    private String userName;

    private String nickName;

    private String password;

    private String token;

    private Integer sex;

    private Integer age;

    private String qqToken;

    private String wechatToken;

    private Long createTime;

    private Long modifyTime;

    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getQqToken() {
        return qqToken;
    }

    public void setQqToken(String qqToken) {
        this.qqToken = qqToken == null ? null : qqToken.trim();
    }

    public String getWechatToken() {
        return wechatToken;
    }

    public void setWechatToken(String wechatToken) {
        this.wechatToken = wechatToken == null ? null : wechatToken.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}