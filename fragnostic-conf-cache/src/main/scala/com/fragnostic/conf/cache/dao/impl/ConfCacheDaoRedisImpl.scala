package com.fragnostic.conf.cache.dao.impl

import com.fragnostic.conf.cache.dao.api.ConfCacheDaoApi
import io.lettuce.core.FlushMode
import io.lettuce.core.api.sync.RedisCommands
import org.slf4j.{ Logger, LoggerFactory }

import java.util
import scala.util.Try

trait ConfCacheDaoRedisImpl extends ConfCacheDaoApi with RedisConnectionAgnostic {

  def confCacheCrud: ConfCacheCrud = new ConfCacheDaoRedisImpl(cache)

  class ConfCacheDaoRedisImpl(val cache: RedisCommands[String, String]) extends ConfCacheCrud {

    private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

    override def set(key: String, value: String): Either[String, String] =
      Try(cache.set(key, value)) fold (
        error => {
          logger.error(s"set() - error on set key[$key] with value[$value], ${error.getMessage}")
          Left(error.getMessage)
        },
        statusCodeReply => Right(statusCodeReply))

    override def set(key: String, value: Short): Either[String, String] = ???

    override def set(key: String, value: Int): Either[String, String] = ???

    override def set(key: String, value: Long): Either[String, String] = ???

    override def get(key: String): Either[String, Option[String]] =
      Try(cache.get(key)) fold (
        error => {
          logger.error(s"get() - error on try to retrieve key[$key], ${error.getMessage}")
          Left(error.getMessage)
        },
        bulkReply => Right(Option(bulkReply)))

    override def del(key: String): Either[String, Option[String]] =
      get(key) fold (
        error => Left(error),
        opt => opt map (
          value => if (cache.del(key) == 1) Right(Option(value)) else Left("redis.dao.del.error")) getOrElse Right(None))

    override def getAllKeys: util.List[String] =
      cache.keys("*")

    override def delAllKeys: String = {
      cache.flushall(FlushMode.ASYNC)
    }

  }

}
