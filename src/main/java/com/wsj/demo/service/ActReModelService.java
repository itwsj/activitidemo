package com.wsj.demo.service;

import com.wsj.demo.entity.ActReModel;
import java.util.List;

/**
 * (ActReModel)表服务接口
 *
 * @author makejava
 * @since 2020-07-22 13:54:02
 */
public interface ActReModelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id_ 主键
     * @return 实例对象
     */
    ActReModel queryById(Object id_);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ActReModel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param actReModel 实例对象
     * @return 实例对象
     */
    ActReModel insert(ActReModel actReModel);

    /**
     * 修改数据
     *
     * @param actReModel 实例对象
     * @return 实例对象
     */
    ActReModel update(ActReModel actReModel);

    /**
     * 通过主键删除数据
     *
     * @param id_ 主键
     * @return 是否成功
     */
    boolean deleteById(Object id_);

}