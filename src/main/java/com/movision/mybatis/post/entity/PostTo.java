package com.movision.mybatis.post.entity;

import java.io.Serializable;
import java.util.Date;

public class PostTo implements Serializable {
    private Integer id;

    private Integer heatvalue;

    private String circleid;

    private String title;

    private String subtitle;

    private String postcontent;

    private String zansum;

    private String commentsum;

    private String forwardsum;

    private String collectsum;

    private String isactive;

    private Integer partsum_enddays;//当前活动显示参与人数还是显示剩余结束天数 0 显示结束天数 1 显示活动参与人数

    private String activetype;

    private Double activefee;

    private String type;

    private String ishot;

    private String isessence;

    private String isessencepool;

    private Integer orderid;

    private String coverimg;

    private String hotimgurl;

    private Date intime;

    private String totalpoint;

    private String isdel;

    private Date essencedate;//精选日期

    private String userid;//用户id

    private Date begintime;

    private Date endtime;

    private String activestatue;

    private String iscontribute;//是否投稿

    private Date oprtime;//用户最后参与时间

    private Integer ishotorder;//热门排序

    private Integer category;//0图文贴1纯图片贴2视频贴

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getHeatvalue() {
        return heatvalue;
    }

    public void setHeatvalue(Integer heatvalue) {
        this.heatvalue = heatvalue;
    }

    public Integer getIshotorder() {
        return ishotorder;
    }

    public void setIshotorder(Integer ishotorder) {
        this.ishotorder = ishotorder;
    }

    public Date getOprtime() {
        return oprtime;
    }

    public void setOprtime(Date oprtime) {
        this.oprtime = oprtime;
    }

    public String getIscontribute() {
        return iscontribute;
    }

    public void setIscontribute(String iscontribute) {
        this.iscontribute = iscontribute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCircleid() {
        return circleid;
    }

    public void setCircleid(String circleid) {
        this.circleid = circleid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPostcontent() {
        return postcontent;
    }

    public void setPostcontent(String postcontent) {
        this.postcontent = postcontent;
    }

    public String getZansum() {
        return zansum;
    }

    public void setZansum(String zansum) {
        this.zansum = zansum;
    }

    public String getCommentsum() {
        return commentsum;
    }

    public void setCommentsum(String commentsum) {
        this.commentsum = commentsum;
    }

    public String getForwardsum() {
        return forwardsum;
    }

    public void setForwardsum(String forwardsum) {
        this.forwardsum = forwardsum;
    }

    public String getCollectsum() {
        return collectsum;
    }

    public void setCollectsum(String collectsum) {
        this.collectsum = collectsum;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getActivetype() {
        return activetype;
    }

    public void setActivetype(String activetype) {
        this.activetype = activetype;
    }

    public Double getActivefee() {
        return activefee;
    }

    public void setActivefee(Double activefee) {
        this.activefee = activefee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    public String getIsessence() {
        return isessence;
    }

    public void setIsessence(String isessence) {
        this.isessence = isessence;
    }

    public String getIsessencepool() {
        return isessencepool;
    }

    public void setIsessencepool(String isessencepool) {
        this.isessencepool = isessencepool;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public String getHotimgurl() {
        return hotimgurl;
    }

    public void setHotimgurl(String hotimgurl) {
        this.hotimgurl = hotimgurl;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public String getTotalpoint() {
        return totalpoint;
    }

    public void setTotalpoint(String totalpoint) {
        this.totalpoint = totalpoint;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public Date getEssencedate() {
        return essencedate;
    }

    public void setEssencedate(Date essencedate) {
        this.essencedate = essencedate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getActivestatue() {
        return activestatue;
    }

    public void setActivestatue(String activestatue) {
        this.activestatue = activestatue;
    }

    public Integer getPartsum_enddays() {
        return partsum_enddays;
    }

    public void setPartsum_enddays(Integer partsum_enddays) {
        this.partsum_enddays = partsum_enddays;
    }
}