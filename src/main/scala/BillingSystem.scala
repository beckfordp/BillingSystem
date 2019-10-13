package com.capgemmini

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

  def allDrinks = items.forall(_.isInstanceOf[Drink])

  def anyFood = items.exists(_.isInstanceOf[Food])

  def hotFood = items.exists(i => i.isInstanceOf[Hot] && i.isInstanceOf[Food])

  def serviceCharge: Double = {
  val charge = 
    if (allDrinks)
      0.00
    else if (anyFood)
      if (hotFood)
        itemTotal * 0.20
        else
          itemTotal * 0.10
    else
      0.0

    BigDecimal(charge).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  def itemTotal: Double = items.map(_.price).foldLeft(0.0)((a, b) => a + b)
  
}

trait Drink
trait Food
trait Hot

abstract class Item(val name: String, val price: Double)

case class DrinkItem(override val name: String, override val price: Double) extends Item(name: String, price: Double) with Drink

case class HotDrinkItem(override val name: String, override val price: Double) extends Item(name: String, price: Double) with Drink with Hot

case class FoodItem(override val name: String, override val price: Double) extends Item(name: String, price: Double) with Food

case class HotFoodItem(override val name: String, override val price: Double) extends Item(name: String, price: Double) with Food with Hot



