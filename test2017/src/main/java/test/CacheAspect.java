package com.frontpay.shuyou.base.redis;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Aspect for Redis distributed lock
 */
@Aspect
@Component
public class CacheAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("@annotation(CacheLock)")
	public void cachePointcut() {
	}

	@Pointcut("@annotation(CacheRWLock)")
	public void cacheRWPointcut() {
	}

	@Around("cachePointcut()")
	public Object cacheAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// 获取注解参数
		getAnnotationParams(joinPoint);

		// 新建一个锁
		RedisLock lock = new RedisLock(lockedPrefix, null);
		// 加锁
		boolean result = lock.lock(timeOut, expireTime);
		if (!result) {  // 取锁失败
			return false;
		}

		try{
			// 加锁成功，执行方法
			return joinPoint.proceed();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			lock.unlock();  // 释放锁
		}

		return true;
	}

	@Around("cacheRWPointcut()")
	public Object cacheRWAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// 获取注解参数
		getRWAnnotationParams(joinPoint);

		// 新建一个锁
		RedisRWLock lock = new RedisRWLock(lockedPrefix, null);

		// 设置读写锁，默认是读锁
		if (priority == 1) {
			lock.setLockedValue(RedisRWLock.W_LOCKED);
		}

		// 加锁
		boolean result = lock.lock(timeOut, expireTime);
		if (!result) {  // 取锁失败
			return false;
		}

		try{
			// 加锁成功，执行方法
			return joinPoint.proceed();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		} finally {
			lock.unlock();  // 释放锁
		}

	}

	private String lockedPrefix;
	private long timeOut;
	private int expireTime;
	private int priority;

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 *
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public  String getAnnotationParams(JoinPoint joinPoint)  throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";

		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					lockedPrefix = method.getAnnotation(CacheLock.class).lockedPrefix();
					timeOut = method.getAnnotation(CacheLock.class).timeOut();
					expireTime = method.getAnnotation(CacheLock.class).expireTime();
					break;
				}
			}
		}

		return description;
	}


	public  String getRWAnnotationParams(JoinPoint joinPoint)  throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";

		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					lockedPrefix = method.getAnnotation(CacheRWLock. class).lockedPrefix();
					timeOut = method.getAnnotation(CacheRWLock. class).timeOut();
					expireTime = method.getAnnotation(CacheRWLock. class).expireTime();
					priority = method.getAnnotation(CacheRWLock.class).priority();
					break;
				}
			}
		}

		return description;
	}
}
