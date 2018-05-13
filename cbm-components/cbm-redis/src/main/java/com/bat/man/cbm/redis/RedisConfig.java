package com.bat.man.cbm.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * EnableCaching<p>
 * 注解描述：<p>
 * Cacheable 表明在Spring调用之前，首先应该在缓存中查找方法的返回值，如果这个值能够找到，就会返回缓存的值，否则这个方法会被调用，返回值会放到缓存中<p>
 * CachePut 表明Spring应该将该方法返回值放到缓存中，在方法调用前不会检查缓存，方法始终会被调用<p>
 * CacheEvict 表明Spring应该在缓存中清楚一个或多个条目<p>
 * Caching 分组注解，能够同时应用多个其他的缓存注解<p>
 * CacheConfig 可以在类层级配置一些共有的缓存配置<p>
 * 注解属性：<p>
 * value 缓存名称<p>
 * condition SpEL表达式，如果得到的值是false，则不会应用缓存在该方法<p>
 * key SpEl表达式，用来计算自定义的缓存key<p>
 * unless SpEl表达式，如果得到的值为true，返回值不会放到缓存中
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

}