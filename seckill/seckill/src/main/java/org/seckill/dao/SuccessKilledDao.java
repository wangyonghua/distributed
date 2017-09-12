package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

/**
 * Created by wangyonghua on 2017/9/12.
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复
     * @param secKillId
     * @param userPhone
     * @return插入的行数
     */
    int inertSuccessKilled(long secKillId,long userPhone);

    /**
     *根据ID查询SuccessKilled并携带秒杀产品对象实体
     * @param secKillId
     * @return
     */
    SuccessKilled queryByIdWithSecKill(long secKillId);
}
