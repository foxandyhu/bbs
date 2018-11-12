package com.jeecms.core.manager.impl;

import com.jeecms.common.email.EmailSendTool;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.common.security.encoder.PwdEncoder;
import com.jeecms.core.dao.UnifiedUserDao;
import com.jeecms.core.entity.Config.ConfigLogin;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.UnifiedUserMng;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author: andy_hulibo@163.com
 * @date: 2018/11/12 15:50
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UnifiedUserMngImpl implements UnifiedUserMng {

    @Autowired
    private PwdEncoder pwdEncoder;
    @Autowired
    private UnifiedUserDao dao;
    @Autowired
    private ConfigMng configMng;

    /**
     * 超时时间
     * @author: andy_hulibo@163.com
     * @date: 2018/11/12 15:52
     */
    private static final int INTERVAL=30*1000;

    @Override
    public UnifiedUser passwordForgotten(Integer userId, EmailSender email,
                                         MessageTemplate tpl) {
        UnifiedUser user = findById(userId);
        String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
        user.setResetKey(uuid);
        String resetPwd = RandomStringUtils.randomNumeric(10);
        user.setResetPwd(resetPwd);
        senderEmail(user.getId(), user.getUsername(), user.getEmail(), user
                .getResetKey(), user.getResetPwd(), email, tpl);
        return user;
    }

    private void senderEmail(final Integer uid, final String username,
                             final String to, final String resetKey, final String resetPwd,
                             final EmailSender email, final MessageTemplate tpl) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(email.getHost());
        if (email.getPort() != null) {
            sender.setPort(email.getPort());
        }
        sender.setUsername(email.getUsername());
        sender.setPassword(email.getPassword());
        sender.send((MimeMessage mimeMessage) -> {
            MimeMessageHelper msg = new MimeMessageHelper(mimeMessage,
                    false, email.getEncoding());
            msg.setSubject(tpl.getForgotPasswordSubject());
            msg.setTo(to);
            msg.setFrom(email.getUsername(), email.getPersonal());
            String text = tpl.getForgotPasswordText();
            text = StringUtils.replace(text, "${uid}", String.valueOf(uid));
            text = StringUtils.replace(text, "${username}", username);
            text = StringUtils.replace(text, "${resetKey}", resetKey);
            text = StringUtils.replace(text, "${resetPwd}", resetPwd);
            msg.setText(text);
        });
    }

    private void senderEmail(final String username, final String to,
                             final String activationCode, final EmailSender email,
                             final MessageTemplate tpl) throws UnsupportedEncodingException, MessagingException {
        String text = tpl.getRegisterText();
        text = StringUtils.replace(text, "${username}", username);
        text = StringUtils.replace(text, "${activationCode}", activationCode);
        EmailSendTool sendEmail = new EmailSendTool(email.getHost(), email
                .getUsername(), email.getPassword(), to, tpl
                .getRegisterSubject(), text, email.getPersonal(), "", "");
        sendEmail.send();
    }

    @Override
    public UnifiedUser resetPassword(Integer userId) {
        UnifiedUser user = findById(userId);
        user.setPassword(pwdEncoder.encodePassword(user.getResetPwd()));
        user.setResetKey(null);
        user.setResetPwd(null);
        return user;
    }

    @Override
    public Integer errorRemaining(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        UnifiedUser user = getByUsername(username);
        if (user == null) {
            return null;
        }
        long now = System.currentTimeMillis();
        ConfigLogin configLogin = configMng.getConfigLogin();
        int maxErrorTimes = configLogin.getErrorTimes();
        int maxErrorInterval = configLogin.getErrorInterval() * 60 * 1000;
        Integer errorCount = user.getErrorCount();
        Date errorTime = user.getErrorTime();
        if (errorCount <= 0 || errorTime == null
                || errorTime.getTime() + maxErrorInterval < now) {
            return maxErrorTimes;
        }
        return maxErrorTimes - errorCount;
    }

    @Override
    public UnifiedUser login(String username, String password, String ip)
            throws UsernameNotFoundException, BadCredentialsException {
        UnifiedUser user = getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found: "
                    + username);
        }
        if (!pwdEncoder.isPasswordValid(user.getPassword(), password)) {
            throw new BadCredentialsException("password invalid");
        }
        if (!user.getActivation()) {
            throw new BadCredentialsException("account not activated");
        }
        updateLoginInfo(user.getId(), ip);
        return user;
    }

    @Override
    public UnifiedUser applogin(String username, String password, String ip)
            throws UsernameNotFoundException, BadCredentialsException {
        UnifiedUser user = getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found: " + username);
        }
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("password invalid");
        }
        if (!user.getActivation()) {
            throw new BadCredentialsException("account not activated");
        }
        updateLoginInfo(user.getId(), ip);
        return user;
    }

    /**
     * cookie中存放的password为密文
     */
    @Override
    public UnifiedUser loginByCookie(String username, String password, String ip)
            throws UsernameNotFoundException, BadCredentialsException {
        UnifiedUser user = getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found: "
                    + username);
        }
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("password invalid");
        }
        if (!user.getActivation()) {
            throw new BadCredentialsException("account not activated");
        }
        updateLoginInfo(user.getId(), ip);
        return user;
    }

    public void updateLoginInfo(Integer userId, String ip) {
        Date now = new Timestamp(System.currentTimeMillis());
        UnifiedUser user = findById(userId);

        user.setLoginCount(user.getLoginCount() + 1);
        user.setLastLoginIp(ip);
        user.setLastLoginTime(now);
    }

    @Override
    public boolean usernameExist(String username) {
        return getByUsername(username) != null;
    }

    @Override
    public boolean emailExist(String email) {
        return dao.countByEmail(email) > 0;
    }

    @Override
    public UnifiedUser getByUsername(String username) {
        return dao.getByUsername(username);
    }

    @Override
    public List<UnifiedUser> getByEmail(String email) {
        return dao.getByEmail(email);
    }

    @Transactional(readOnly = true,rollbackFor = Exception.class)
    @Override
    public Pagination getPage(int pageNo, int pageSize) {
        return dao.getPage(pageNo, pageSize);
    }

    @Transactional(readOnly = true,rollbackFor = Exception.class)
    @Override
    public UnifiedUser findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public UnifiedUser save(String username, String email, String password,
                            String ip) throws UnsupportedEncodingException, MessagingException {
        return save(username, email, password, ip, true, null);
    }

    @Override
    public UnifiedUser save(String username, String email, String password,
                            String ip, Boolean activation, String uuid) {
        Date now = new Timestamp(System.currentTimeMillis());
        UnifiedUser user = new UnifiedUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(pwdEncoder.encodePassword(password));
        user.setRegisterIp(ip);
        user.setRegisterTime(now);
        user.setLastLoginIp(ip);
        user.setLastLoginTime(now);
        user.setLoginCount(0);
        user.setActivation(activation);
        user.setErrorCount(0);
        dao.save(user);
        if (!activation) {
            user.setActivationCode(uuid);
        }
        return user;
    }

    @Override
    public String sendEmail(String username, String email,
                            Boolean activation, EmailSender sender,
                            MessageTemplate msgTpl) throws UnsupportedEncodingException, MessagingException {
        String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
        if (!activation) {
            senderEmail(username, email, uuid, sender, msgTpl);
        }
        return uuid;
    }

    /**
     * @see UnifiedUserMng#update(Integer, String, String)
     */
    @Override
    public UnifiedUser update(Integer id, String password, String email) {
        UnifiedUser user = findById(id);
        if (!StringUtils.isBlank(email)) {
            user.setEmail(email);
        } else {
            user.setEmail(null);
        }
        if (!StringUtils.isBlank(password)) {
            user.setPassword(pwdEncoder.encodePassword(password));
        }
        return user;
    }

    @Override
    public boolean isPasswordValid(Integer id, String password) {
        UnifiedUser user = findById(id);
        return pwdEncoder.isPasswordValid(user.getPassword(), password);
    }

    @Override
    public UnifiedUser deleteById(Integer id) {
        return dao.deleteById(id);
    }

    @Override
    public UnifiedUser[] deleteByIds(Integer[] ids) {
        UnifiedUser[] beans = new UnifiedUser[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            beans[i] = deleteById(ids[i]);
        }
        return beans;
    }

    @Override
    public UnifiedUser active(String username, String activationCode) {
        UnifiedUser bean = getByUsername(username);
        bean.setActivation(true);
        bean.setActivationCode(null);
        return bean;
    }

    @Override
    public UnifiedUser activeLogin(UnifiedUser user, String ip) {
        updateLoginSuccess(user.getId(), ip);
        return user;
    }

    @Override
    public void updateLoginSuccess(Integer userId, String ip) {
        UnifiedUser user = findById(userId);
        Date now = new Timestamp(System.currentTimeMillis());
        user.setLoginCount(user.getLoginCount() + 1);
        user.setLastLoginIp(ip);
        user.setLastLoginTime(now);

        user.setErrorCount(0);
        user.setErrorTime(null);
        user.setErrorIp(null);
    }

    @Override
    public void updateLoginError(Integer userId, String ip) {
        UnifiedUser user = findById(userId);
        Date now = new Timestamp(System.currentTimeMillis());
        ConfigLogin configLogin = configMng.getConfigLogin();
        int errorInterval = configLogin.getErrorInterval();
        Date errorTime = user.getErrorTime();

        user.setErrorIp(ip);
        if (errorTime == null || errorTime.getTime() + errorInterval * INTERVAL < now
                .getTime()) {
            user.setErrorTime(now);
            user.setErrorCount(1);
        } else {
            user.setErrorCount(user.getErrorCount() + 1);
        }
    }
}