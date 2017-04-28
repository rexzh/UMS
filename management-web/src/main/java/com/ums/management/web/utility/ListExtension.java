package com.ums.management.web.utility;

import java.util.Comparator;
import java.util.List;

/**
 * Created by zling on 2017-04-28.
 */
public class ListExtension {
    public static <T> boolean inclusion(List<T> list, List<T> sub, Comparator<T> c) {
        for (T item : sub) {
            boolean contain = false;
            for (T s : list) {
                if (c.compare(s, item) == 0) {
                    contain = true;
                    break;
                }
            }
            if (!contain)
                return false;
        }
        return true;
    }
}
