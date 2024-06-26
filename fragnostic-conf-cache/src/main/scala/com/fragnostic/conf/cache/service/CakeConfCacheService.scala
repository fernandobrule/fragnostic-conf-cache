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

  private def getRedisHost: String = {
    CakeConfEnvService.confEnvService.getString(key = "FRG_REDIS_HOST") fold (
      error => throw new IllegalStateException(error),
      host => host //
    )
  }

  private def getRedisPort: Int = {
    CakeConfEnvService.confEnvService.getInt(key = "FRG_REDIS_PORT") fold (
      error => throw new IllegalStateException(error),
      port => port //
    )
  }

  private def getRedisCommands: Either[String, RedisCommands[String, String]] =
    try {
      val host: String = getRedisHost
      val port: Int = getRedisPort
      val uri: String = s"redis://$host:${port}" // https://lettuce.io/core/release/reference/index.html#redisuri.uri-syntax
      val client: RedisClient = RedisClient.create(uri)
      val connection: StatefulRedisConnection[String, String] = client.connect()
      val redisCommands: RedisCommands[String, String] = connection.sync()
      Right(redisCommands)
    } catch {
      case e: RedisConnectionException =>
        logger.error(s"redisCommands - RedisConnectionException - ${e.getMessage}")
        Left(s"cake.conf.cache.service.error.redis.connection.exception")
      case e: Throwable =>
        logger.error(s"redisCommands - Throwable - ${e.getMessage}")
        Left(s"cake.conf.cache.service.error_${e.getMessage}")
    }

  lazy val confCacheService = confCacheServicePiece.confServiceApi

  lazy val confCacheServicePiece = new ConfCacheServiceImpl with ConfCacheDaoRedisImpl {
    override val cache: Either[String, RedisCommands[String, String]] = getRedisCommands
  }

}
