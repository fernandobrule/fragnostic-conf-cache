package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeConfCacheService.confCacheService.{ del, set }
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceGetIntTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {
    set(keyInt, valueInt)
  }

  override def afterEach(): Unit = {
    del(keyInt)
  }

  describe("***Conf Service Get Int Test***") {

    it("Can Get Value As Int from Cache") {

      val value = CakeConfCacheService.confCacheService.getInt(key = keyInt) fold (
        error => throw new IllegalStateException(error),
        value => value //
      )

      assertResult(valueInt)(value)
    }

    it("Can Not Get Value As Int from Cache") {

      val value = CakeConfCacheService.confCacheService.getInt(key = keyThatDoesNotExists) fold (
        error => error,
        value => value //
      )

      assertResult(valueThatNotExists)(value)
    }

  }

}

