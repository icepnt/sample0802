package com.icepoint.sample.application.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AssetsLog entity.
 */
public class AssetsLogDTO implements Serializable {

    private Long id;

    private String digitalCode;

    private Long custId;

    private String securityAccountCode;

    private Integer assetsType;

    private String assetsCode;

    private Integer assetsSubtype;

    private Instant transDatetime;

    private Float tradeAmt;

    private Float feeAmt;

    private Float startBal;

    private Float endBal;

    private Integer tradeObjType;

    private Long tradeObjId;

    private String description;

    private String remark;

    private Long employeeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDigitalCode() {
        return digitalCode;
    }

    public void setDigitalCode(String digitalCode) {
        this.digitalCode = digitalCode;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getSecurityAccountCode() {
        return securityAccountCode;
    }

    public void setSecurityAccountCode(String securityAccountCode) {
        this.securityAccountCode = securityAccountCode;
    }

    public Integer getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(Integer assetsType) {
        this.assetsType = assetsType;
    }

    public String getAssetsCode() {
        return assetsCode;
    }

    public void setAssetsCode(String assetsCode) {
        this.assetsCode = assetsCode;
    }

    public Integer getAssetsSubtype() {
        return assetsSubtype;
    }

    public void setAssetsSubtype(Integer assetsSubtype) {
        this.assetsSubtype = assetsSubtype;
    }

    public Instant getTransDatetime() {
        return transDatetime;
    }

    public void setTransDatetime(Instant transDatetime) {
        this.transDatetime = transDatetime;
    }

    public Float getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(Float tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public Float getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(Float feeAmt) {
        this.feeAmt = feeAmt;
    }

    public Float getStartBal() {
        return startBal;
    }

    public void setStartBal(Float startBal) {
        this.startBal = startBal;
    }

    public Float getEndBal() {
        return endBal;
    }

    public void setEndBal(Float endBal) {
        this.endBal = endBal;
    }

    public Integer getTradeObjType() {
        return tradeObjType;
    }

    public void setTradeObjType(Integer tradeObjType) {
        this.tradeObjType = tradeObjType;
    }

    public Long getTradeObjId() {
        return tradeObjId;
    }

    public void setTradeObjId(Long tradeObjId) {
        this.tradeObjId = tradeObjId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssetsLogDTO assetsLogDTO = (AssetsLogDTO) o;
        if (assetsLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetsLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetsLogDTO{" +
            "id=" + getId() +
            ", digitalCode='" + getDigitalCode() + "'" +
            ", custId=" + getCustId() +
            ", securityAccountCode='" + getSecurityAccountCode() + "'" +
            ", assetsType=" + getAssetsType() +
            ", assetsCode='" + getAssetsCode() + "'" +
            ", assetsSubtype=" + getAssetsSubtype() +
            ", transDatetime='" + getTransDatetime() + "'" +
            ", tradeAmt=" + getTradeAmt() +
            ", feeAmt=" + getFeeAmt() +
            ", startBal=" + getStartBal() +
            ", endBal=" + getEndBal() +
            ", tradeObjType=" + getTradeObjType() +
            ", tradeObjId=" + getTradeObjId() +
            ", description='" + getDescription() + "'" +
            ", remark='" + getRemark() + "'" +
            ", employee=" + getEmployeeId() +
            "}";
    }
}
