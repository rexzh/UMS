//generator==
package com.ums.management.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ums.management.core.dao.MsgL10nMapper;
import com.ums.management.core.model.MsgL10n;
import com.ums.management.core.view.model.MsgL10nVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ums.management.core.model.L10n;
import com.ums.management.core.dao.L10nMapper;
import com.ums.management.core.service.IL10nService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class L10nServiceImpl implements IL10nService {
    @Autowired
    private L10nMapper _dao = null;

    @Autowired
    private MsgL10nMapper _msgDao = null;

    @Override
    public L10n getL10nByCode(String code) {
        return _dao.selectByPrimaryKey(code);
    }

    @Override
    public List<L10n> getL10ns() {
        return _dao.selectL10ns();
    }

    @Override
    public void deleteL10nByCode(String code) {
        _dao.deleteByPrimaryKey(code);
    }

    @Override
    public void create(L10n l10n) {
        _dao.insert(l10n);
    }

    @Override
    public void update(L10n l10n) {
        _dao.updateByPrimaryKey(l10n);
    }

    @Override
    public Map<String, String> getPackageByCode(String code) {
        List<MsgL10n> msgList = _msgDao.selectPackage(code);
        Map<String, String> result = new HashMap<>();
        for (MsgL10n msg : msgList) {
            result.put(msg.getMsg(), msg.getTransMsg());
        }
        return result;
    }

    @Override
    public List<MsgL10n> getMsgL10n(String msg, Integer start, Integer rows) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("msg", msg);

        queryMap.put("start", start);
        queryMap.put("rows", rows);

        return _msgDao.selectMsgL10ns(queryMap);
    }

    @Override
    public Integer countMsgL10n(String msg) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("msg", msg);

        return _msgDao.countMsgL10ns(queryMap);
    }

    @Override
    public List<MsgL10n> getMsgL10nByMsg(String msg){
        return _msgDao.selectByMsg(msg);
    }

    @Override
    @Transactional
    public void saveMsgL10n(List<MsgL10n> l10nList){
        for(MsgL10n l10n : l10nList) {
            if(l10n.getId() != null){
                _msgDao.updateByPrimaryKey(l10n);
            } else {
                _msgDao.insert(l10n);
            }
        }
    }

    @Override
    public void deleteMsgL10nByMsg(String msg){
        _msgDao.deleteByMsg(msg);
    }
}