package com.uifuture.chapter12.entity;

import com.uifuture.chapter12.base.BaseEntity;

/**
 * 文章
 * table name: post
 *
 * @author chenhaoxiang 2018-10-17
 */
public class Post extends BaseEntity {
    /**
     * fields name: post.subject
     */
    private String subject;

    /**
     * fields name: post.body
     */
    private String body;

    /**
     * fields name: post.blog_id
     */
    private Integer blogId;

    /**
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * @return body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    /**
     * @return blog_id
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * @param blogId
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("subject='").append(subject).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", blogId=").append(blogId);
        sb.append('}');
        return sb.toString();
    }
}