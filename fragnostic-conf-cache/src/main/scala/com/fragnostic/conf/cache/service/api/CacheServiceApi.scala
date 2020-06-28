package com.fragnostic.conf.cache.service.api

import java.util.Locale

trait CacheServiceApi {

  def cacheServiceApi: CacheServiceApi

  trait CacheServiceApi {

    def getString(key: String): Either[String, Option[String]]

    def getString(locale: Locale, key: String): Either[String, Option[String]]

    def getShort(key: String): Either[String, Option[Short]]

    def getInt(key: String): Either[String, Option[Int]]

    def getLong(key: String): Either[String, Option[Long]]

    def set(key: String, value: String): Either[String, String]

    def set(key: String, value: Short): Either[String, String]

    def set(key: String, value: Int): Either[String, String]

    def set(key: String, value: Long): Either[String, String]

    def del(key: String): Either[String, Option[String]]

    def delAllKeys(): Either[String, String]

  }

}
