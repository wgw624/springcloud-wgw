package org.weiyada.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.weiyada.entity.Factory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.weiyada.entity.req.RequestPage;
import org.weiyada.entity.res.BooleanRes;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-03-25
 */
public interface FactoryService extends IService<Factory> {

    /**
     *
     *@description:
     * @param factory
     *@return: BooleanRes
     *@author: weiguangwei
     *@time: 2021/3/29 3:37 下午
     */
    BooleanRes saveOrUpdateFactory(Factory factory);

    /**
     *
     *@description:
     * @param requestPage
     *@return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<org.weiyada.entity.Factory>
     *@author: weiguangwei
     *@time: 2021/3/29 3:37 下午
     */
    Page<Factory> getAllFactory(RequestPage requestPage);
}
