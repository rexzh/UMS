package com.ums.management.web.utility;

/**
 * Created by zling on 2017-04-11.
 */
public class PageExtension {
    public static Integer calcStart(Integer page, Integer rows) {
        if(page != null) {
            if(rows == null)
                rows = 10;
            return (page - 1) * rows;
        }
        else {
            return null;
        }
    }

    public static Long calcStart(Long page, Integer rows) {
        if(page != null) {
            if(rows == null)
                rows = 10;
            return (page - 1) * rows;
        }
        else {
            return null;
        }
    }
}
