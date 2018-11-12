package com.jeecms.core.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.UnifiedUser;

public interface UnifiedUserDao {
    UnifiedUser getByUsername(String username);

    List<UnifiedUser> getByEmail(String email);

    int countByEmail(String email);

    Pagination getPage(int pageNo, int pageSize);

    UnifiedUser findById(Integer id);

    UnifiedUser save(UnifiedUser bean);

    UnifiedUser updateByUpdater(Updater<UnifiedUser> updater);

    UnifiedUser deleteById(Integer id);
}