package com.capgemmini

import scala.math.max

object BillingSystem {
  val inventory: List[(String, Item)] = List(
    ("Cola", DrinkItem("Cola", 0.50)) ,
    ("Coffee", HotDrinkItem("Coffee", 1.00)),
    ("Cheese Sandwich", FoodItem("Cheese Sandwich", 2.00)),
    ("Steak Sandwich", HotFoodItem("Steak Sandwich", 4.50))
  )
}

case class BillingSystem() {

  def createBill(items: List[String]): Bill = {
    Bill(items.map(findItem).flatten)
  }

  private def findItem(name :String): Option[Item] = {
    BillingSystem.inventory.find(name == _._1).map(_._2)
  }
}

case class Bill(items: List[Item]) {
  def serviceCharge: Double = {
    val maxServiceCharge = items.map(_.serviceCharge).foldLeft(0.0)(max)
    val charge = itemTotal * maxServiceCharge
    val cappedServiceCharge = if (maxServiceCharge == 0.2 && (charge >= 20.00)) 20.00 else charge
    BigDecimal(cappedServiceCharge).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  def itemTotal: Double = items.map(_.price).foldLeft(0.0)((a, b) => a + b)
}

abstract class Item(val name: String, val price: Double, val serviceCharge: Double = 0.0)
case class DrinkItem(override val name: String, override val price: Double) extends Item(name: String, price: Double)
case class HotDrinkItem(override val name: String, override val price: Double) extends Item(name: String, price: Double)
case class FoodItem(override val name: String, override val price: Double) extends Item(name: String, price: Double) {
  override val serviceCharge = 0.1
}
case class HotFoodItem(override val name: String, override val price: Double) extends Item(name: String, price: Double) {
  override val serviceCharge = 0.2
}



