package com.jeecms.bbs.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 论坛帖子内容
 *
 * @author andy_hulibo@163.com
 * 2018年10月30日下午5:08:58
 */
@Entity
@Table(name = "bbs_post_text")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "beanCache")
public class BbsPostText implements Serializable {

    private static final long serialVersionUID = -6301034656965349193L;

    /**
     * 帖子标题
     */
    @Column(name = "POST_TITLE")
    private String title;

    /**
     * 帖子内容
     */
    @Column(name = "POST_CONTENT")
    private String content;

    @Id
    @OneToOne
    @JoinColumn(name = "POST_ID")
    private BbsPost post;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BbsPost getPost() {
        return post;
    }

    public void setPost(BbsPost post) {
        this.post = post;
    }
}