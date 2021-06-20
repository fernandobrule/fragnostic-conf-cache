package com.fragnostic.conf.cache.service.impl

import com.fragnostic.conf.base.service.api.ConfServiceApi
import com.fragnostic.conf.base.service.support.{ KeyComposeSupport, TypesSupport }
import com.fragnostic.conf.cache.dao.api.ConfCacheDaoApi

import java.util
import java.util.Locale

trait ConfCacheServiceImpl extends ConfServiceApi {
  this: ConfCacheDaoApi =>

  def confServiceApi = new DefaultConfCacheService

  class DefaultConfCacheService extends ConfServiceApi with TypesSupport with KeyComposeSupport {

    private val OK = "OK"

    override def getString(key: String): Either[String, Option[String]] =
      confCacheCrud.get(key)

    override def getString(locale: Locale, key: String): Either[String, Option[String]] =
      confCacheCrud.get(compose(locale, key))

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

    override def getBoolean(key: String): Either[String, Option[Boolean]] =
      getString(key) fold (
        error => Left(error),
        opt => toBoolean(opt))

    def set(key: String, value: String): Either[String, String] =
      confCacheCrud.set(key, value) fold (
        error => Left("conf.cache.service.set.error"),
        statusCodeReply =>
          if (OK == statusCodeReply) {
            Right("conf.cache.service.set.success")
          } else {
            Left("conf.cache.service.set.error")
          })

    def set(key: String, value: Short): Either[String, String] =
      set(key, value.toString)

    def set(key: String, value: Int): Either[String, String] =
      set(key, value.toString)

    def set(key: String, value: Long): Either[String, String] =
      set(key, value.toString)

    def set(key: String, value: Boolean): Either[String, String] =
      set(key, value.toString)

    def del(key: String): Either[String, Option[String]] =
      confCacheCrud.del(key)

    def getAllKeys: Either[String, util.List[String]] =
      confCacheCrud.getAllKeys

    def delAllKeys: Either[String, String] =
      confCacheCrud.delAllKeys fold (
        error => Left(error),
        ans => if (ans.contains(OK)) Right("conf.cache.service.del.all.keys.success")
        else Left("conf.cache.service.del.all.keys.error"))

  }

}
