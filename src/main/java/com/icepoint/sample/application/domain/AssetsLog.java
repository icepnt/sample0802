package com.icepoint.sample.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AssetsLog.
 */
@Entity
@Table(name = "assets_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AssetsLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "digital_code")
    private String digitalCode;

    @Column(name = "cust_id")
    private Long custId;

    @Column(name = "security_account_code")
    private String securityAccountCode;

    @Column(name = "assets_type")
    private Integer assetsType;

    @Column(name = "assets_code")
    private String assetsCode;

    @Column(name = "assets_subtype")
    private Integer assetsSubtype;

    @Column(name = "trans_datetime")
    private Instant transDatetime;

    @Column(name = "trade_amt")
    private Float tradeAmt;

    @Column(name = "fee_amt")
    private Float feeAmt;

    @Column(name = "start_bal")
    private Float startBal;

    @Column(name = "end_bal")
    private Float endBal;

    @Column(name = "trade_obj_type")
    private Integer tradeObjType;

    @Column(name = "trade_obj_id")
    private Long tradeObjId;

    @Column(name = "description")
    private String description;

    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties("logs")
    private Employee employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDigitalCode() {
        return digitalCode;
    }

    public AssetsLog digitalCode(String digitalCode) {
        this.digitalCode = digitalCode;
        return this;
    }

    public void setDigitalCode(String digitalCode) {
        this.digitalCode = digitalCode;
    }

    public Long getCustId() {
        return custId;
    }

    public AssetsLog custId(Long custId) {
        this.custId = custId;
        return this;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getSecurityAccountCode() {
        return securityAccountCode;
    }

    public AssetsLog securityAccountCode(String securityAccountCode) {
        this.securityAccountCode = securityAccountCode;
        return this;
    }

    public void setSecurityAccountCode(String securityAccountCode) {
        this.securityAccountCode = securityAccountCode;
    }

    public Integer getAssetsType() {
        return assetsType;
    }

    public AssetsLog assetsType(Integer assetsType) {
        this.assetsType = assetsType;
        return this;
    }

    public void setAssetsType(Integer assetsType) {
        this.assetsType = assetsType;
    }

    public String getAssetsCode() {
        return assetsCode;
    }

    public AssetsLog assetsCode(String assetsCode) {
        this.assetsCode = assetsCode;
        return this;
    }

    public void setAssetsCode(String assetsCode) {
        this.assetsCode = assetsCode;
    }

    public Integer getAssetsSubtype() {
        return assetsSubtype;
    }

    public AssetsLog assetsSubtype(Integer assetsSubtype) {
        this.assetsSubtype = assetsSubtype;
        return this;
    }

    public void setAssetsSubtype(Integer assetsSubtype) {
        this.assetsSubtype = assetsSubtype;
    }

    public Instant getTransDatetime() {
        return transDatetime;
    }

    public AssetsLog transDatetime(Instant transDatetime) {
        this.transDatetime = transDatetime;
        return this;
    }

    public void setTransDatetime(Instant transDatetime) {
        this.transDatetime = transDatetime;
    }

    public Float getTradeAmt() {
        return tradeAmt;
    }

    public AssetsLog tradeAmt(Float tradeAmt) {
        this.tradeAmt = tradeAmt;
        return this;
    }

    public void setTradeAmt(Float tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public Float getFeeAmt() {
        return feeAmt;
    }

    public AssetsLog feeAmt(Float feeAmt) {
        this.feeAmt = feeAmt;
        return this;
    }

    public void setFeeAmt(Float feeAmt) {
        this.feeAmt = feeAmt;
    }

    public Float getStartBal() {
        return startBal;
    }

    public AssetsLog startBal(Float startBal) {
        this.startBal = startBal;
        return this;
    }

    public void setStartBal(Float startBal) {
        this.startBal = startBal;
    }

    public Float getEndBal() {
        return endBal;
    }

    public AssetsLog endBal(Float endBal) {
        this.endBal = endBal;
        return this;
    }

    public void setEndBal(Float endBal) {
        this.endBal = endBal;
    }

    public Integer getTradeObjType() {
        return tradeObjType;
    }

    public AssetsLog tradeObjType(Integer tradeObjType) {
        this.tradeObjType = tradeObjType;
        return this;
    }

    public void setTradeObjType(Integer tradeObjType) {
        this.tradeObjType = tradeObjType;
    }

    public Long getTradeObjId() {
        return tradeObjId;
    }

    public AssetsLog tradeObjId(Long tradeObjId) {
        this.tradeObjId = tradeObjId;
        return this;
    }

    public void setTradeObjId(Long tradeObjId) {
        this.tradeObjId = tradeObjId;
    }

    public String getDescription() {
        return description;
    }

    public AssetsLog description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public AssetsLog remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Employee getEmployee() {
        return employee;
    }

    public AssetsLog employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssetsLog assetsLog = (AssetsLog) o;
        if (assetsLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetsLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetsLog{" +
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
            "}";
    }
}
