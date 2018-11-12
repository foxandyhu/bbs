package com.jeecms.bbs.manager;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserExt;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.page.Pagination;

/**
 * BBS用户业务逻辑接口
 *
 * @author: andy_hulibo@163.com
 * @date: 2018/11/12 16:16
 */
public interface BbsUserMng {

    List<BbsUser> getList(String username, Integer groupId,
                          Boolean disabled, Boolean admin, Boolean official,
                          Integer first, Integer count);

    Pagination getPage(String username, String email, Integer groupId,
                       Boolean disabled, Boolean admin, Boolean official, Integer lastLoginDay,
                       Integer rank, Integer orderBy, int pageNo, int pageSize);

    /**
     * 查找我关注的人和关注我的人
     *
     * @param attent 0 我关注的  1关注我的
     * @return
     */
    Pagination getPageByAttent(Integer attent, Integer userId,
                               int pageNo, int pageSize);

    List<BbsUser> getListByAttent(Integer attent, Integer userId,
                                  Integer first, Integer count);

    List<BbsUser> getAdminList(Integer siteId, Boolean allChannel,
                               Boolean disabled, Integer rank);

    BbsUser findById(Integer id);

    BbsUser findByUsername(String username);

    BbsUser registerMember(String username, String email, Boolean official,
                           String password, String ip, Integer groupId, BbsUserExt userExt, Map<String, String> attr) throws UnsupportedEncodingException, MessagingException;

    BbsUser registerMember(String username, String email,
                           String password, String ip, Integer groupId, BbsUserExt userExt, Map<String, String> attr, Boolean activation, EmailSender sender, MessageTemplate msgTpl) throws UnsupportedEncodingException, MessagingException;

    /**
     * 更新用户登录信息：登录次数，最后登录时间,最后登录IP，SessionId等
     *
     * @param userId    用户ID
     * @param ip        登录ip
     * @param loginTime 登录时间
     * @param sessionId
     * @author: andy_hulibo@163.com
     * @date: 2018/11/12 16:17
     */
    void updateLoginInfo(Integer userId, String ip, Date loginTime, String sessionId);

    void updateUploadSize(Integer userId, Integer size);

    void updatePwdEmail(Integer id, String password, String email);

    void updateGroup(Integer id, Integer groupId);

    boolean isPasswordValid(Integer id, String password);

    BbsUser saveAdmin(String username, String email, String password,
                      String ip, int rank, Integer groupId, Integer[] roleIds,
                      BbsUserExt userExt) throws UnsupportedEncodingException, MessagingException;

    BbsUser updateAdmin(BbsUser bean, BbsUserExt ext, String password,
                        Integer groupId, Integer[] roleIds);

    BbsUser updateMember(Integer id, String email, String password,
                         Boolean isDisabled, String signed, String avatar, BbsUserExt ext, Map<String, String> attr,
                         Integer groupId);

    BbsUser updateMember(Integer id, String email, String password, String realname, Boolean gender, String tel);

    BbsUser deleteById(Integer id);

    BbsUser[] deleteByIds(Integer[] ids);

    boolean usernameNotExist(String username);

    boolean emailNotExist(String email);

    List<BbsUser> getSuggestMember(String username, Integer count);

    void updateActiveLevel();

    /**
     * @param userId   用户Id
     * @param point    积分
     * @param prestige 威望
     * @param mid      道具标识
     * @param num      道具数量
     * @param operator 0出售道具，=1使用道具 =2丢弃道具 =3购买道具=4系统赠送道具
     */
    void updatePoint(Integer userId, Integer point, Integer prestige, String mid, int num, int operator);

    int attentUser(Integer userId, Integer attentUserId, Integer operate);

    BbsUser update(BbsUser bean);

    BbsUser forbidUser(Integer siteId, BbsUser user, boolean disabled);

}