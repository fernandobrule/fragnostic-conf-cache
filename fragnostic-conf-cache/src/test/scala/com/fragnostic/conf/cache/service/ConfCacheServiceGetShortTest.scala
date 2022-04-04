package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeConfCacheService.confCacheService.{ del, set }
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceGetShortTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {
    set(keyShort, valueShort)
  }

  override def afterEach(): Unit = {
    del(keyShort)
  }

  describe("***Conf Cache Service Get Short Test***") {

    it("Can Get Value As Short from Cache") {

      val value = CakeConfCacheService.confCacheService.getShort(key = keyShort) fold (
        error => throw new IllegalStateException(error),
        value => value //
      )

      assertResult(valueShort)(value)

    }

    it("Can Not Get Value As Short from Cache") {

      val value = CakeConfCacheService.confCacheService.getShort(key = keyThatDoesNotExists) fold (
        error => error,
        value => value //
      )

      assertResult(valueThatNotExists)(value)

    }

  }

}

