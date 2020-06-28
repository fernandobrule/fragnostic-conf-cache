package com.fragnostic.conf.cache.service.impl

import com.fragnostic.conf.base.service.api.ConfServiceApi
import com.fragnostic.conf.base.service.support.{ KeySupport, TypesSupport }
import com.fragnostic.conf.cache.service.CakeCacheService

trait ConfCacheServiceImpl extends ConfServiceApi {

  def confServiceApi: ConfServiceApi = new DefaultConfCacheService

  class DefaultConfCacheService extends ConfServiceApi with TypesSupport with KeySupport {

    override def getString(key: String): Either[String, Option[String]] =
      CakeCacheService.cacheServiceApi.getString(key)

    override def getShort(key: String): Either[String, Option[Short]] =
      CakeCacheService.cacheServiceApi.getShort(key)

    override def getInt(key: String): Either[String, Option[Int]] =
      CakeCacheService.cacheServiceApi.getInt(key)

    override def getLong(key: String): Either[String, Option[Long]] =
      CakeCacheService.cacheServiceApi.getLong(key)

  }

}
