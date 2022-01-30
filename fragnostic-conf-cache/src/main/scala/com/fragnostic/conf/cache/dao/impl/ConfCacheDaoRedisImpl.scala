package com.fragnostic.conf.cache.dao.impl

import com.fragnostic.conf.cache.dao.api.ConfCacheDaoApi
import io.lettuce.core.FlushMode
import io.lettuce.core.api.sync.RedisCommands
import org.slf4j.{ Logger, LoggerFactory }

import java.util
import scala.util.Try

trait ConfCacheDaoRedisImpl extends ConfCacheDaoApi with RedisConnectionAgnostic {

  def confCacheCrud: ConfCacheCrud = new ConfCacheDaoRedisImpl(cache)

  class ConfCacheDaoRedisImpl(val eitherCache: Either[String, RedisCommands[String, String]]) extends ConfCacheCrud {

    private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

    override def set(key: String, value: String): Either[String, String] =
      eitherCache.fold(
        error => Left(error),
        cache => Try(cache.set(key, value)) fold (
          error => {
            logger.error(s"set() - error on set key[$key] with value[$value], ${error.getMessage}")
            Left(error.getMessage)
          },
          statusCodeReply => Right(statusCodeReply)) //
      )

    override def set(key: String, value: Short): Either[String, String] = ???

    override def set(key: String, value: Int): Either[String, String] = ???

    override def set(key: String, value: Long): Either[String, String] = ???

    override def get(key: String): Either[String, String] =
      eitherCache.fold(
        error => Left(error),
        cache => Try(cache.get(key)) fold (
          error => {
            logger.error(s"get() - error on try to retrieve key[$key], ${error.getMessage}")
            Left(error.getMessage)
          },
          bulkReply => Option(bulkReply) match {
            case None => Left(s"conf.cache.dao.redis.error.key__${key}__does.not.exists")
            case Some(value) => Right(value)
          } //
        ) //
      )

    override def del(key: String): Either[String, Option[String]] =
      get(key) fold (
        error => Left(error),
        value => //
          eitherCache fold (
            error => Left(error),
            cache => if (cache.del(key) == 1) {
              Right(Option(value))
            } else {
              Left("redis.dao.del.error")
            } //
          ) //
      )

    override def getAllKeys: Either[String, util.List[String]] =
      eitherCache.fold(
        error => Left(error),
        cache => Try(cache.keys("*")) fold (
          error => Left(error.getMessage),
          keys => Right(keys)) //
      )

    override def delAllKeys: Either[String, String] =
      eitherCache.fold(
        error => Left(error),
        cache => Right(cache.flushall(FlushMode.ASYNC)) //
      )

  }

}
