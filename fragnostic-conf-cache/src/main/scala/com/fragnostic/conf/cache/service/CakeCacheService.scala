package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.dao.impl.RedisDaoImpl
import com.fragnostic.conf.cache.service.impl.CacheServiceImpl
import com.fragnostic.conf.env.service.CakeConfEnvService
import redis.clients.jedis.Jedis

object CakeCacheService {

  private val jedisInstance: Jedis = {

    val host: String = CakeConfEnvService.confServiceApi.getString(key = "REDIS_HOST") fold (
      error => throw new IllegalStateException(error),
      opt => opt map (host => host) getOrElse (throw new IllegalStateException("cake.conf.cache.error.host.does.not.exists")))

    val port: Int = CakeConfEnvService.confServiceApi.getInt(key = "REDIS_PORT") fold (
      error => throw new IllegalStateException(error),
      opt => opt map (host => host) getOrElse (throw new IllegalStateException("cake.conf.cache.error.port.does.not.exists")))

    new Jedis(host, port)
  }

  val cacheServiceApi = new CacheServiceImpl with RedisDaoImpl {
    override val jedis: Jedis = jedisInstance
  }.cacheServiceApi

}
