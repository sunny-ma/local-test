package com.sunny;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * id生成策略
 * TODO 如何根据不同机器自动生成不同机器码，不可重复
 */
@Component
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeIdWorker {

    private static Logger log = LoggerFactory.getLogger(SnowflakeIdWorker.class);

    private static boolean defaultValue = true;

    private static List<Entry> list = new ArrayList<Entry>();

    @Data
    public static class Entry{
        private Long workId;
        private Long datacenterId;
        private String ip;
    }

    public void setList(List<Entry> listAddress) {
        SnowflakeIdWorker.list = listAddress;
    }

    public List<Entry> getList() {
        return list;
    }

    // ==============================Fields===========================================
    /**
     * 开始时间截 (2015-01-01)
     */
    private static final long twepoch = 1420041600000L;


    /**
     * 机器id所占的位数
     */
    private static final long workerIdBits = 5L;


    /**
     * 数据标识id所占的位数
     */
    private static final long datacenterIdBits = 5L;


    /**
     * 支持的最大机器id,结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);


    /**
     * 支持的最大数据标识id,结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);


    /**
     * 序列在id中占的位数
     */
    private static final long sequenceBits = 12L;


    /**
     * 机器ID向左移12位
     */
    private static final long workerIdShift = sequenceBits;


    /**
     * 数据标识id向左移17位(12+5)
     */
    private static final long datacenterIdShift = sequenceBits + workerIdBits;


    /**
     * 时间截向左移22位(5+5+12)
     */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;


    /**
     * 生成序列的掩码,这里为4095 (0b111111111111=0xfff=4095)
     */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);


    /**
     * 工作机器ID(0~31)
     */
    private static Long workerId;


    /**
     * 数据中心ID(0~31)
     */
    private static Long datacenterId;


    /**
     * 毫秒内序列(0~4095)
     */
    private static long sequence = 0L;


    /**
     * 上次生成ID的时间截
     */
    private static long lastTimestamp = -1L;


// ==============================Constructors=====================================

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    /*private SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", workerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", datacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }*/

    /*public synchronized long next(){
        if (worker == null) {
            worker = new SnowflakeIdWorker(0, 0);
        }
        return worker.nextId();
    }*/

    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public static synchronized long nextId() {
        String hostAddress;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e){
            throw new RuntimeException("获取内网ip地址异常");
        }
        for (Entry address : SnowflakeIdWorker.list) {
            if (address.getIp().equals(hostAddress)) {
                SnowflakeIdWorker.workerId = address.getWorkId();
                SnowflakeIdWorker.datacenterId = address.getDatacenterId();
            }
        }
        if (SnowflakeIdWorker.list.isEmpty() && defaultValue) {
            SnowflakeIdWorker.workerId = 0L;
            SnowflakeIdWorker.datacenterId = 0L;
        }
        if (SnowflakeIdWorker.workerId == null || SnowflakeIdWorker.datacenterId == null) {
            throw new RuntimeException(String.format("id生成策略初始化异常，没有在配置文件中找到对应的ip，当前内网ip为：%s", hostAddress));
        }


        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳,说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 如果是同一时间生成的,则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
        // 毫秒内序列溢出
            if (sequence == 0) {
        // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变,毫秒内序列重置
        else {
            sequence = 0L;
        }
        // 上次生成ID的时间截
        lastTimestamp = timestamp;
        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }


    /**
     * 阻塞到下一个毫秒,直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }


    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private static long timeGen() {
        return Instant.now().toEpochMilli();
    }

}