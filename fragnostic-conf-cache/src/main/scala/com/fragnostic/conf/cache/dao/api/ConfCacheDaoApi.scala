package com.fragnostic.conf.cache.dao.api

trait ConfCacheDaoApi {

  def confCacheCrud: ConfCacheCrud

  trait ConfCacheCrud {

    def set(key: String, value: String): Either[String, String]

    def set(key: String, value: Short): Either[String, String]

    def set(key: String, value: Int): Either[String, String]

    def set(key: String, value: Long): Either[String, String]

    def get(key: String): Either[String, Option[String]]

    def del(key: String): Either[String, Option[String]]

    def delAllKeys(): String

  }

}
