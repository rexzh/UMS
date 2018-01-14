//generator==
package com.ums.management.core.service;

import java.util.Map;
import java.util.List;

import com.ums.management.core.model.L10n;
import com.ums.management.core.model.MsgL10n;
import com.ums.management.core.view.model.MsgL10nVO;

public interface IL10nService {
    L10n getL10nByCode(String code);

    List<L10n> getL10ns();

    void deleteL10nByCode(String code);

    void create(L10n l10n);

    void update(L10n l10n);

    Map<String, String> getPackageByCode(String code);

    List<MsgL10n> getMsgL10n(String msg, Integer start, Integer rows);
    Integer countMsgL10n(String msg);

    List<MsgL10n> getMsgL10nByMsg(String msg);
    void saveMsgL10n(List<MsgL10n> l10nList);
    void deleteMsgL10nByMsg(String msg);
}