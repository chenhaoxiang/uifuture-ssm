package com.uifuture.chapter12.entity;

import java.util.List;


public class BlogExt extends Blog {
    private List<Post> posts;

    /**
     * Getter method for property <tt>posts</tt>.
     *
     * @return property value of posts
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Setter method for property <tt>posts</tt>.
     *
     * @param posts value to be assigned to property posts
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlogExt{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("posts=").append(posts);
        sb.append('}');
        return sb.toString();
    }
}