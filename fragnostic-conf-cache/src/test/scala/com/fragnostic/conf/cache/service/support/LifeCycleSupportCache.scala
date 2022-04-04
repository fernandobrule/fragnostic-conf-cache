package com.fragnostic.conf.cache.service.support

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpec

import java.util.Locale

trait LifeCycleSupportCache extends AnyFunSpec with BeforeAndAfterEach {

  val keyThatDoesNotExists = "KEY_THAT_DOES_NOT_EXISTS"
  val valueThatNotExists = "conf.cache.dao.redis.error.key__KEY_THAT_DOES_NOT_EXISTS__does.not.exists"

  //
  // es/CL
  //
  val localeEsCl: Locale = new Locale.Builder().setRegion("CL").setLanguage("es").build()
  val keyEsCl: String = "key_es_CL"
  val valueEsCl: String = "hola"

  //
  // pt/BR
  //
  val localePtBr: Locale = new Locale.Builder().setRegion("BR").setLanguage("pt").build()
  val keyPtBr: String = "key_pt_BR"
  val valuePtBr: String = "oi"

  //
  // en/US
  //
  val localeEnUs: Locale = new Locale.Builder().setRegion("US").setLanguage("en").build()
  val keyEnUs: String = "key_en_US"
  val valueEnUs: String = "hello"

  //
  // Short
  //
  val keyShort: String = "key.short"
  val valueShort: Short = 123

  //
  // Int
  //
  val keyInt: String = "key.int"
  val valueInt: Int = 456

  //
  // Long
  //
  val keyLong: String = "key.long"
  val valueLong: Long = 789L

  //
  // Boolean
  //
  val keyBoolean: String = "key.boolean"
  val valueBoolean: Boolean = true

}
