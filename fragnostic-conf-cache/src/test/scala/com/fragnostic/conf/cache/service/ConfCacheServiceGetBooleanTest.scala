package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeConfCacheService.confCacheService.{ del, set }
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceGetBooleanTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {
    set(keyBoolean, valueBoolean)
  }

  override def afterEach(): Unit = {
    del(keyBoolean)
  }

  describe("***Conf Cache Service Get Boolean Test***") {

    it("Can Get Value As Boolean from Cache") {

      val value = CakeConfCacheService.confCacheService.getBoolean(key = keyBoolean) fold (
        error => throw new IllegalStateException(error),
        value => value //
      )

      assertResult(valueBoolean)(value)

    }

    it("Can Not Get Value As Boolean from Cache") {

      val value = CakeConfCacheService.confCacheService.getBoolean(key = keyThatDoesNotExists) fold (
        error => error,
        value => value //
      )

      assertResult(valueThatNotExists)(value)

    }

  }

}

