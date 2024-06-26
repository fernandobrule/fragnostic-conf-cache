package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeConfCacheService.confCacheService.set
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceDeleteAllKeysTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {
    set("1", "1")
    set("2", "2")
    set("3", "3")
    set("4", "4")
    set("5", "5")
  }

  override def afterEach(): Unit = {
  }

  describe("***Conf Cache Service Delete All Keys Test***") {

    it("Can Delete All Keys") {

      val size: Int = CakeConfCacheService.confCacheService.getAllKeys fold (
        error => throw new IllegalStateException(error),
        allKeys => allKeys.size() //
      )

      // TODO size should be >= 5

      val message: String = CakeConfCacheService.confCacheService.delAllKeys fold (
        error => error,
        message => message //
      )

      assertResult(message)("conf.cache.service.del.all.keys.success")

      val zeroSize: Int = CakeConfCacheService.confCacheService.getAllKeys fold (
        error => throw new IllegalStateException(error),
        allKeys => allKeys.size() //
      )

      assertResult(zeroSize)(0)

    }

  }

}

