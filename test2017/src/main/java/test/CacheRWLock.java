package com.frontpay.shuyou.base.redis;

import java.lang.annotation.*;

/**
 * 方法级注解，Redis分布式锁
 *
 * @author 赵仲文
 * @Copyright (c) 2016, frontpay.cn
 * @date 2017/1/3 10:16
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheRWLock {
    String lockedPrefix() default "";  // redis 锁key的前缀
    long timeOut() default 2000;  // 轮询锁的时间
    int expireTime() default 1000;  // key在redis里存在的时间，1000S
    int priority() default 0;
}
