package com.mage.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @param
 * @author qzp
 * @create 2020-02-21 22:52
 */
public class Account {
    private Integer id;
    private String aname;
    private BigDecimal money;
    private Date createTime;
    private Date updateTime;
    private String remarker;
    private Integer userId;
    private Integer type;

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarker() {
        return remarker;
    }

    public void setRemarker(String remarker) {
        this.remarker = remarker;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", aname='" + aname + '\'' +
                ", money=" + money +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remarker='" + remarker + '\'' +
                ", userId=" + userId +
                ", type=" + type +
                '}';
    }
}
