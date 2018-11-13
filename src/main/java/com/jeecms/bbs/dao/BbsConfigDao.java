package com.jeecms.bbs.dao;

import com.jeecms.bbs.entity.BbsConfig;
import com.jeecms.common.hibernate4.Updater;

/**
 * 论坛配置数据接口
 * @author: andy_hulibo@163.com
 * @date: 2018/11/13 17:40
 */
public interface BbsConfigDao {
    /**
     * 清理当日数据
     */
    void clearTodayData();

    BbsConfig findById(Integer id);

    BbsConfig save(BbsConfig bean);

    BbsConfig updateByUpdater(Updater<BbsConfig> updater);

    BbsConfig deleteById(Integer id);
}