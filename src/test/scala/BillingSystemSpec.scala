package com.capgemmini

import org.scalatest.{FlatSpec, Matchers}


class BillingSystemSpec extends FlatSpec with Matchers {

    behavior of "BillingSystem"

    it should "create bill" in {
        val bill = BillingSystem().createBill(List("Cola"))
        bill.items shouldBe(List(DrinkItem("Cola", 0.50)))
    }

    it should "create bill with total" in {
      val bill = BillingSystem().createBill(List("Cola", "Coffee", "Cheese Sandwich"))
      bill.sum shouldBe(3.50)
    }

    it should "create bill with no service charge if all items are drinks" in {
      val bill = BillingSystem().createBill(List("Cola", "Coffee"))
      bill.serviceCharge shouldBe(0.00)
    }

    it should "create bill with 10% service charge if any item is food" in {
      val bill = BillingSystem().createBill(List("Cola", "Cheese Sandwich"))
      bill.serviceCharge shouldBe(0.25)
    }

    it should "create bill with 20% service charge if any item is hot food" in {
      val bill = BillingSystem().createBill(List("Cola", "Steak Sandwich"))
      bill.serviceCharge shouldBe(1.00)
    }
}
