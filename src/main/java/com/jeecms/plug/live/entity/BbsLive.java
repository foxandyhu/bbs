package com.jeecms.plug.live.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.common.util.DateUtils;
import com.jeecms.core.entity.CmsSite;

/**
 * 活动live
 * 
 * @author andy_hulibo@163.com 2018年11月1日下午3:50:10
 */
@Entity
@Table(name = "bbs_live")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BbsLive implements Serializable {

	/**
	 * @author andy_hulibo@163.com 2018年10月26日下午4:40:51
	 */
	private static final long serialVersionUID = 4170838610723053146L;

	public static final Short CHECKING = 0;
	public static final Short CHECKED = 1;
	public static final Short REJECT = 2;
	public static final Short STOP = 3;

	public static final Short LIVE_PLAT_BAIDU = 1;
	public static final Short LIVE_PLAT_TENCENT = 2;

	public static final String LIVE_PLAT_STR_BAIDU = "baidu";
	public static final String LIVE_PLAT_STR_TENCENT = "tencent";

	@Id
	@Column(name="live_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="live_logo")
	private String liveLogo;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="begin_time")
	private Date beginTime;
	
	@Column(name="end_time")
	private Date endTime;
	
	@Column(name="begin_price")
	private Double beginPrice;
	
	@Column(name="after_price")
	private Double afterPrice;
	
	@Column(name="limit_user_num")
	private Integer limitUserNum;
	
	@Column(name="commission_rate")
	private Double commissionRate;
	
	@Column(name="check_status")
	private Short checkStatus;
	
	@Column(name="join_user_num")
	private Integer joinUserNum;
	
	@Column(name="inlive_user_num")
	private Integer inliveUserNum;
	
	@Column(name="live_url")
	private String liveUrl;
	
	@Column(name="demand_url")
	private String demandUrl;
	
	@Column(name="demand_image_url")
	private String demandImageUrl;
	
	@Column(name="live_mobile_url")
	private String liveMobileUrl;
	
	@Column(name="live_plat_key")
	private String livePlatKey;
	
	@Column(name="live_plat")
	private Short livePlat;
	
	@Column(name="reject_reason")
	private String reason;
	
	@Column(name="total_amount")
	private Double totalAmount;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private BbsUser user;
	
	@ManyToOne
	@JoinColumn(name="chapter_id")
	private BbsLiveChapter chapter;
	
	@ManyToOne
	@JoinColumn(name="site_id")
	private CmsSite site;

	@OneToMany(mappedBy="live",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<BbsLiveMessage> messages;
	
	@OneToMany(mappedBy="live",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<BbsLiveUser> joinUsers;
	
	@OneToMany(mappedBy="live",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE,orphanRemoval=true)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<BbsLiveCharge> chargeSet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLiveLogo() {
		return liveLogo;
	}

	public void setLiveLogo(String liveLogo) {
		this.liveLogo = liveLogo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getBeginPrice() {
		return beginPrice;
	}

	public void setBeginPrice(Double beginPrice) {
		this.beginPrice = beginPrice;
	}

	public Double getAfterPrice() {
		return afterPrice;
	}

	public void setAfterPrice(Double afterPrice) {
		this.afterPrice = afterPrice;
	}

	public Integer getLimitUserNum() {
		return limitUserNum;
	}

	public void setLimitUserNum(Integer limitUserNum) {
		this.limitUserNum = limitUserNum;
	}

	public Double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Short getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Short checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getJoinUserNum() {
		return joinUserNum;
	}

	public void setJoinUserNum(Integer joinUserNum) {
		this.joinUserNum = joinUserNum;
	}

	public Integer getInliveUserNum() {
		return inliveUserNum;
	}

	public void setInliveUserNum(Integer inliveUserNum) {
		this.inliveUserNum = inliveUserNum;
	}

	public String getLiveUrl() {
		return liveUrl;
	}

	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}

	public String getDemandUrl() {
		return demandUrl;
	}

	public void setDemandUrl(String demandUrl) {
		this.demandUrl = demandUrl;
	}

	public String getDemandImageUrl() {
		return demandImageUrl;
	}

	public void setDemandImageUrl(String demandImageUrl) {
		this.demandImageUrl = demandImageUrl;
	}

	public String getLiveMobileUrl() {
		return liveMobileUrl;
	}

	public void setLiveMobileUrl(String liveMobileUrl) {
		this.liveMobileUrl = liveMobileUrl;
	}

	public String getLivePlatKey() {
		return livePlatKey;
	}

	public void setLivePlatKey(String livePlatKey) {
		this.livePlatKey = livePlatKey;
	}

	public Short getLivePlat() {
		return livePlat;
	}

	public void setLivePlat(Short livePlat) {
		this.livePlat = livePlat;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BbsUser getUser() {
		return user;
	}

	public void setUser(BbsUser user) {
		this.user = user;
	}

	public BbsLiveChapter getChapter() {
		return chapter;
	}

	public void setChapter(BbsLiveChapter chapter) {
		this.chapter = chapter;
	}

	public CmsSite getSite() {
		return site;
	}

	public void setSite(CmsSite site) {
		this.site = site;
	}

	public Set<BbsLiveMessage> getMessages() {
		return messages;
	}

	public void setMessages(Set<BbsLiveMessage> messages) {
		this.messages = messages;
	}

	public Set<BbsLiveUser> getJoinUsers() {
		return joinUsers;
	}

	public void setJoinUsers(Set<BbsLiveUser> joinUsers) {
		this.joinUsers = joinUsers;
	}

	public Set<BbsLiveCharge> getChargeSet() {
		return chargeSet;
	}

	public void setChargeSet(Set<BbsLiveCharge> chargeSet) {
		this.chargeSet = chargeSet;
	}

	public String getUrlWhole() {
		return getSite().getUrlBuffer(true, true, false).append("/live/front/get.jspx?id=").append(getId()).toString();
	}

	public boolean isCharge() {
		boolean charge = false;
		if (getBeginPrice() != null && getBeginPrice() > 0) {
			charge = true;
		}
		if (getAfterPrice() != null && getAfterPrice() > 0) {
			charge = true;
		}
		return charge;
	}

	public boolean isUnlimited() {
		Integer limitUserNum = getLimitUserNum();
		if (limitUserNum != null && limitUserNum.equals(0)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasEnoughUserNum() {
		Integer limitUserNum = getLimitUserNum();
		Integer joinUserNum = getJoinUserNum();
		if (limitUserNum != null) {
			if (limitUserNum.equals(0)) {
				return true;
			}
			if (limitUserNum > joinUserNum) {
				return true;
			}
		}
		return false;
	}

	public String[] getJoinUserIds() {
		Set<BbsLiveUser> joinUsers = getJoinUsers();
		String[] ids = new String[joinUsers.size()];
		int i = 0;
		for (BbsLiveUser c : joinUsers) {
			ids[i++] = c.getJoinUser().getId().toString();
		}
		return ids;
	}

	public BbsLiveCharge getLiveCharge() {
		Set<BbsLiveCharge> set = getChargeSet();
		if (set != null && set.size() > 0) {
			return set.iterator().next();
		} else {
			return null;
		}
	}

	public Double getTotalAmount() {
		BbsLiveCharge charge = getLiveCharge();
		if (charge != null) {
			return charge.getTotalAmount();
		} else {
			return 0d;
		}
	}

	public Double getYearAmount() {
		BbsLiveCharge charge = getLiveCharge();
		if (charge != null) {
			return charge.getYearAmount();
		} else {
			return 0d;
		}
	}

	public Double getMonthAmount() {
		BbsLiveCharge charge = getLiveCharge();
		if (charge != null) {
			return charge.getMonthAmount();
		} else {
			return 0d;
		}
	}

	public Double getDayAmount() {
		BbsLiveCharge charge = getLiveCharge();
		if (charge != null) {
			return charge.getDayAmount();
		} else {
			return 0d;
		}
	}

	public Integer getTicketNum() {
		BbsLiveCharge charge = getLiveCharge();
		if (charge != null) {
			return charge.getTicketNum();
		} else {
			return 0;
		}
	}

	public Integer getGiftNum() {
		BbsLiveCharge charge = getLiveCharge();
		if (charge != null) {
			return charge.getGiftNum();
		} else {
			return 0;
		}
	}

	public Date getLastBuyTime() {
		BbsLiveCharge charge = getLiveCharge();
		if (charge != null) {
			return charge.getLastBuyTime();
		} else {
			return null;
		}
	}

	public boolean getHasOver() {
		Date endTime = getEndTime();
		Date now = Calendar.getInstance().getTime();
		if (endTime == null) {
			return false;
		} else {
			if (endTime.before(now)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean getHasBegin() {
		Date beginTime = getBeginTime();
		Date now = Calendar.getInstance().getTime();
		if (beginTime == null) {
			return false;
		} else {
			if (beginTime.before(now)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void init() {
		if (getTotalAmount() == null) {
			setTotalAmount(0d);
		}
		if (getJoinUserNum() == null) {
			setJoinUserNum(0);
		}
		if (getCommissionRate() == null) {
			setCommissionRate(0d);
		}
		if (getAfterPrice() == null) {
			setAfterPrice(0d);
		}
		if (getBeginPrice() == null) {
			setBeginPrice(0d);
		}
		if (getLimitUserNum() == null) {
			setLimitUserNum(0);
		}
		if (getInliveUserNum() == null) {
			setInliveUserNum(0);
		}
	}

	public JSONObject convertToJson(Short qStatus) throws JSONException {
		JSONObject json = new JSONObject();
		if (getId() != null) {
			json.put("id", getId());
		} else {
			json.put("id", "");
		}
		if (StringUtils.isNotBlank(getTitle())) {
			json.put("title", getTitle());
		} else {
			json.put("title", "");
		}
		if (getBeginTime() != null) {
			json.put("beginTime", DateUtils.parseDateToDateStr(getBeginTime()));
		} else {
			json.put("beginTime", "");
		}
		if (getUser() != null && StringUtils.isNotBlank(getUser().getRealname())) {
			json.put("realname", getUser().getRealname());
		} else {
			json.put("realname", "");
		}
		if (getUser() != null && StringUtils.isNotBlank(getUser().getUsername())) {
			json.put("username", getUser().getUsername());
		} else {
			json.put("username", "");
		}
		if (getBeginPrice() != null) {
			json.put("beginPrice", getBeginPrice());
		} else {
			json.put("beginPrice", "");
		}
		if (qStatus != null) {
			if (qStatus == 1) {
				if (getLimitUserNum() != null) {
					json.put("limitUserNum", getLimitUserNum());
				} else {
					json.put("limitUserNum", "");
				}
			} else if (qStatus == 2) {
				if (getJoinUserNum() != null) {
					json.put("joinUserNum", getJoinUserNum());
				} else {
					json.put("joinUserNum", "");
				}
				if (getInliveUserNum() != null) {
					json.put("inliveUserNum", getInliveUserNum());
				} else {
					json.put("inliveUserNum", "");
				}
				if (getGiftNum() != null) {
					json.put("giftNum", getGiftNum());
				} else {
					json.put("giftNum", "");
				}
			} else if (qStatus == 3) {
				if (getLimitUserNum() != null) {
					json.put("limitUserNum", getLimitUserNum());
				} else {
					json.put("limitUserNum", "");
				}
				if (getEndTime() != null) {
					json.put("endTime", DateUtils.parseDateToDateStr(getEndTime()));
				} else {
					json.put("endTime", "");
				}
				if (getGiftNum() != null) {
					json.put("giftNum", getGiftNum());
				} else {
					json.put("giftNum", "");
				}
			} else if (qStatus == 4) {
				if (getCheckStatus() != null) {
					json.put("checkStatus", getCheckStatus());
				} else {
					json.put("checkStatus", "");
				}
			} else if (qStatus == 5) {
				if (getJoinUserNum() != null) {
					json.put("joinUserNum", getJoinUserNum());
				} else {
					json.put("joinUserNum", 0);
				}
				if (getInliveUserNum() != null) {
					json.put("inliveUserNum", getInliveUserNum());
				} else {
					json.put("inliveUserNum", 0);
				}
				if (getGiftNum() != null) {
					json.put("giftNum", getGiftNum());
				} else {
					json.put("giftNum", 0);
				}
			} else {
				if (getCheckStatus() != null) {
					json.put("checkStatus", getCheckStatus());
				} else {
					json.put("checkStatus", "");
				}
				if (StringUtils.isNotBlank(getDescription())) {
					json.put("description", getDescription());
				} else {
					json.put("description", "");
				}
				if (StringUtils.isNotBlank(getLiveLogo())) {
					json.put("liveLogo", getLiveLogo());
				} else {
					json.put("liveLogo", "");
				}
				if (getEndTime() != null) {
					json.put("endTime", DateUtils.parseDateToDateStr(getEndTime()));
				} else {
					json.put("endTime", "");
				}
			}
		} else {
			if (getCheckStatus() != null) {
				json.put("checkStatus", getCheckStatus());
			} else {
				json.put("checkStatus", "");
			}
			if (StringUtils.isNotBlank(getDescription())) {
				json.put("description", getDescription());
			} else {
				json.put("description", "");
			}
			if (StringUtils.isNotBlank(getLiveLogo())) {
				json.put("liveLogo", getLiveLogo());
			} else {
				json.put("liveLogo", "");
			}
			if (getEndTime() != null) {
				json.put("endTime", DateUtils.parseDateToDateStr(getEndTime()));
			} else {
				json.put("endTime", "");
			}
		}
		return json;
	}
}