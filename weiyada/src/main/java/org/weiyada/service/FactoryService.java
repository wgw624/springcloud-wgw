package org.weiyada.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.weiyada.entity.Factory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.weiyada.entity.req.RequestPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-03-25
 */
public interface FactoryService extends IService<Factory> {
    boolean saveOrUpdateFactory(Factory factory);
    Page<Factory> getAllFactory(RequestPage requestPage);
}
