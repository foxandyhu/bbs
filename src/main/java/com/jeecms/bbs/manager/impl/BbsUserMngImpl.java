package com.jeecms.bbs.manager.impl;

import com.jeecms.bbs.dao.BbsUserDao;
import com.jeecms.bbs.entity.*;
import com.jeecms.bbs.manager.*;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.CmsConfigMng;
import com.jeecms.core.manager.CmsRoleMng;
import com.jeecms.core.manager.UnifiedUserMng;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: andy_hulibo@163.com
 * @date: 2018/11/12 16:37
 */
@Service("bbsUserMng")
@Transactional(rollbackFor = Exception.class)
public class BbsUserMngImpl implements BbsUserMng {
    private static final Logger log = LoggerFactory
            .getLogger(BbsUserMngImpl.class);

    @Override
    public List<BbsUser> getList(String username, Integer groupId,
                                 Boolean disabled, Boolean admin, Boolean official, Integer first, Integer count) {
        return dao.getList(username, groupId, disabled, admin, official, first, count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Pagination getPage(String username, String email, Integer groupId,
                              Boolean disabled, Boolean admin, Boolean official, Integer lastLoginDay,
                              Integer rank, Integer orderBy, int pageNo,
                              int pageSize) {
        return dao.getPage(username, email, groupId, disabled,
                admin, official, lastLoginDay,
                rank, orderBy, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Pagination getPageByAttent(Integer attent, Integer userId, int pageNo, int pageSize) {
        return dao.getPageByAttent(attent, userId, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<BbsUser> getListByAttent(Integer attent, Integer userId,
                                         Integer first, Integer count) {
        return dao.getListByAttent(attent, userId, first, count);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<BbsUser> getList(Integer count) {
        return dao.getList(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<BbsUser> getAdminList(Integer siteId, Boolean allChannel,
                                      Boolean disabled, Integer rank) {
        return dao.getAdminList(siteId, allChannel, disabled, rank);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public BbsUser findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public BbsUser findByUsername(String username) {
        BbsUser entity = null;
        if ((!StringUtils.isBlank(username))) {
            entity = dao.findByUsername(username);
        }
        return entity;
    }

    @Override
    public BbsUser registerMember(String username, String email, Boolean official,
                                  String password, String ip, Integer groupId, BbsUserExt userExt, Map<String, String> attr) throws UnsupportedEncodingException, MessagingException {
        UnifiedUser unifiedUser = unifiedUserMng.save(username, email,
                password, ip);
        BbsUser user = new BbsUser();
        user.forMember(unifiedUser);
        user.setAttr(attr);
        BbsUserGroup group;
        if (groupId != null) {
            group = bbsUserGroupMng.findById(groupId);
        } else {
            group = bbsUserGroupMng.getRegDef();
        }
        if (group == null) {
            throw new RuntimeException(
                    "register default member group not found!");
        }
        Integer defaultActiveLevel = cmsConfigMng.get().getDefaultActiveLevel();
        user.setActiveLevel(bbsUserActiveLevelMng.findById(defaultActiveLevel));
        if (official != null) {
            user.setOfficial(official);
        } else {
            user.setOfficial(false);
        }
        user.setGroup(group);
        user.init();
        user = dao.save(user);
        bbsUserExtMng.save(userExt, user);
        bbsUserOnlineMng.saveByUser(user);
        userAccountMng.updateAccountInfo(null, null, (short) 0, user);
        return user;
    }

    @Override
    public BbsUser registerMember(String username, String email,
                                  String password, String ip, Integer groupId, BbsUserExt userExt, Map<String, String> attr,
                                  Boolean activation, EmailSender sender, MessageTemplate msgTpl) throws UnsupportedEncodingException, MessagingException {
        String uuid;
        try {
            uuid = unifiedUserMng.sendEmail(username, email, activation, sender, msgTpl);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            throw e;
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw e;
        }
        BbsUser user = new BbsUser();
        UnifiedUser unifiedUser = unifiedUserMng.save(username, email,
                password, ip, activation, uuid);
        user.forMember(unifiedUser);
        user.setAttr(attr);
        BbsUserGroup group;
        if (groupId != null) {
            group = bbsUserGroupMng.findById(groupId);
        } else {
            group = bbsUserGroupMng.getRegDef();
        }
        if (group == null) {
            throw new RuntimeException(
                    "register default member group not found!");
        }
        Integer defaultActiveLevel = cmsConfigMng.get().getDefaultActiveLevel();
        user.setActiveLevel(bbsUserActiveLevelMng.findById(defaultActiveLevel));
        user.setGroup(group);
        user.init();
        user = dao.save(user);
        bbsUserExtMng.save(userExt, user);
        userAccountMng.updateAccountInfo(null, null, (short) 0, user);
        return user;
    }

    @Override
    public void updateLoginInfo(Integer userId, String ip, Date loginTime, String sessionId) {
        BbsUser user = findById(userId);
        if (user != null) {
            user.setLoginCount(user.getLoginCount() + 1);
            if (StringUtils.isNotBlank(ip)) {
                user.setLastLoginIp(ip);
            }
            if (loginTime != null) {
                user.setLastLoginTime(loginTime);
            }
            user.setSessionId(sessionId);
        }
    }

    @Override
    public void updateUploadSize(Integer userId, Integer size) {
        BbsUser user = findById(userId);
        user.setUploadTotal(user.getUploadTotal() + size);
        if (user.getUploadDate() != null) {
            if (BbsUser.isToday(user.getUploadDate())) {
                size += user.getUploadSize();
            }
        }
        user.setUploadDate(new java.sql.Date(System.currentTimeMillis()));
        user.setUploadSize(size);
    }

    @Override
    public boolean isPasswordValid(Integer id, String password) {
        return unifiedUserMng.isPasswordValid(id, password);
    }

    @Override
    public void updatePwdEmail(Integer id, String password, String email) {
        BbsUser user = findById(id);
        if (!StringUtils.isBlank(email)) {
            user.setEmail(email);
        } else {
            user.setEmail(null);
        }
        unifiedUserMng.update(id, password, email);
    }

    @Override
    public void updateGroup(Integer id, Integer groupId) {
        BbsUser user = findById(id);
        user.setGroup(bbsUserGroupMng.findById(groupId));
    }

    @Override
    public BbsUser saveAdmin(String username, String email, String password,
                             String ip, int rank, Integer groupId, Integer[] roleIds,
                             BbsUserExt userExt) throws UnsupportedEncodingException, MessagingException {
        UnifiedUser unifiedUser = unifiedUserMng.save(username, email,
                password, ip);
        BbsUser user = new BbsUser();
        user.forAdmin(unifiedUser, rank);
        BbsUserGroup group;
        if (groupId != null) {
            group = bbsUserGroupMng.findById(groupId);
        } else {
            group = bbsUserGroupMng.getRegDef();
        }
        if (group == null) {
            throw new RuntimeException(
                    "register default member group not setted!");
        }
        Integer defaultActiveLevel = cmsConfigMng.get().getDefaultActiveLevel();
        user.setActiveLevel(bbsUserActiveLevelMng.findById(defaultActiveLevel));
        user.setGroup(group);
        user.init();
        user = dao.save(user);
        if (roleIds != null) {
            for (Integer rid : roleIds) {
                user.addToRoles(roleMng.findById(rid));
            }
        }
        bbsUserExtMng.save(userExt, user);
        userAccountMng.updateAccountInfo(null, null, (short) 0, user);
        return user;
    }

    @Override
    public BbsUser updateAdmin(BbsUser bean, BbsUserExt ext, String password,
                               Integer groupId, Integer[] roleIds) {
        Updater<BbsUser> updater = new Updater<>(bean);
        updater.include("email");
        BbsUser user = dao.updateByUpdater(updater);
        user.setGroup(bbsUserGroupMng.findById(groupId));
        // 更新角色
        user.getRoles().clear();
        if (roleIds != null) {
            for (Integer rid : roleIds) {
                user.addToRoles(roleMng.findById(rid));
            }
        }
        bbsUserExtMng.update(ext, user);
        unifiedUserMng.update(bean.getId(), password, bean.getEmail());
        return user;
    }

    @Override
    public BbsUser updateMember(Integer id, String email, String password,
                                Boolean isDisabled, String signed, String avatar, BbsUserExt ext, Map<String, String> attr,
                                Integer groupId) {
        BbsUser entity = findById(id);
        if (!StringUtils.isBlank(email)) {
            entity.setEmail(email);
        }
        if (isDisabled != null) {
            entity.setDisabled(isDisabled);
        }
        if (signed != null) {
            entity.setSigned(signed);
        }
        if (avatar != null) {
            entity.setAvatar(avatar);
        }
        if (groupId != null) {
            entity.setGroup(bbsUserGroupMng.findById(groupId));
        }
        // 更新属性表
        if (attr != null) {
            Map<String, String> attrOrig = entity.getAttr();
            attrOrig.clear();
            attrOrig.putAll(attr);
        }
        bbsUserExtMng.update(ext, entity);
        unifiedUserMng.update(id, password, email);
        return entity;
    }

    @Override
    public BbsUser updateMember(Integer id, String email, String password, String realname, Boolean gender, String tel) {
        BbsUser entity = findById(id);
        if (!StringUtils.isBlank(email)) {
            entity.setEmail(email);
        }
        BbsUserExt ext = entity.getUserExt();
        ext.setRealname(realname);
        ext.setGender(gender);
        ext.setMoble(tel);
        bbsUserExtMng.update(ext, entity);
        unifiedUserMng.update(id, password, email);
        return entity;
    }

    @Override
    public BbsUser deleteById(Integer id) {
        unifiedUserMng.deleteById(id);
        return dao.deleteById(id);
    }

    @Override
    public BbsUser[] deleteByIds(Integer[] ids) {
        BbsUser[] beans = new BbsUser[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            beans[i] = deleteById(ids[i]);
        }
        return beans;
    }

    @Override
    public boolean usernameNotExist(String username) {
        return dao.countByUsername(username) <= 0;
    }

    @Override
    public boolean emailNotExist(String email) {
        return dao.countByEmail(email) <= 0;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<BbsUser> getSuggestMember(String username, Integer count) {
        return dao.getSuggestMember(username, count);
    }

    @Override
    public void updateActiveLevel() {
        List<BbsUserActiveLevel> levels = bbsUserActiveLevelMng.getList(Integer.MAX_VALUE);
        List<BbsUser> users = getList(Integer.MAX_VALUE);
        for (BbsUser u : users) {
            BbsUserActiveLevel l = getUpdateToLevel(u, levels);
            dao.updateActiveLevel(u, l);
        }
    }

    private BbsUserActiveLevel getUpdateToLevel(BbsUser user, List<BbsUserActiveLevel> levels) {
        if (levels != null && levels.size() > 0) {
            BbsUserActiveLevel level = levels.get(0);
            for (BbsUserActiveLevel l : levels) {
                BbsUserOnline online = user.getUserOnline();
                if (online != null) {
                    if (online.getOnlineTotal() > l.getRequiredHour()) {
                        level = l;
                        continue;
                    } else {
                        break;
                    }
                }
            }
            return level;
        } else {
            return null;
        }
    }

    @Override
    public void updatePoint(Integer userId, Integer point, Integer prestige,
                            String mid, int num, int operator) {
        BbsUser user = findById(userId);
        if (point != null) {
            user.setPoint(user.getPoint() + point);
        }
        if (prestige != null) {
            user.setPrestige(user.getPrestige() + prestige);
        }
        // operator=-1无须下面操作
        if (StringUtils.isNotBlank(mid) && operator != -1) {
            BbsMemberMagic magic;
            BbsCommonMagic comMagic = magicMng.findByIdentifier(mid);
            magic = user.getMemberMagic(mid);
            // operator==0出售道具，=1使用道具 =2丢弃道具 =3购买道具=4系统赠送道具
            if (operator == 0) {
                // 用户存在该道具---减少数量
                if (magic != null) {
                    magic.setNum(magic.getNum() - num);
                    // 减少包容量
                    //user.setMagicPacketSize(user.getMagicPacketSize() - num* comMagic.getWeight());
                    // 增加系统包数量
                    //comMagic.setNum(num + comMagic.getNum());
                    magicMng.update(comMagic);
                }
            } else if (operator == 1) {
                // 减少数量
                if (magic != null) {
                    magic.setNum(magic.getNum() - num);
                    // 减少包容量
                    //user.setMagicPacketSize(user.getMagicPacketSize() - num* comMagic.getWeight());
                }
            } else if (operator == 2) {
                // 减少数量
                if (magic != null) {
                    magic.setNum(magic.getNum() - num);
                    // 减少包容量
                    //user.setMagicPacketSize(user.getMagicPacketSize() - num* comMagic.getWeight());
                }
            } else if (operator == 3) {
                // 增加数量
                if (magic != null) {
                    magic.setNum(magic.getNum() + num);
                    // 增加包容量
                    //user.setMagicPacketSize(user.getMagicPacketSize() + num* comMagic.getWeight());
                    // 减少系统包数量
                    //comMagic.setNum(comMagic.getNum() - num);
                    magicMng.update(comMagic);
                } else {
                    magic = new BbsMemberMagic();
                    magic.setMagic(comMagic);
                    magic.setNum(num);
                    magic.setUser(user);
                    memberMagicMng.save(magic);
                    user.addToMemberMagics(magic);
                }
            } else if (operator == 4) {
                // 增加数量
                if (magic != null) {
                    magic.setNum(magic.getNum() + num);
                    // 增加包容量
                    magicMng.update(comMagic);
                } else {
                    magic = new BbsMemberMagic();
                    magic.setMagic(comMagic);
                    magic.setNum(num);
                    magic.setUser(user);
                    memberMagicMng.save(magic);
                    user.addToMemberMagics(magic);
                }
            }

        }
    }

    @Override
    public int attentUser(Integer userId, Integer attentUserId, Integer operate) {
        int status = -1;
        BbsUser user = findById(userId);
        BbsUser attentUser = findById(attentUserId);
        if (attentUser != null && user != null) {
            if (user.getMyAttentions().contains(attentUser)) {
                status = 1;
                if (operate != null && operate == 1) {
                    user.getMyAttentions().remove(attentUser);
                }
            } else {
                status = 0;
                if (operate != null && operate == 0) {
                    user.getMyAttentions().add(attentUser);
                }
            }
        }
        return status;
    }

    @Override
    public BbsUser update(BbsUser bean) {
        Updater<BbsUser> updater = new Updater<>(bean);
        bean = dao.updateByUpdater(updater);
        return bean;
    }

    @Override
    public BbsUser forbidUser(Integer siteId, BbsUser user, boolean disabled) {
        List<BbsPost> posts = postMng.getListForTag(siteId,
                null, null, user.getId(), null, BbsPost.OPT_ALL, 0, 0, Integer.MAX_VALUE);
        if (disabled) {
            for (BbsPost p : posts) {
                p.setStatus(BbsPost.SHIELD);
                postMng.update(p);
            }
        } else {
            for (BbsPost p : posts) {
                p.setStatus(BbsPost.NORMAL);
                postMng.update(p);
            }
        }
        user.setDisabled(disabled);
        return update(user);
    }

    @Autowired
    private BbsUserGroupMng bbsUserGroupMng;
    @Autowired
    private UnifiedUserMng unifiedUserMng;
    @Autowired
    private BbsUserExtMng bbsUserExtMng;
    @Autowired
    private BbsUserDao dao;
    @Autowired
    private BbsCommonMagicMng magicMng;
    @Autowired
    private BbsMemberMagicMng memberMagicMng;
    @Autowired
    private BbsUserOnlineMng bbsUserOnlineMng;
    @Autowired
    private CmsConfigMng cmsConfigMng;
    @Autowired
    private BbsUserActiveLevelMng bbsUserActiveLevelMng;
    @Autowired
    private CmsRoleMng roleMng;
    @Autowired
    private BbsPostMng postMng;
    @Autowired
    private BbsUserAccountMng userAccountMng;
}