package com.jeecms.bbs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.common.hibernate4.PriorityInterface;
import com.jeecms.common.util.DateUtils;
import com.jeecms.core.entity.CmsRole;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.plug.live.entity.BbsLiveUserAccount;

/**
 * BBS用户和UnifiedUser一对一关系主要
 * 描述论坛用户信息相关
 * @author andy_hulibo@163.com
 * 2018年11月2日下午5:13:46
 */
@Entity
@Table(name = "jb_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
public class BbsUser implements PriorityInterface, Serializable {

    private static final long serialVersionUID = -6036262873974695603L;

    /**
     * 本地头像
     */
    public static final short AVATAR_LOCAL = 0;
    /**
     * 链接头像
     */
    public static final short AVATAR_LINK = 1;

    /**
     * 不禁言
     */
    public static final short PROHIBIT_NO = 0;
    /**
     * 永久禁言
     */
    public static final short PROHIBIT_FOREVER = 1;
    /**
     * 暂时禁言
     */
    public static final short PROHIBIT_TEMPORARY = 2;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 注册IP
     */
    @Column(name = "register_ip")
    private String registerIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 最后登录ip
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 登录次数
     */
    @Column(name = "login_count")
    private Integer loginCount;

    /**
     * 上传总大小
     */
    @Column(name = "upload_total")
    private Long uploadTotal;

    /**
     * 今日上传大小
     */
    @Column(name = "UPLOAD_TODAY")
    private Integer uploadToday;

    /**
     * 上传大小
     */
    @Column(name = "upload_size")
    private Integer uploadSize;

    /**
     * 上传日期
     */
    @Column(name = "upload_date")
    private java.sql.Date uploadDate;

    /**
     * 是否管理员
     */
    @Column(name = "is_admin")
    private Boolean admin;

    /**
     * 是否禁用
     */
    @Column(name = "is_disabled")
    private Boolean disabled;

    /**
     * 积分
     */
    @Column(name = "POINT")
    private Long point;

    /**
     * 威望
     */
    @Column(name = "PRESTIGE")
    private Long prestige;

    /**
     * 个人介绍
     */
    @Column(name = "INTRODUCTION")
    private String introduction;

    /**
     * 个性签名
     */
    @Column(name = "SIGNED")
    private String signed;

    /**
     * 个人头像
     */
    @Column(name = "AVATAR")
    private String avatar;

    /**
     * 头像类型
     */
    @Column(name = "AVATAR_TYPE")
    private Short avatarType;

    /**
     * 主题总数
     */
    @Column(name = "TOPIC_COUNT")
    private Integer topicCount;

    /**
     * 回复总数
     */
    @Column(name = "REPLY_COUNT")
    private Integer replyCount;

    /**
     * 精华总数
     */
    @Column(name = "PRIME_COUNT")
    private Integer primeCount;

    /**
     * 今日发帖
     */
    @Column(name = "POST_TODAY")
    private Integer postToday;

    /**
     * 最后回帖时间
     */
    @Column(name = "LAST_POST_TIME")
    private Date lastPostTime;

    /**
     * 禁言(0:不禁言;1:永久禁言;2:定期禁言)
     */
    @Column(name = "PROHIBIT_POST")
    private Short prohibitPost;

    /**
     * 解禁时间
     */
    @Column(name = "PROHIBIT_TIME")
    private Date prohibitTime;

    /**
     * 今日评分
     */
    @Column(name = "GRADE_TODAY")
    private Integer gradeToday;

    /**
     * 用户道具包现有容量
     */
    @Column(name = "magic_packet_size")
    private Integer magicPacketSize;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "is_official")
    private Boolean official;

    /**
     * 是否活动live主讲人
     */
    @Column(name = "live_host")
    private Boolean liveHost;

    /**
     * 管理员级别
     */
    @Column(name = "rank")
    private Integer rank;

    /**
     * 累计积分
     */
    @Column(name = "total_point")
    private Long totalPoint;

    /**
     * 是否只读
     */
    @Column(name = "read_only")
    private Boolean readOnly;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private BbsUserGroup group;

    @ManyToOne
    @JoinColumn(name = "active_level_id")
    private BbsUserActiveLevel activeLevel;

    @ManyToMany(mappedBy = "users")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private Set<CmsRole> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id desc")
    private Set<BbsLoginLog> loginLogs;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BbsMemberMagic> memberMagics;

    @OneToMany(mappedBy = "operater", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BbsOperation> operations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BbsSession> sessions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private Set<BbsFriendShip> myFriends;

    @OneToMany(mappedBy = "friend", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private Set<BbsFriendShip> toFriends;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<BbsTopicTypeSubscribe> subscribes;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private BbsLiveUserAccount liveUserAccount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private Set<BbsGiftUser> gifts;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "user")
    private BbsUserOnline userOnline;

    @ElementCollection
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    @CollectionTable(name = "jb_user_attr", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyColumn(name = "attr_name")
    @Column(name = "attr_value")
    private Map<String, String> attr;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private BbsUserExt userExt;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private Set<BbsTopicPostOperate> topicPostOperates;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private BbsUserAccount userAccount;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private Set<BbsThirdAccount> thirdAccounts;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "jb_user_attention", joinColumns = @JoinColumn(name = "attention_user_id", referencedColumnName = "user_id"),inverseJoinColumns = @JoinColumn(name = "be_attention_user_id", referencedColumnName = "user_id"))
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private Set<BbsUser> myAttentions;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "jb_user_attention", joinColumns = @JoinColumn(name = "be_attention_user_id", referencedColumnName = "user_id"),inverseJoinColumns = @JoinColumn(name = "attention_user_id", referencedColumnName = "user_id"))
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
    private Set<BbsUser> myFans;

    public void setUserAccount(BbsUserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public BbsUserExt getUserExt() {
        return userExt;
    }

    public void setUserExt(BbsUserExt userExt) {
        this.userExt = userExt;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Long getUploadTotal() {
        return uploadTotal;
    }

    public void setUploadTotal(Long uploadTotal) {
        this.uploadTotal = uploadTotal;
    }

    public Integer getUploadToday() {
        return uploadToday;
    }

    public void setUploadToday(Integer uploadToday) {
        this.uploadToday = uploadToday;
    }

    public Integer getUploadSize() {
        return uploadSize;
    }

    public void setUploadSize(Integer uploadSize) {
        this.uploadSize = uploadSize;
    }

    public java.sql.Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(java.sql.Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Long getPrestige() {
        return prestige;
    }

    public void setPrestige(Long prestige) {
        this.prestige = prestige;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSigned() {
        return signed;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Short getAvatarType() {
        return avatarType;
    }

    public void setAvatarType(Short avatarType) {
        this.avatarType = avatarType;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getPrimeCount() {
        return primeCount;
    }

    public void setPrimeCount(Integer primeCount) {
        this.primeCount = primeCount;
    }

    public Integer getPostToday() {
        return postToday;
    }

    public void setPostToday(Integer postToday) {
        this.postToday = postToday;
    }

    public Date getLastPostTime() {
        return lastPostTime;
    }

    public void setLastPostTime(Date lastPostTime) {
        this.lastPostTime = lastPostTime;
    }

    public Short getProhibitPost() {
        return prohibitPost;
    }

    public void setProhibitPost(Short prohibitPost) {
        this.prohibitPost = prohibitPost;
    }

    public Date getProhibitTime() {
        return prohibitTime;
    }

    public void setProhibitTime(Date prohibitTime) {
        this.prohibitTime = prohibitTime;
    }

    public Integer getGradeToday() {
        return gradeToday;
    }

    public void setGradeToday(Integer gradeToday) {
        this.gradeToday = gradeToday;
    }

    public Integer getMagicPacketSize() {
        return magicPacketSize;
    }

    public void setMagicPacketSize(Integer magicPacketSize) {
        this.magicPacketSize = magicPacketSize;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getOfficial() {
        return official;
    }

    public void setOfficial(Boolean official) {
        this.official = official;
    }

    public Boolean getLiveHost() {
        return liveHost;
    }

    public void setLiveHost(Boolean liveHost) {
        this.liveHost = liveHost;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Long getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Long totalPoint) {
        this.totalPoint = totalPoint;
    }

    public BbsUserOnline getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(BbsUserOnline userOnline) {
        this.userOnline = userOnline;
    }

    public BbsUserGroup getGroup() {
        return group;
    }

    public void setGroup(BbsUserGroup group) {
        this.group = group;
    }

    public BbsUserActiveLevel getActiveLevel() {
        return activeLevel;
    }

    public void setActiveLevel(BbsUserActiveLevel activeLevel) {
        this.activeLevel = activeLevel;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public Set<CmsRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<CmsRole> roles) {
        this.roles = roles;
    }

    public Set<BbsLoginLog> getLoginLogs() {
        return loginLogs;
    }

    public void setLoginLogs(Set<BbsLoginLog> loginLogs) {
        this.loginLogs = loginLogs;
    }

    public Set<BbsMemberMagic> getMemberMagics() {
        return memberMagics;
    }

    public void setMemberMagics(Set<BbsMemberMagic> memberMagics) {
        this.memberMagics = memberMagics;
    }

    public Set<BbsOperation> getOperations() {
        return operations;
    }

    public void setOperations(Set<BbsOperation> operations) {
        this.operations = operations;
    }

    public Set<BbsSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<BbsSession> sessions) {
        this.sessions = sessions;
    }

    public Set<BbsFriendShip> getMyFriends() {
        return myFriends;
    }

    public void setMyFriends(Set<BbsFriendShip> myFriends) {
        this.myFriends = myFriends;
    }

    public Set<BbsFriendShip> getToFriends() {
        return toFriends;
    }

    public void setToFriends(Set<BbsFriendShip> toFriends) {
        this.toFriends = toFriends;
    }

    public Set<BbsTopicTypeSubscribe> getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(Set<BbsTopicTypeSubscribe> subscribes) {
        this.subscribes = subscribes;
    }

    public Set<BbsTopicPostOperate> getTopicPostOperates() {
        return topicPostOperates;
    }

    public void setTopicPostOperates(Set<BbsTopicPostOperate> topicPostOperates) {
        this.topicPostOperates = topicPostOperates;
    }

    public BbsUserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccountSet(BbsUserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Set<BbsThirdAccount> getThirdAccounts() {
        return thirdAccounts;
    }

    public void setThirdAccounts(Set<BbsThirdAccount> thirdAccounts) {
        this.thirdAccounts = thirdAccounts;
    }

    public Set<BbsUser> getMyAttentions() {
        return myAttentions;
    }

    public void setMyAttentions(Set<BbsUser> myAttentions) {
        this.myAttentions = myAttentions;
    }

    public Set<BbsUser> getMyFans() {
        return myFans;
    }

    public void setMyFans(Set<BbsUser> myFans) {
        this.myFans = myFans;
    }

    public Set<BbsGiftUser> getGifts() {
        return gifts;
    }

    public void setGifts(Set<BbsGiftUser> gifts) {
        this.gifts = gifts;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public JSONObject convertToJson(CmsSite site, Integer https) throws JSONException {
        JSONObject json = new JSONObject();
        if (getId() != null) {
            json.put("id", getId());
        } else {
            json.put("id", "");
        }
        if (StringUtils.isNotBlank(getUsername())) {
            json.put("username", getUsername());
        } else {
            json.put("username", "");
        }
        if (StringUtils.isNotBlank(getEmail())) {
            json.put("email", getEmail());
        } else {
            json.put("email", "");
        }
        if (getRegisterTime() != null) {
            json.put("registerTime", DateUtils.parseDateToTimeStr(getRegisterTime()));
        } else {
            json.put("registerTime", "");
        }
        if (StringUtils.isNotBlank(getRegisterIp())) {
            json.put("registerIp", getRegisterIp());
        }
        if (getLastLoginTime() != null) {
            json.put("lastLoginTime", DateUtils.parseDateToTimeStr(getLastLoginTime()));
        } else {
            json.put("lastLoginTime", "");
        }
        if (StringUtils.isNotBlank(getLastLoginIp())) {
            json.put("lastLoginIp", getLastLoginIp());
        } else {
            json.put("lastLoginIp", "");
        }
        if (getLoginCount() != null) {
            json.put("loginCount", getLoginCount());
        } else {
            json.put("loginCount", "");
        }
        if (getGroup() != null && getGroup().getId() != null) {
            json.put("groupId", getGroup().getId());
        } else {
            json.put("groupId", "");
        }
        if (getGroup() != null && StringUtils.isNotBlank(getGroup().getName())) {
            json.put("groupName", getGroup().getName());
        } else {
            json.put("groupName", "");
        }
        if (getPoint() != null) {
            json.put("point", getPoint() + "");
        } else {
            json.put("point", "");
        }
        if (getPrestige() != null) {
            json.put("prestige", getPrestige());
        } else {
            json.put("prestige", "");
        }
        if (StringUtils.isNotBlank(getSigned())) {
            json.put("signed", getSigned());
        } else {
            json.put("signed", "");
        }
        if (getAdmin() != null) {
            json.put("admin", getAdmin());
        } else {
            json.put("admin", "");
        }
        if (getTopicCount() != null) {
            json.put("topicCount", getTopicCount());
        } else {
            json.put("topicCount", "");
        }
        if (getReplyCount() != null) {
            json.put("replyCount", getReplyCount());
        } else {
            json.put("replyCount", "");
        }
        if (getPostToday() != null) {
            json.put("postToday", getPostToday());
        } else {
            json.put("postToday", "");
        }
        json.put("attentionCount", getAttentionCount());
        json.put("fanCount", getFanCount());
        if (StringUtils.isNotBlank(getRealname())) {
            json.put("realname", getUserExt().getRealname());
        } else {
            json.put("realname", "");
        }
        if (getUserExt() != null && getUserExt().getGender() != null) {
            json.put("gender", getUserExt().getGender());
        } else {
            json.put("gender", "");
        }
        if (getUserExt() != null && getUserExt().getBirthday() != null) {
            json.put("birthday", DateUtils.parseDateToDateStr(getUserExt().getBirthday()));
        } else {
            json.put("birthday", "");
        }
        if (getUserExt() != null && StringUtils.isNotBlank(getUserExt().getIntro())) {
            json.put("intro", getUserExt().getIntro());
        } else {
            json.put("intro", "");
        }
        if (getUserExt() != null && StringUtils.isNotBlank(getUserExt().getComefrom())) {
            json.put("comefrom", getUserExt().getComefrom());
        } else {
            json.put("comefrom", "");
        }
        if (getUserExt() != null && StringUtils.isNotBlank(getUserExt().getQq())) {
            json.put("qq", getUserExt().getQq());
        } else {
            json.put("qq", "");
        }
        if (getUserExt() != null && StringUtils.isNotBlank(getUserExt().getMsn())) {
            json.put("msn", getUserExt().getMsn());
        } else {
            json.put("msn", "");
        }
        if (getUserExt() != null && StringUtils.isNotBlank(getUserExt().getPhone())) {
            json.put("phone", getUserExt().getPhone());
        } else {
            json.put("phone", "");
        }
        if (getUserExt() != null && StringUtils.isNotBlank(getUserExt().getMoble())) {
            json.put("mobile", getUserExt().getMoble());
        } else {
            json.put("mobile", "");
        }
        if (StringUtils.isNotBlank(getAvatar())) {
            json.put("userImg", getAvatar());
        } else {
            json.put("userImg", "");
        }
        Set<BbsThirdAccount> accounts = getThirdAccounts();
        if (accounts != null && accounts.size() > 0) {
            json.put("thirdAccount", true);
        } else {
            json.put("thirdAccount", false);
        }
        if (getDisabled() != null) {
            json.put("disabled", getDisabled());
        } else {
            json.put("disabled", "");
        }
        if (getRank() != null) {
            json.put("rank", getRank());
        } else {
            json.put("rank", "");
        }
        if (getRoles() != null) {
            Set<CmsRole> set = getRoles();
            String cmsRoleNames = "";
            for (CmsRole cmsRole : set) {
                if (StringUtils.isNotBlank(cmsRole.getName())) {
                    cmsRoleNames += cmsRole.getName() + ",";
                }
            }
            if (cmsRoleNames.length() > 0) {
                cmsRoleNames = cmsRoleNames.substring(0, cmsRoleNames.length() - 1);
            }
            json.put("roleNames", cmsRoleNames);
        } else {
            json.put("roleNames", "");
        }
        if (getRoleIds() != null) {
            Integer[] roleIdArray = getRoleIds();
            String roleIds = "";
            for (int i = 0; i < roleIdArray.length; i++) {
                roleIds += roleIdArray[i] + ",";
            }
            if (roleIds.length() > 0) {
                roleIds = roleIds.substring(0, roleIds.length() - 1);
            }
            json.put("roleIds", roleIds);
        } else {
            json.put("roleIds", "");
        }
        if (getReadOnly() != null) {
            json.put("readOnly", getReadOnly());
        } else {
            json.put("readOnly", "");
        }
        return json;
    }

    public boolean getProhibit() {
        if (getProhibitPost() == PROHIBIT_FOREVER) {
            return true;
        }
        if (getProhibitPost() == PROHIBIT_TEMPORARY) {
            Date time = new Date();
            if (time.before(getProhibitTime())) {
                return true;
            }
        }
        return false;
    }

    public Boolean getModerator() {
       return getGroup().getType().equals(BbsUserGroup.SYSTEM);
    }

    public String getRealname() {
        BbsUserExt ext = getUserExt();
        if (ext != null) {
            return ext.getRealname();
        } else {
            return null;
        }
    }

    public Boolean getGender() {
        BbsUserExt ext = getUserExt();
        if (ext != null) {
            return ext.getGender();
        } else {
            return null;
        }
    }

    public Date getBirthday() {
        BbsUserExt ext = getUserExt();
        if (ext != null) {
            return ext.getBirthday();
        } else {
            return null;
        }
    }

    public String getIntro() {
        BbsUserExt ext = getUserExt();
        if (ext != null) {
            return ext.getIntro();
        } else {
            return null;
        }
    }

    public String getComefrom() {
        BbsUserExt ext = getUserExt();
        if (ext != null) {
            return ext.getComefrom();
        } else {
            return null;
        }
    }

    public String getQq() {
        BbsUserExt ext = getUserExt();
        if (ext != null) {
            return ext.getQq();
        } else {
            return null;
        }
    }

    public String getMsn() {
        BbsUserExt ext = getUserExt();
        if (ext != null) {
            return ext.getMsn();
        } else {
            return null;
        }
    }

    public String getPhone() {
        BbsUserExt ext = getUserExt();
        if (ext != null) {
            return ext.getPhone();
        } else {
            return null;
        }
    }

    public String getMoble() {
        BbsUserExt ext = getUserExt();
        if (ext != null) {
            return ext.getMoble();
        } else {
            return null;
        }
    }

    public BbsSession getUserSession() {
        Set<BbsSession> set = getSessions();
        if (set != null && set.size() > 0) {
            return set.iterator().next();
        } else {
            return null;
        }
    }

    public String getAccountWeixin() {
        BbsUserAccount ext = getUserAccount();
        if (ext != null) {
            return ext.getAccountWeixin();
        } else {
            return null;
        }
    }

    public String getAccountWeixinOpenId() {
        BbsUserAccount ext = getUserAccount();
        if (ext != null) {
            return ext.getAccountWeixinOpenId();
        } else {
            return null;
        }
    }

    public String getAccountAlipy() {
        BbsUserAccount ext = getUserAccount();
        if (ext != null) {
            return ext.getAccountAlipy();
        } else {
            return null;
        }
    }

    public Short getDrawAccount() {
        BbsUserAccount ext = getUserAccount();
        if (ext != null) {
            return ext.getDrawAccount();
        } else {
            return 0;
        }
    }

    public Double getAdAccountMount() {
        BbsUserAccount ext = getUserAccount();
        if (ext != null) {
            return ext.getAdAccountMount();
        } else {
            return 0.0;
        }
    }

    public Double getLiveTotalAmount() {
        BbsLiveUserAccount ext = getLiveUserAccount();
        if (ext != null) {
            return ext.getTotalAmount();
        } else {
            return 0.0;
        }
    }

    public Double getLiveNoPayAmount() {
        BbsLiveUserAccount ext = getLiveUserAccount();
        if (ext != null) {
            return ext.getNoPayAmount();
        } else {
            return 0.0;
        }
    }

    public Integer getLiveDrawCount() {
        BbsLiveUserAccount ext = getLiveUserAccount();
        if (ext != null) {
            return ext.getDrawCount();
        } else {
            return 0;
        }
    }

    public Integer getLiveTicketNum() {
        BbsLiveUserAccount ext = getLiveUserAccount();
        if (ext != null) {
            return ext.getTicketNum();
        } else {
            return 0;
        }
    }

    public Integer getLiveGiftNum() {
        BbsLiveUserAccount ext = getLiveUserAccount();
        if (ext != null) {
            return ext.getGiftNum();
        } else {
            return 0;
        }
    }

    public Integer getLiveTopPriority() {
        BbsLiveUserAccount ext = getLiveUserAccount();
        if (ext != null) {
            return ext.getTopPriority();
        } else {
            return 0;
        }
    }

    public BbsLiveUserAccount getLiveUserAccount() {
        return liveUserAccount;
    }

    public void setLiveUserAccount(BbsLiveUserAccount liveUserAccount) {
        this.liveUserAccount = liveUserAccount;
    }

    public Date getUserLastActiveTime() {
        BbsSession userSession = getUserSession();
        if (userSession == null) {
            return null;
        } else {
            return userSession.getLastActivetime();
        }
    }

    public BbsLoginLog getUserLaestLoginLog() {
        Set<BbsLoginLog> set = getLoginLogs();
        if (set != null && set.size() > 0) {
            return set.iterator().next();
        } else {
            return null;
        }
    }

    public int getLaestOnLineMinute() {
        BbsLoginLog log = getUserLaestLoginLog();
        int timout;
        if (log == null) {
            timout = 0;
        } else {
            timout = (int) ((log.getLogoutTime().getTime() - log.getLoginTime().getTime()) / 1000 / 60);
        }
        return timout;
    }

    public Double getOnlineLatest() {
        if (getUserOnline() != null) {
            return splitDouble(getUserOnline().getOnlineLatest() / 60.0);
        } else {
            return 0d;
        }
    }

    public int getDayOnLineMinute() {
        Set<BbsLoginLog> logs = getLoginLogs();
        Iterator<BbsLoginLog> it = logs.iterator();
        BbsLoginLog log;
        int timout = 0;
        while (it.hasNext()) {
            log = it.next();
            if (DateUtils.isToday(log.getLoginTime())) {
                timout += (int) ((log.getLogoutTime().getTime() - log.getLoginTime().getTime()) / 1000 / 60);
            }
        }
        return timout;
    }

    public Double getOnlineDay() {
        if (getUserOnline() != null) {
            return splitDouble(getUserOnline().getOnlineDay() / 60.0);
        } else {
            return 0d;
        }
    }

    public int getWeekOnLineMinute() {
        Set<BbsLoginLog> logs = getLoginLogs();
        Iterator<BbsLoginLog> it = logs.iterator();
        BbsLoginLog log;
        int timout = 0;
        while (it.hasNext()) {
            log = it.next();
            if (DateUtils.isThisWeek(log.getLoginTime())) {
                timout += (int) ((log.getLogoutTime().getTime() - log.getLoginTime().getTime()) / 1000 / 60);
            }
        }
        return timout;
    }

    public Double getOnlineWeek() {
        if (getUserOnline() != null) {
            return splitDouble(getUserOnline().getOnlineWeek() / 60.0);
        } else {
            return 0d;
        }
    }

    public int getMonthOnLineMinute() {
        Set<BbsLoginLog> logs = getLoginLogs();
        Iterator<BbsLoginLog> it = logs.iterator();
        BbsLoginLog log;
        int timout = 0;
        while (it.hasNext()) {
            log = it.next();
            if (DateUtils.isThisMonth(log.getLoginTime())) {
                timout += (int) ((log.getLogoutTime().getTime() - log.getLoginTime().getTime()) / 1000 / 60);
            }
        }
        return timout;
    }

    public Double getOnlineMonth() {
        if (getUserOnline() != null) {
            return splitDouble(getUserOnline().getOnlineMonth() / 60.0);
        } else {
            return 0d;
        }
    }

    public int getYearOnLineMinute() {
        Set<BbsLoginLog> logs = getLoginLogs();
        Iterator<BbsLoginLog> it = logs.iterator();
        BbsLoginLog log;
        int timout = 0;
        while (it.hasNext()) {
            log = it.next();
            if (DateUtils.isThisYear(log.getLoginTime())) {
                timout += (int) ((log.getLogoutTime().getTime() - log.getLoginTime().getTime()) / 1000 / 60);
            }
        }
        return timout;
    }

    public Double getOnlineYear() {
        if (getUserOnline() != null) {
            return splitDouble(getUserOnline().getOnlineYear() / 60.0);
        } else {
            return 0d;
        }
    }

    private double splitDouble(double d) {
        return ((double) Math.round(d * 100)) / 100;
    }

    public Set<BbsPostType> getPostTypeByForum(BbsForum forum) {
        Set<BbsPostType> forumPostTypes = forum.getPostTypes();
        Iterator<BbsPostType> uit = getGroup().getPostTypes().iterator();
        Set<BbsPostType> result = new HashSet<BbsPostType>();
        BbsPostType type;
        while (uit.hasNext()) {
            type = uit.next();
            if (forumPostTypes.contains(type)) {
                result.add(type);
            }
        }
        return result;
    }

    public BbsMemberMagic getMemberMagic(String mid) {
        Set<BbsMemberMagic> magics = getMemberMagics();
        Iterator<BbsMemberMagic> it = magics.iterator();
        BbsMemberMagic magic;
        while (it.hasNext()) {
            magic = it.next();
            if (magic.getMagic().getIdentifier().equals(mid)) {
                return magic;
            }
        }
        return null;
    }

    public void addToMemberMagics(BbsMemberMagic magic) {
        Set<BbsMemberMagic> magics = getMemberMagics();
        if (magics == null) {
            magics = new HashSet<BbsMemberMagic>();
            setMemberMagics(magics);
        }
        magics.add(magic);
    }

    public void setMemberMagicNum(String mid, int num, int operator) {
        Set<BbsMemberMagic> magics = getMemberMagics();
        Iterator<BbsMemberMagic> it = magics.iterator();
        BbsMemberMagic magic;
        BbsCommonMagic commomMagic;
        while (it.hasNext()) {
            magic = it.next();
            commomMagic = magic.getMagic();
            if (commomMagic.getIdentifier().equals(mid)) {
                if (operator == 0) {
                    // 减少数量
                    commomMagic.setNum(commomMagic.getNum() - num);
                } else if (operator == 1) {
                    // 增加数量
                    commomMagic.setNum(commomMagic.getNum() + num);
                }
                break;
            }
        }
    }

    public void forMember(UnifiedUser u) {
        forUser(u);
        setAdmin(false);
    }

    public void forAdmin(UnifiedUser u, int rank) {
        forUser(u);
        setAdmin(true);
        setRank(rank);
    }

    public void forUser(UnifiedUser u) {
        setDisabled(false);
        setId(u.getId());
        setUsername(u.getUsername());
        setEmail(u.getEmail());
        setRegisterIp(u.getRegisterIp());
        setRegisterTime(u.getRegisterTime());
        setLastLoginIp(u.getLastLoginIp());
        setLastLoginTime(u.getLastLoginTime());
        setLoginCount(0);
    }

    public void init() {
        if (getUploadTotal() == null) {
            setUploadTotal(0L);
        }
        if (getUploadSize() == null) {
            setUploadSize(0);
        }
        if (getUploadDate() == null) {
            setUploadDate(new java.sql.Date(System.currentTimeMillis()));
        }
        if (getAdmin() == null) {
            setAdmin(false);
        }
        if (getProhibitPost() == null) {
            setProhibitPost(PROHIBIT_NO);
        }
        if (getTotalPoint() == null) {
            setTotalPoint(0l);
        }
        if (getDisabled() == null) {
            setDisabled(false);
        }
        if (getUploadToday() == null) {
            setUploadToday(0);
        }
        if (getPoint() == null) {
            setPoint(0L);
        }
        if (getPrestige() == null) {
            setPrestige(0L);
        }
        if (getMagicPacketSize() == null) {
            setMagicPacketSize(0);
        }
        if (getAvatarType() == null) {
            setAvatarType(AVATAR_LOCAL);
        }
        if (getTopicCount() == null) {
            setTopicCount(0);
        }
        if (getReplyCount() == null) {
            setReplyCount(0);
        }
        if (getPrimeCount() == null) {
            setPrimeCount(0);
        }
        if (getPostToday() == null) {
            setPostToday(0);
        }
        if (getOfficial() == null) {
            setOfficial(false);
        }
        if (getRank() == null) {
            setRank(0);
        }
        if (getLiveHost() == null) {
            setLiveHost(false);
        }
        if (getReadOnly() == null) {
            setReadOnly(false);
        }
    }

    public static Integer[] fetchIds(Collection<BbsUser> users) {
        if (users == null) {
            return null;
        }
        Integer[] ids = new Integer[users.size()];
        int i = 0;
        for (BbsUser u : users) {
            ids[i++] = u.getId();
        }
        return ids;
    }

    public String[] getSubscribeIds() {
        Set<BbsTopicTypeSubscribe> set = getSubscribes();
        return BbsTopicTypeSubscribe.fetchTypeIds(set);
    }

    public Integer getSubscribeCount() {
        Set<BbsTopicTypeSubscribe> set = getSubscribes();
        if (set != null && set.size() > 0) {
            return set.size();
        } else {
            return 0;
        }
    }

    public boolean getHasSubscribe() {
        Set<BbsTopicTypeSubscribe> set = getSubscribes();
        if (set != null && set.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public BbsTopicType getFirstSubscribeType() {
        Set<BbsTopicTypeSubscribe> set = getSubscribes();
        if (set != null && set.size() > 0) {
            return set.iterator().next().getType();
        } else {
            return null;
        }
    }

    public Integer getFirstSubscribeId() {
        String[] ids = getSubscribeIds();
        if (ids != null && ids.length > 0) {
            return Integer.parseInt(ids[0]);
        } else {
            return null;
        }
    }

    public String[] getCollectTopicIds() {
        Set<BbsTopicPostOperate> set = getTopicPostOperates();
        return getTopicIds(set, BbsTopicPostOperate.OPT_COLLECT);
    }

    public Integer getCollectTopicCount() {
        Set<BbsTopicPostOperate> set = getTopicPostOperates();
        if (set != null && set.size() > 0) {
            return set.size();
        } else {
            return 0;
        }
    }

    public String[] getAttentTopicIds() {
        Set<BbsTopicPostOperate> set = getTopicPostOperates();
        return getTopicIds(set, BbsTopicPostOperate.OPT_ATTENT);
    }

    public Integer getAttentTopicCount() {
        Set<BbsTopicPostOperate> set = getTopicPostOperates();
        if (set != null && set.size() > 0) {
            return set.size();
        } else {
            return 0;
        }
    }

    /**
     * 获取好友id
     * @author: andy_hulibo@163.com
     * @date: 2018/11/12 17:02
     */
    public Integer[] getMyFriendIds() {
        Set<BbsFriendShip> set = getMyFriends();
        return getFriendIds(set);
    }

    public Integer getMyFriendCount() {
        Set<BbsFriendShip> set = getMyFriends();
        if (set != null && set.size() > 0) {
            return set.size();
        } else {
            return 0;
        }
    }

    public String[] getUpTopicIds() {
        Set<BbsTopicPostOperate> set = getTopicPostOperates();
        return getTopicIds(set, BbsTopicPostOperate.OPT_UP);
    }

    public int getUpTopicCount() {
        Set<BbsTopicPostOperate> set = getTopicPostOperates();
        if (set != null) {
            return set.size();
        } else {
            return 0;
        }
    }

    public String[] getUpPostIds() {
        Set<BbsTopicPostOperate> set = getTopicPostOperates();
        return getPostIds(set, BbsTopicPostOperate.OPT_UP);
    }

    public Integer getUpPostCount() {
        Set<BbsTopicPostOperate> set = getTopicPostOperates();
        if (set != null) {
            return set.size();
        } else {
            return 0;
        }
    }

    public boolean hasCollectTopic(Integer topicId) {
        String[] topicIds = getCollectTopicIds();
        boolean hasCollect = false;
        for (String tid : topicIds) {
            if (tid.equals(topicId.toString())) {
                hasCollect = true;
                break;
            }
        }
        return hasCollect;
    }

    public boolean hasAttentTopic(Integer topicId) {
        String[] topicIds = getAttentTopicIds();
        boolean hasAttent = false;
        for (String tid : topicIds) {
            if (tid.equals(topicId.toString())) {
                hasAttent = true;
                break;
            }
        }
        return hasAttent;
    }

    public boolean hasUpTopic(Integer topicId) {
        String[] topicIds = getUpTopicIds();
        boolean hasUp = false;
        for (String tid : topicIds) {
            if (tid.equals(topicId.toString())) {
                hasUp = true;
                break;
            }
        }
        return hasUp;
    }

    public boolean hasUpPost(Integer postId) {
        String[] postIds = getUpPostIds();
        boolean hasUp = false;
        for (String tid : postIds) {
            if (tid.equals(postId.toString())) {
                hasUp = true;
                break;
            }
        }
        return hasUp;
    }

    public String[] getMyAttentionIds() {
        Set<BbsUser> set = getMyAttentions();
        return getUserIds(set);
    }

    public String[] getMyFanIds() {
        Set<BbsUser> set = getMyFans();
        return getUserIds(set);
    }

    public Integer getMyFanCount() {
        Set<BbsUser> set = getMyFans();
        if (set != null) {
            return set.size();
        } else {
            return 0;
        }
    }

    public int getAttentionCount() {
        Set<BbsUser> set = getMyAttentions();
        if (set != null) {
            return set.size();
        } else {
            return 0;
        }
    }

    public int getFanCount() {
        Set<BbsUser> set = getMyFans();
        if (set != null) {
            return set.size();
        } else {
            return 0;
        }
    }

    public int getGiftCount(Integer giftId) {
        Set<BbsGiftUser> set = getGifts();
        if (set != null) {
            for (BbsGiftUser g : set) {
                if (g.getGift().getId().equals(giftId)) {
                    return g.getNum();
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    private String[] getTopicIds(Set<BbsTopicPostOperate> set, Integer operate) {
        if (set == null) {
            return null;
        }
        List<BbsTopicPostOperate> idList = new ArrayList<>();
        int i = 0;
        for (BbsTopicPostOperate u : set) {
            if (u.getDataType().equals(BbsTopicPostOperate.DATA_TYPE_TOPIC) && operate.equals(u.getOperate())) {
                idList.add(u);
            }
        }
        String[] ids = new String[idList.size()];
        for (BbsTopicPostOperate u : idList) {
            ids[i++] = u.getDataId().toString();
        }
        return ids;
    }

    /*
     * 获取好友id数组
     */
    private Integer[] getFriendIds(Set<BbsFriendShip> set) {
        if (set == null) {
            return null;
        }
        List<BbsFriendShip> idList = new ArrayList<BbsFriendShip>();
        for (BbsFriendShip friendShip : set) {
            if (friendShip.getStatus().equals(BbsFriendShip.ACCEPT)) {
                idList.add(friendShip);
            }
        }
        int i = 0;
        Integer[] ids = new Integer[idList.size()];
        for (BbsFriendShip friendShip : idList) {
            ids[i++] = friendShip.getFriend().getId();
        }
        return ids;
    }

    private String[] getPostIds(Set<BbsTopicPostOperate> set, Integer operate) {
        if (set == null) {
            return null;
        }
        List<BbsTopicPostOperate> idList = new ArrayList<>();
        int i = 0;
        for (BbsTopicPostOperate u : set) {
            if (u.getDataType().equals(BbsTopicPostOperate.DATA_TYPE_POST) && operate.equals(u.getOperate())) {
                idList.add(u);
            }
        }
        String[] ids = new String[idList.size()];
        for (BbsTopicPostOperate u : idList) {
            ids[i++] = u.getDataId().toString();
        }
        return ids;
    }

    private String[] getUserIds(Set<BbsUser> set) {
        if (set == null) {
            return null;
        }
        int i = 0;
        String[] ids = new String[set.size()];
        for (BbsUser u : set) {
            ids[i++] = u.getId().toString();
        }
        return ids;
    }

    public Integer[] getRoleIds() {
        Set<CmsRole> roles = getRoles();
        return CmsRole.fetchIds(roles);
    }

    public CmsRole getTopRole() {
        Set<CmsRole> roles = getRoles();
        CmsRole topRole = null;
        for (CmsRole r : roles) {
            topRole = r;
            if (r.getLevel() > topRole.getLevel()) {
                topRole = r;
            }
        }
        return topRole;
    }

    public Integer getTopRoleLevel() {
        CmsRole topRole = getTopRole();
        if (topRole != null) {
            return topRole.getLevel();
        } else {
            return 0;
        }
    }

    /**
     * 获取用户余额
     *
     * @return
     */
    public Double getUserTotalAmount() {
        BbsUserAccount userAccount = getUserAccount();
        if (userAccount != null) {
            return userAccount.getTotalAmount();
        } else {
            return 0d;
        }
    }

    /**
     * 获取今日收入
     *
     * @return
     */
    public Double getUserDayAmount() {
        BbsUserAccount userAccount = getUserAccount();
        if (userAccount != null) {
            return userAccount.getDayAmount();
        } else {
            return 0d;
        }
    }

    /**
     * 获取本月收入
     *
     * @return
     */
    public Double getUserMonthAmount() {
        BbsUserAccount userAccount = getUserAccount();
        if (userAccount != null) {
            return userAccount.getMonthAmount();
        } else {
            return 0d;
        }
    }

    /**
     * 获取本年收入
     *
     * @return
     */
    public Double getUserYearAmount() {
        BbsUserAccount userAccount = getUserAccount();
        if (userAccount != null) {
            return userAccount.getYearAmount();
        } else {
            return 0d;
        }
    }

    /**
     * 用于排列顺序。此处优先级为0，则按ID升序排。
     */
    @Override
    public Number getPriority() {
        return 0;
    }

    /**
     * 是否是今天。根据System.currentTimeMillis() / 1000 / 60 / 60 / 24计算。
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        long day = date.getTime() / 1000 / 60 / 60 / 24;
        long currentDay = System.currentTimeMillis() / 1000 / 60 / 60 / 24;
        return day == currentDay;
    }

    public void addToRoles(CmsRole role) {
        if (role == null) {
            return;
        }
        Set<CmsRole> set = getRoles();
        if (set == null) {
            set = new HashSet<CmsRole>();
            setRoles(set);
        }
        set.add(role);
    }

    public boolean isSuper() {
        Set<CmsRole> roles = getRoles();
        if (roles == null) {
            return false;
        }
        for (CmsRole role : getRoles()) {
            if (role.getAll()) {
                return true;
            }
        }
        return false;
    }

    public Set<String> getPerms() {
        if (getDisabled()) {
            return null;
        }
        Set<CmsRole> roles = getRoles();
        if (roles == null) {
            return null;
        }
        boolean isSuper = false;
        Set<String> allPerms = new HashSet<>();
        for (CmsRole role : getRoles()) {
            if (role.getAll()) {
                isSuper = true;
                break;
            }
            allPerms.addAll(role.getPerms());
        }
        if (isSuper) {
            allPerms.clear();
            allPerms.add("*");
        }
        return allPerms;
    }

    public String getPermStr() {
        if (getDisabled()) {
            return "";
        }
        Set<CmsRole> roles = getRoles();
        if (roles == null) {
            return "";
        }
        boolean isSuper = false;
        StringBuffer permBuff = new StringBuffer();
        for (CmsRole role : getRoles()) {
            if (role.getAll()) {
                isSuper = true;
                break;
            }
            for (String s : role.getPerms()) {
                permBuff.append(s + ",");
            }
        }
        if (isSuper) {
            int sb_length = permBuff.length();
            permBuff.delete(0, sb_length);
            permBuff.append("*");
        }
        return permBuff.toString();
    }
}