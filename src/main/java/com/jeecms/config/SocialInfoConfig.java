package com.jeecms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 第三方社交系统相关配置
 * @Author: andy_hulibo@163.com
 * @CreateDate: 2018/11/10 10:46
 */
@Component
@ConfigurationProperties
public class SocialInfoConfig {

    private Weixin weixin = new Weixin();
    private Tencent tencent = new Tencent();
    private Alipay alipay = new Alipay();

    public Weixin getWeixin() {
        return weixin;
    }

    public Tencent getTencent() {
        return tencent;
    }

    public Alipay getAlipay() {
        return alipay;
    }

    /**
     * @Description: 微信相关配置
     * @Author: andy_hulibo@163.com
     * @CreateDate: 2018/11/10 10:49
     */
    public class Weixin {
        private Address address = new Address();
        private Order order = new Order();
        private Auth auth = new Auth();
        private String jscode2sessionUrl;

        public String getJscode2sessionUrl() {
            return jscode2sessionUrl;
        }

        public void setJscode2sessionUrl(String jscode2sessionUrl) {
            this.jscode2sessionUrl = jscode2sessionUrl;
        }

        public Address getAddress() {
            return address;
        }

        public Order getOrder() {
            return order;
        }

        public Auth getAuth() {
            return auth;
        }

        class Address {
            private String token;
            private String users;
            private String send;
            private String upload;
            private String menu;
            private String uploadImg;
            private String uploadNews;
            private String addNews;
            private String addMaterial;
            private String sendAllMessage;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getUsers() {
                return users;
            }

            public void setUsers(String users) {
                this.users = users;
            }

            public String getSend() {
                return send;
            }

            public void setSend(String send) {
                this.send = send;
            }

            public String getUpload() {
                return upload;
            }

            public void setUpload(String upload) {
                this.upload = upload;
            }

            public String getMenu() {
                return menu;
            }

            public void setMenu(String menu) {
                this.menu = menu;
            }

            public String getUploadImg() {
                return uploadImg;
            }

            public void setUploadImg(String uploadImg) {
                this.uploadImg = uploadImg;
            }

            public String getUploadNews() {
                return uploadNews;
            }

            public void setUploadNews(String uploadNews) {
                this.uploadNews = uploadNews;
            }

            public String getAddNews() {
                return addNews;
            }

            public void setAddNews(String addNews) {
                this.addNews = addNews;
            }

            public String getAddMaterial() {
                return addMaterial;
            }

            public void setAddMaterial(String addMaterial) {
                this.addMaterial = addMaterial;
            }

            public String getSendAllMessage() {
                return sendAllMessage;
            }

            public void setSendAllMessage(String sendAllMessage) {
                this.sendAllMessage = sendAllMessage;
            }
        }

        public class Order {
            private String queryUrl;
            private String payUrl;

            public String getPayUrl() {
                return payUrl;
            }

            public void setPayUrl(String payUrl) {
                this.payUrl = payUrl;
            }

            public String getQueryUrl() {
                return queryUrl;
            }

            public void setQueryUrl(String queryUrl) {
                this.queryUrl = queryUrl;
            }
        }

       public class Auth {
            private String codeUrl;
            private String accessTokenUrl;
            private String refreshAccessTokenUrl;
            private String userInfoUrl;
            private String qrCodeUrl;
            private String transferUrl;

            public String getTransferUrl() {
                return transferUrl;
            }

            public void setTransferUrl(String transferUrl) {
                this.transferUrl = transferUrl;
            }

            public String getCodeUrl() {
                return codeUrl;
            }

            public void setCodeUrl(String codeUrl) {
                this.codeUrl = codeUrl;
            }

            public String getAccessTokenUrl() {
                return accessTokenUrl;
            }

            public void setAccessTokenUrl(String accessTokenUrl) {
                this.accessTokenUrl = accessTokenUrl;
            }

            public String getUserInfoUrl() {
                return userInfoUrl;
            }

            public void setUserInfoUrl(String userInfoUrl) {
                this.userInfoUrl = userInfoUrl;
            }

            public String getQrCodeUrl() {
                return qrCodeUrl;
            }

            public void setQrCodeUrl(String qrCodeUrl) {
                this.qrCodeUrl = qrCodeUrl;
            }

            public String getRefreshAccessTokenUrl() {
                return refreshAccessTokenUrl;
            }

            public void setRefreshAccessTokenUrl(String refreshAccessTokenUrl) {
                this.refreshAccessTokenUrl = refreshAccessTokenUrl;
            }
        }
    }

    /**
     * @Description: 腾讯相关配置
     * @Author: andy_hulibo@163.com
     * @CreateDate: 2018/11/10 10:49
     */
    public class Tencent {
        private VideoCloud videoCloud = new VideoCloud();

        public VideoCloud getVideoCloud() {
            return videoCloud;
        }

        public class VideoCloud {
            private String pushUrl;
            private String playUrl;

            public void setPushUrl(String pushUrl) {
                this.pushUrl = pushUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public String getPushUrl() {
                return pushUrl;
            }

            public String getPlayUrl() {
                return playUrl;
            }
        }
    }

    /**
     * @Description: 支付宝相关配置
     * @Author: andy_hulibo@163.com
     * @CreateDate: 2018/11/10 10:49
     */
    public class Alipay {
        private String openapiUrl;

        public String getOpenapiUrl() {
            return openapiUrl;
        }

        public void setOpenapiUrl(String openapiUrl) {
            this.openapiUrl = openapiUrl;
        }
    }
}
