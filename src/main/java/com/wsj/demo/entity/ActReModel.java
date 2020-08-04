package com.wsj.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ActReModel)实体类
 *
 * @author makejava
 * @since 2020-07-31 10:08:55
 */
public class ActReModel implements Serializable {
    private static final long serialVersionUID = 399333826242832890L;
    
    private Object id_;
    
    private Object rev_;
    
    private Object name_;
    
    private Object key_;
    
    private Object category_;
    
    private Date createTime_;
    
    private Date lastUpdateTime_;
    
    private Object version_;
    
    private Object metaInfo_;
    
    private Object deploymentId_;
    
    private Object editorSourceValueId_;
    
    private Object editorSourceExtraValueId_;
    
    private Object tenantId_;
    /**
    * 是否包含子流程，0默认流程，1主流程，2子流程
    */
    private Object subtype_;


    public Object getId_() {
        return id_;
    }

    public void setId_(Object id_) {
        this.id_ = id_;
    }

    public Object getRev_() {
        return rev_;
    }

    public void setRev_(Object rev_) {
        this.rev_ = rev_;
    }

    public Object getName_() {
        return name_;
    }

    public void setName_(Object name_) {
        this.name_ = name_;
    }

    public Object getKey_() {
        return key_;
    }

    public void setKey_(Object key_) {
        this.key_ = key_;
    }

    public Object getCategory_() {
        return category_;
    }

    public void setCategory_(Object category_) {
        this.category_ = category_;
    }

    public Date getCreateTime_() {
        return createTime_;
    }

    public void setCreateTime_(Date createTime_) {
        this.createTime_ = createTime_;
    }

    public Date getLastUpdateTime_() {
        return lastUpdateTime_;
    }

    public void setLastUpdateTime_(Date lastUpdateTime_) {
        this.lastUpdateTime_ = lastUpdateTime_;
    }

    public Object getVersion_() {
        return version_;
    }

    public void setVersion_(Object version_) {
        this.version_ = version_;
    }

    public Object getMetaInfo_() {
        return metaInfo_;
    }

    public void setMetaInfo_(Object metaInfo_) {
        this.metaInfo_ = metaInfo_;
    }

    public Object getDeploymentId_() {
        return deploymentId_;
    }

    public void setDeploymentId_(Object deploymentId_) {
        this.deploymentId_ = deploymentId_;
    }

    public Object getEditorSourceValueId_() {
        return editorSourceValueId_;
    }

    public void setEditorSourceValueId_(Object editorSourceValueId_) {
        this.editorSourceValueId_ = editorSourceValueId_;
    }

    public Object getEditorSourceExtraValueId_() {
        return editorSourceExtraValueId_;
    }

    public void setEditorSourceExtraValueId_(Object editorSourceExtraValueId_) {
        this.editorSourceExtraValueId_ = editorSourceExtraValueId_;
    }

    public Object getTenantId_() {
        return tenantId_;
    }

    public void setTenantId_(Object tenantId_) {
        this.tenantId_ = tenantId_;
    }

    public Object getSubtype_() {
        return subtype_;
    }

    public void setSubtype_(Object subtype_) {
        this.subtype_ = subtype_;
    }

}