package com.wsj.demo.service.impl;

import com.wsj.demo.entity.ActReModel;
import com.wsj.demo.dao.ActReModelDao;
import com.wsj.demo.service.ActReModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ActReModel)表服务实现类
 *
 * @author makejava
 * @since 2020-07-22 13:54:02
 */
@Service("actReModelService")
public class ActReModelServiceImpl implements ActReModelService {
    @Resource
    private ActReModelDao actReModelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id_ 主键
     * @return 实例对象
     */
    @Override
    public ActReModel queryById(Object id_) {
        return this.actReModelDao.queryById(id_);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ActReModel> queryAllByLimit(int offset, int limit) {
        return this.actReModelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param actReModel 实例对象
     * @return 实例对象
     */
    @Override
    public ActReModel insert(ActReModel actReModel) {
        this.actReModelDao.insert(actReModel);
        return actReModel;
    }

    /**
     * 修改数据
     *
     * @param actReModel 实例对象
     * @return 实例对象
     */
    @Override
    public ActReModel update(ActReModel actReModel) {
        this.actReModelDao.update(actReModel);
        return this.queryById(actReModel.getId_());
    }

    /**
     * 通过主键删除数据
     *
     * @param id_ 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id_) {
        return this.actReModelDao.deleteById(id_) > 0;
    }
}