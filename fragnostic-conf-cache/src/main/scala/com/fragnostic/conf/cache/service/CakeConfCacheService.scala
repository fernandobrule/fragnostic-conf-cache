package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.dao.impl.ConfCacheDaoRedisImpl
import com.fragnostic.conf.cache.service.impl.ConfCacheServiceImpl
import com.fragnostic.conf.env.service.CakeConfEnvService
import org.slf4j.{ Logger, LoggerFactory }
import redis.clients.jedis.Jedis

object CakeConfCacheService {

  private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  private val jedisInstance: Jedis = {

    val host: String = CakeConfEnvService.confEnvService.getString(key = "REDIS_HOST") fold (
      error => throw new IllegalStateException(error),
      opt => opt map (host => host) getOrElse {
        logger.error("jedisInstance - tienes que setear la variable de ambiente REDIS_HOST")
        throw new IllegalStateException("cake.conf.cache.error.host.does.not.exists")
      })

    val port: Int = CakeConfEnvService.confEnvService.getInt(key = "REDIS_PORT") fold (
      error => throw new IllegalStateException(error),
      opt => opt map (host => host) getOrElse {
        logger.error("jedisInstance - tienes que setear la variable de ambiente REDIS_PORT")
        throw new IllegalStateException("cake.conf.cache.error.port.does.not.exists")
      })

    new Jedis(host, port)
  }

  /*val cacheServiceApi = new CacheServiceImpl with ConfCacheDaoRedisImpl {
    override val jedis: Jedis = jedisInstance
  }.cacheServiceApi
  */

  lazy val confCacheService = confCacheServicePiece.confServiceApi

  lazy val confCacheServicePiece = new ConfCacheServiceImpl with ConfCacheDaoRedisImpl {
    override val jedis: Jedis = jedisInstance
  }

}
