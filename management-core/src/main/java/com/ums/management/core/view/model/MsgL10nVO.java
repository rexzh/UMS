package com.ums.management.core.view.model;

import com.ums.management.core.model.MsgL10n;

import java.util.HashMap;
import java.util.Map;

public class MsgL10nVO {
    private String msg;
    private Map<String, String> l10n;

    public MsgL10nVO() {
        l10n = new HashMap<>();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Map<String, String> getL10n() {
        return l10n;
    }

    public void setL10n(Map<String, String> l10n) {
        this.l10n = l10n;
    }
}
