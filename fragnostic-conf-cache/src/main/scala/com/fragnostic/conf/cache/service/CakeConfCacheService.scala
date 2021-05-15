package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.dao.impl.ConfCacheDaoRedisImpl
import com.fragnostic.conf.cache.service.impl.ConfCacheServiceImpl
import com.fragnostic.conf.env.service.CakeConfEnvService
import io.lettuce.core.{ RedisClient, RedisConnectionException }
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import org.slf4j.{ Logger, LoggerFactory }

object CakeConfCacheService {

  private[this] val logger: Logger = LoggerFactory.getLogger("CakeConfCacheService")

  private val redisCommands: RedisCommands[String, String] = {

    if (logger.isInfoEnabled) {
      logger.info(s"redisCommands - enter")
    }

    val host: String = CakeConfEnvService.confEnvService.getString(key = "REDIS_HOST") fold (
      error => throw new IllegalStateException(error),
      opt => opt map (host => host) getOrElse {
        logger.error("redisCommands - tienes que setear la variable de ambiente REDIS_HOST")
        throw new IllegalStateException("cake.conf.cache.error.host.does.not.exists")
      })

    val port: Int = CakeConfEnvService.confEnvService.getInt(key = "REDIS_PORT") fold (
      error => throw new IllegalStateException(error),
      opt => opt map (host => host) getOrElse {
        logger.error("redisCommands - tienes que setear la variable de ambiente REDIS_PORT")
        throw new IllegalStateException("cake.conf.cache.error.port.does.not.exists")
      })

    // https://lettuce.io/core/release/reference/index.html#redisuri.uri-syntax
    val uri: String = s"redis://$host:${port}"
    val client: RedisClient = RedisClient.create(uri)
    try {
      val connection: StatefulRedisConnection[String, String] = client.connect()
      val redisCommands: RedisCommands[String, String] = connection.sync()
      if (logger.isInfoEnabled) {
        logger.info(s"redisCommands - OK")
      }

      redisCommands
    } catch {
      case e: RedisConnectionException =>
        logger.error(s"redisCommands - RedisConnectionException - ${e.getMessage}")
        // TODO
        ???
      case e: Throwable =>
        logger.error(s"redisCommands - Throwable - ${e.getMessage}")
        // TODO
        ???
    }

  }

  lazy val confCacheService = confCacheServicePiece.confServiceApi

  lazy val confCacheServicePiece = new ConfCacheServiceImpl with ConfCacheDaoRedisImpl {
    override val cache: RedisCommands[String, String] = redisCommands
  }

}
