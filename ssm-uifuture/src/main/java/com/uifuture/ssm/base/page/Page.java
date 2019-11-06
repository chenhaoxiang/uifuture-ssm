/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.base.page;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页实体对象，包含了详细的数据信息，包括分页相关信息。
 * 添加transform方法用来转换类型
 *
 * @author chenhx
 * @version Page.java, v 0.1 2019-09-20 14:29 chenhx
 */

public class Page<T> implements Serializable {
    private static final long serialVersionUID = -2303691927147928256L;
    public static String CURRENT_INDEX = "currentIndex";
    public static String PAGE_SIZE = "pageSize";
    public static String INDEX = "index";
    /**
     * 当前页
     */
    private int currentIndex;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private int totalNumber;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 下一页
     */
    private int nextIndex;

    /**
     * 上一页
     */
    private int preIndex;

    /**
     * 当前页的数据记录
     */
    private List<T> items = Collections.emptyList();


    /**
     * @param totalNumber
     * @param currentIndex
     * @param pageSize
     * @param items
     */
    public Page(int totalNumber, int currentIndex, int pageSize, List<T> items) {
        this.totalNumber = totalNumber;
        this.currentIndex = currentIndex;
        this.pageSize = pageSize;
        this.items = items;
    }

    public Page() {
        this.totalNumber = 0;
        this.currentIndex = 1;
        this.pageSize = 10;
    }

    /**
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * @param currentIndex
     */
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     * @return
     */
    public int getTotalNumber() {
        return totalNumber;
    }

    /**
     * @param totalNumber
     */
    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    /**
     * 总页数
     *
     * @return
     */
    public int getTotalPage() {

        int size = this.totalNumber / this.pageSize;
        if (this.totalNumber % this.pageSize != 0) {
            size = size + 1;
        }
        this.totalPage = size;

        return this.totalPage;
    }

    /**
     * 当前页的下一页，如果当前耶大于等于最后一页
     * 那么下一页就是最后一页。
     *
     * @return
     */
    public int getNextIndex() {

        if (this.currentIndex >= this.getTotalPage()) {
            this.nextIndex = this.currentIndex;
        } else {
            this.nextIndex = this.currentIndex + 1;
        }
        return nextIndex;
    }

    /**
     * 当前页的上一页，如果当前页小于第一页那么上一页为0
     *
     * @return
     */
    public int getPreIndex() {

        if (this.currentIndex <= 1) {
            this.preIndex = 0;
        } else {
            this.preIndex = this.currentIndex - 1;
        }

        return preIndex;
    }

    /**
     * @return
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * @param items
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    public String replaceUrl(String url, int page) {

        if (url != null && url.indexOf("?") == -1) {
            return url + "?" + INDEX + "=" + page;
        }
        if (url != null && url.indexOf("index=") == -1) {
            return url + "&" + INDEX + "=" + page;
        }
        return url == null ? "" : url.replaceAll("index=\\d{1,}", INDEX + "=" + page);
    }

    public String getPageHtml(String linkUrl) {

        int totalPage = getTotalPage();
        //确定totalPage不可能小于10
        StringBuffer str = new StringBuffer("");

        str.append("<div class=\"sort clearfix\">");

        if (currentIndex == 1) {
            str.append("<a  click_type=\"page-prev\" class=\"pre\">上一页</a>");
        } else {
            str.append("<a click_type=\"page-prev\" class=\"pre\" href=\"" + this.replaceUrl(linkUrl, (currentIndex - 1)) + "\">上一页</a>");
        }

        if (totalPage <= 7) {
            for (int i = 1; i <= totalPage; i++) {
                if (i == currentIndex) {
                    str.append("<a class=\"activeNumber\">" + i + "</a>");
                } else {
                    str.append("<a click_type=\"page-number-" + i + "\" href=\"").append(this.replaceUrl(linkUrl, i) + "\">" + i + "</a>");
                }
            }
        }
        if (totalPage > 7) {
            if (currentIndex < 6) {
                for (int i = 1; i <= 6; i++) {
                    if (i == currentIndex) {
                        str.append("<a click_type=\"page-number-" + i + "\" class=\"activeNumber\" href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a>");
                    } else {
                        str.append("<a click_type=\"page-number-" + i + "\" href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a>");
                    }
                }
                str.append("<span>...</span>");
                str.append("<a click_type=\"page-number-" + totalPage + "\" href=\"").append(this.replaceUrl(linkUrl, totalPage) + "\" >" + totalPage + "</a>");
            } else {
                int preDiff = currentIndex;
                int nextDiff = totalPage - currentIndex;
                if (preDiff <= 4) {
                    for (int i = 1; i < preDiff; i++) {
                        str.append("<a click_type=\"page-number-" + i + "\" href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a>");
                    }
                }
                if (preDiff > 4) {
                    str.append("<a click_type=\"page-number-1\" href=\"").append(this.replaceUrl(linkUrl, 1) + "\" >1</a>");
                    str.append("<span>...</span>");
                    for (int i = currentIndex - 2; i < currentIndex; i++) {
                        str.append("<a click_type=\"page-number-" + i + "\" href=\"").append(this.replaceUrl(linkUrl, i) + "\"").append(">" + i + "</a>");
                    }
                }
                str.append("<a class=\"activeNumber\">" + currentIndex + "</a>");

                if (nextDiff <= 3) {
                    for (int i = currentIndex + 1; i <= totalPage; i++) {
                        str.append("<a click_type=\"page-number-" + i + "\" href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a>");
                    }
                }
                if (nextDiff > 3) {
                    for (int i = currentIndex + 1; i <= currentIndex + 2; i++) {
                        str.append("<a click_type=\"page-number-" + i + "\" href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a>");
                    }
                    str.append("<span>...</span>");
                    str.append("<a click_type=\"page-number-" + totalPage + "\" href=\"").append(this.replaceUrl(linkUrl, totalPage) + "\" >" + totalPage + "</a>");
                }
            }

        }

        if (currentIndex == totalPage) {
            str.append("<a click_type=\"page-next\" class=\"next\">下一页</a>");
        } else {
            str.append("<a click_type=\"page-next\" class=\"next\" href=\"" + this.replaceUrl(linkUrl, (currentIndex + 1)) + "\">下一页</a>");
        }

        str.append("</div>");
        return str.toString();

    }

    public String getNewPageHtml(String linkUrl) {

        int totalPage = getTotalPage();
        StringBuffer str = new StringBuffer("");

        str.append("<div class=\"pagination\">");
        str.append("<ul>");
        if (currentIndex == 1) {
            str.append("<li><a>«</a></li>");
        } else {
            str.append("<li><a href=\"" + this.replaceUrl(linkUrl, (currentIndex - 1)) + "\">«</a></li>");
        }

        if (totalPage <= 7) {
            for (int i = 1; i <= totalPage; i++) {
                if (i == currentIndex) {
                    str.append("<li class=\"active\"><a>" + i + "</a></li>");
                } else {
                    str.append("<li><a href=\"").append(this.replaceUrl(linkUrl, i) + "\">" + i + "</a></li>");
                }
            }
        }
        if (totalPage > 7) {
            if (currentIndex < 6) {
                for (int i = 1; i <= 6; i++) {
                    if (i == currentIndex) {
                        str.append("<li class=\"active\"><a href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a></li>");
                    } else {
                        str.append("<li><a  href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a></li>");
                    }
                }
                str.append("<li><a>...</a></li>");
                str.append("<li><a href=\"").append(this.replaceUrl(linkUrl, totalPage) + "\" >" + totalPage + "</a></li>");
            } else {
                int preDiff = currentIndex;
                int nextDiff = totalPage - currentIndex;
                if (preDiff <= 4) {
                    for (int i = 1; i < preDiff; i++) {
                        str.append("<li><a href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a></li>");
                    }
                }
                if (preDiff > 4) {
                    str.append("<li><a href=\"").append(this.replaceUrl(linkUrl, 1) + "\" >1</a></li>");
                    str.append("<li><a>...</a></li>");
                    for (int i = currentIndex - 2; i < currentIndex; i++) {
                        str.append("<li><a href=\"").append(this.replaceUrl(linkUrl, i) + "\"").append(">" + i + "</a></li>");
                    }
                }
                str.append("<li class=\"active\"><a>" + currentIndex + "</a></li>");

                if (nextDiff <= 3) {
                    for (int i = currentIndex + 1; i <= totalPage; i++) {
                        str.append("<li><a href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a></li>");
                    }
                }
                if (nextDiff > 3) {
                    for (int i = currentIndex + 1; i <= currentIndex + 2; i++) {
                        str.append("<li><a href=\"").append(this.replaceUrl(linkUrl, i) + "\" >" + i + "</a></li>");
                    }
                    str.append("<li><a>...</a></li>");
                    str.append("<li><a  href=\"").append(this.replaceUrl(linkUrl, totalPage) + "\" >" + totalPage + "</a></li>");
                }
            }

        }

        if (currentIndex == totalPage) {
            str.append("<li><a >»</a></li>");
        } else {
            str.append("<li><a href=\"" + this.replaceUrl(linkUrl, (currentIndex + 1)) + "\">»</a></li>");
        }
        str.append("</ul>");
        str.append("</div>");
        return str.toString();
    }

    @SuppressWarnings("rawtypes")
    public Page<?> transform(Function<T, ?> function) {
        List<?> thatItem = Lists.transform(this.items, function);
        @SuppressWarnings({"unchecked"})
        Page<?> newPage = new Page(this.getTotalNumber(), this.getCurrentIndex(), this.getPageSize(), thatItem);
        return newPage;
    }
}
