package com.wsj.demo.dao;

import com.wsj.demo.entity.ActReModel;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ActReModel)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-22 13:54:02
 */
public interface ActReModelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id_ 主键
     * @return 实例对象
     */
    ActReModel queryById(Object id_);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ActReModel> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param actReModel 实例对象
     * @return 对象列表
     */
    List<ActReModel> queryAll(ActReModel actReModel);

    /**
     * 新增数据
     *
     * @param actReModel 实例对象
     * @return 影响行数
     */
    int insert(ActReModel actReModel);

    /**
     * 修改数据
     *
     * @param actReModel 实例对象
     * @return 影响行数
     */
    int update(ActReModel actReModel);

    /**
     * 通过主键删除数据
     *
     * @param id_ 主键
     * @return 影响行数
     */
    int deleteById(Object id_);

}