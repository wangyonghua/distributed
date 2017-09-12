package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.service.SecKillStatEnum;

public class SecKillExecution {

    private long secKillId;

    /**
     * 秒杀执行结果状态
     */
    private int state;

    /**
     * 状态表示
     */
    private String stateInfo;

    private SuccessKilled successKilled;

    public SecKillExecution(long secKillId, int state, String stateInfo, SuccessKilled successKilled) {
        this.secKillId = secKillId;
        this.state = state;
        this.stateInfo = stateInfo;
        this.successKilled = successKilled;
    }

    public SecKillExecution(long secKillId, int state, String stateInfo) {
        this.secKillId = secKillId;
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public SecKillExecution(Long secKillId, SecKillStatEnum repeat) {
    }

    public long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(long secKillId) {
        this.secKillId = secKillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}