package com.fragnostic.conf.cache.service.impl

import java.util.Locale

import com.fragnostic.conf.base.service.support.{ KeySupport, TypesSupport }
import com.fragnostic.conf.cache.dao.api.ConfCacheDaoApi
import com.fragnostic.conf.cache.service.api.CacheServiceApi

trait CacheServiceImpl extends CacheServiceApi {
  this: ConfCacheDaoApi =>

  def cacheServiceApi: CacheServiceApi = new DefaultCacheService

  class DefaultCacheService extends CacheServiceApi with TypesSupport with KeySupport {

    override def getString(key: String): Either[String, Option[String]] =
      confCacheCrud.get(key)

    override def getString(locale: Locale, key: String): Either[String, Option[String]] =
      confCacheCrud.get(compose(Some(locale), key))

    override def getShort(key: String): Either[String, Option[Short]] =
      getString(key) fold (
        error => Left(error),
        opt => toShort(opt))

    override def getInt(key: String): Either[String, Option[Int]] =
      getString(key) fold (
        error => Left(error),
        opt => toInt(opt))

    override def getLong(key: String): Either[String, Option[Long]] =
      getString(key) fold (
        error => Left(error),
        opt => toLong(opt))

    override def set(key: String, value: String): Either[String, String] =
      confCacheCrud.set(key, value) fold (
        error => Left("conf.cache.service.set.error"),
        statusCodeReply =>
          if ("OK" == statusCodeReply) {
            Right("conf.cache.service.set.success")
          } else {
            Left("conf.cache.service.set.error")
          })

    override def set(key: String, value: Short): Either[String, String] =
      set(key, value.toString)

    override def set(key: String, value: Int): Either[String, String] =
      set(key, value.toString)

    override def set(key: String, value: Long): Either[String, String] =
      set(key, value.toString)

    override def del(key: String): Either[String, Option[String]] =
      confCacheCrud.del(key)

    override def delAllKeys(): Either[String, String] =
      if ("1" == confCacheCrud.delAllKeys()) Right("conf.cache.service.del.all.keys.success")
      else Left("conf.cache.service.del.all.keys.error")

  }

}
