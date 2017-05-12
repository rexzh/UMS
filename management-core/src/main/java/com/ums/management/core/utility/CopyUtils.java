package com.ums.management.core.utility;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CopyUtils {
    public static <T> T copyBean(Object source, Class<T> destClass) {
        try {
            T target = destClass.newInstance();
            BeanUtils.copyProperties(source, target);

            return target;
        } catch (Exception ex) {
            throw new CommonException("Constructor fail", ex);
        }
    }

    public static <T> List<T> copyBeanList(List<?> source, Class<T> destClass) {
        if (source.size() == 0)
            return Collections.emptyList();

        List<T> target = new ArrayList<>(source.size());

        for (Object item : source) {
            try {
                T e = destClass.newInstance();
                BeanUtils.copyProperties(item, e);
                target.add(e);
            } catch (Exception ex) {
                throw new CommonException("Constructor fail", ex);
            }
        }

        return target;
    }
}
