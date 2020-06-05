package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.dao.impl.RedisDaoImpl
import com.fragnostic.conf.cache.service.api.ConfCacheServiceApi
import com.fragnostic.conf.cache.service.impl.ConfCacheServiceImpl
import com.fragnostic.conf.env.service.CakeConfEnvService
import redis.clients.jedis.Jedis

object CakeConfCacheService {

  val jedisInstance: Jedis = {

    val host: String = CakeConfEnvService.confEnvServiceApi.getString(key = "REDIS_HOST") fold (
      error => throw new IllegalStateException(error),
      opt => opt map (host => host) getOrElse (throw new IllegalStateException("cake.conf.cache.error.host.does.not.exists")))

    val port: Int = CakeConfEnvService.confEnvServiceApi.getInt(key = "REDIS_PORT") fold (
      error => throw new IllegalStateException(error),
      opt => opt map (host => host) getOrElse (throw new IllegalStateException("cake.conf.cache.error.port.does.not.exists")))

    new Jedis(host, port)
  }

  lazy val confCacheServicePiece: ConfCacheServiceApi = new ConfCacheServiceImpl with RedisDaoImpl {
    override val jedis: Jedis = jedisInstance
  }

  val confCacheServiceApi = confCacheServicePiece.confCacheServiceApi

}
