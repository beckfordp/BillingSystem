package com.capgemmini

import org.scalatest.{FlatSpec, Matchers}


class BillingSystemSpec extends FlatSpec with Matchers {

    behavior of "BillingSystem"

    it should "create bill" in {
        val bill = BillingSystem().createBill(List("Cola"))
        bill.items should contain only(DrinkItem("Cola", 0.50))
    }

    it should "ignore unknown order items" in {
      val bill = BillingSystem().createBill(List("Unknown"))
      bill.items shouldBe (Nil)
    }

    it should "create bill with item total" in {
      val bill = BillingSystem().createBill(List("Cola", "Coffee", "Cheese Sandwich"))
      bill.itemTotal shouldBe(3.50)
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

    it should "create bill with 20% service charge if hot and cold food" in {
      val bill = BillingSystem().createBill(List("Cheese Sandwich", "Steak Sandwich"))
      bill.serviceCharge shouldBe(1.30)
    }

    it should "create bill with max hot food service charge of 20 pounds" in {
      val bill = BillingSystem().createBill(List(
        "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich",
        "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich",
        "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich",
        "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich",
        "Steak Sandwich", "Steak Sandwich", "Coffee", "Cola"
      ))
      bill.serviceCharge shouldBe(20.00)
    }
}

