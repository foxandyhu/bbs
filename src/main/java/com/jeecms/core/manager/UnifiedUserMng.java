package com.jeecms.core.manager;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.core.entity.UnifiedUser;

public interface UnifiedUserMng {
    /**
     * 忘记密码
     *
     * @param userId 用户ID
     * @param email  发送者邮件信息
     * @param tpl    邮件模板。内容模板可用变量${uid}、${username}、${resetKey}、${resetPwd}。
     * @return
     */
    UnifiedUser passwordForgotten(Integer userId, EmailSender email,
                                  MessageTemplate tpl);

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    UnifiedUser resetPassword(Integer userId);

    Integer errorRemaining(String username);

    UnifiedUser login(String username, String password, String ip)
            throws UsernameNotFoundException, BadCredentialsException;

    UnifiedUser applogin(String username, String password, String ip)
            throws UsernameNotFoundException, BadCredentialsException;

    UnifiedUser loginByCookie(String username, String password, String ip)
            throws UsernameNotFoundException, BadCredentialsException;

    boolean usernameExist(String username);

    boolean emailExist(String email);

    UnifiedUser getByUsername(String username);

    List<UnifiedUser> getByEmail(String email);

    Pagination getPage(int pageNo, int pageSize);

    UnifiedUser findById(Integer id);

    UnifiedUser save(String username, String email, String password,
                     String ip) throws UnsupportedEncodingException, MessagingException;

    UnifiedUser save(String username, String email, String password,
                     String ip, Boolean activation, String uuid);

    public String sendEmail(String username, String email,
                            Boolean activation, EmailSender sender,
                            MessageTemplate msgTpl) throws UnsupportedEncodingException, MessagingException;

    /**
     * 修改邮箱和密码
     *
     * @param id       用户ID
     * @param password 未加密密码。如果为null或空串则不修改。
     * @param email    电子邮箱。如果为空串则设置为null。
     * @return
     */
    public UnifiedUser update(Integer id, String password, String email);

    /**
     * 验证用户密码是否正确
     *
     * @param id       用户ID
     * @param password 未加密密码
     * @author: andy_hulibo@163.com
     * @date: 2018/11/12 16:08
     * @return true 正确 false错误
     */
    boolean isPasswordValid(Integer id, String password);

    UnifiedUser deleteById(Integer id);

    UnifiedUser[] deleteByIds(Integer[] ids);

    UnifiedUser active(String username, String activationCode);

    UnifiedUser activeLogin(UnifiedUser user, String ip);

    /**
     * 用户登录失败更新登录信息
     *
     * @param userId 用户ID
     * @param ip     登录IP
     * @Author: andy_hulibo@163.com
     * @CreateDate: 2018/11/12 15:39
     */
    void updateLoginError(Integer userId, String ip);

    /**
     * 用户登录成功更新登录信息
     *
     * @param userId 用户ID
     * @param ip     登录IP
     * @Author: andy_hulibo@163.com
     * @CreateDate: 2018/11/12 15:39
     */
    void updateLoginSuccess(Integer userId, String ip);
}