/*
* Artificial Intelligence for Humans
* Volume 1: Fundamental Algorithms
* Scala Version
* http://www.aifh.org
* http://www.jeffheaton.com
*
* Code repository:
* https://github.com/jeffheaton/aifh

* Copyright 2013 by Jeff Heaton
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
* For more information on Heaton Research copyrights, licenses
* and trademarks visit:
* http://www.heatonresearch.com/copyright
*/
package com.heatonresearch.aifh.examples.discrete

import com.heatonresearch.aifh.discrete.DiscreteAnneal
import com.heatonresearch.aifh.randomize.GenerateRandom
import com.heatonresearch.aifh.randomize.MersenneTwisterGenerateRandom

/**
* This example program shows how to use discrete simulated annealing to find solutions to the Knapsack problem.
* <p/>
* http://en.wikipedia.org/wiki/Knapsack_problem
*/
object KnapsackAnneal extends App {

  /**
   * Number of items to choose from.
   */
  val NUM_ITEMS_TO_CHOOSE: Int = 25
  /**
   * The max weight of the knapsack.
   */
  val KNAPSACK_MAX_WEIGHT: Int = 50
  /**
   * The max weight for an item.
   */
  val ITEM_MAX_WEIGHT: Int = 20
  /**
   * The max value for an item.
   */
  val ITEM_MAX_VALUE: Int = 1000

  // create an start the KnapsackAnneal as an application
  val prg = new KnapsackAnneal
  prg.run()

}

class KnapsackAnneal extends DiscreteAnneal(1000, 40000, 0.001) {
  import KnapsackAnneal._

  /**
   * The profit for each item.
   */
  private val profit: Array[Int] = new Array[Int](NUM_ITEMS_TO_CHOOSE + 1)
  /**
   * The weight for each item.
   */
  private val weight: Array[Int] = new Array[Int](NUM_ITEMS_TO_CHOOSE + 1)
  /**
   * A random number generator.
   */
  private val rnd: GenerateRandom = new MersenneTwisterGenerateRandom(1L)

  /**
   * The current items taken.
   */
  private val currentTaken = {
    val arr = new Array[Boolean](NUM_ITEMS_TO_CHOOSE)
    (0 until arr.length) foreach { i =>
      arr(i) = rnd.nextBoolean
    }
    arr
  }

  /**
   * A backup of the items taken, in case we need to revert.
   */
  private val backupTaken = new Array[Boolean](NUM_ITEMS_TO_CHOOSE)

  /**
   * The best set of items so far.
   */
  private val bestTaken = new Array[Boolean](NUM_ITEMS_TO_CHOOSE)
  balance()

  /**
   * Run the example.
   */
  def run() {
    (0 until NUM_ITEMS_TO_CHOOSE) foreach { n=>
      profit(n) = (Math.random * ITEM_MAX_VALUE).asInstanceOf[Int]
      weight(n) = (Math.random * ITEM_MAX_WEIGHT).asInstanceOf[Int]
    }

    while (!done) {
      iteration()
      println("Iteration #" + getK + ", Best Score=" + getBestScore + "," + getStatus)
    }
    println("item" + "\t" + "profit" + "\t" + "weight" + "\t" + "take")
    (0 until NUM_ITEMS_TO_CHOOSE) foreach { n =>
      println((n + 1) + "\t" + profit(n) + "\t" + weight(n) + "\t" + bestTaken(n))
    }
  }

  override def backupState() {
    System.arraycopy(currentTaken, 0, backupTaken, 0, currentTaken.length)
  }

  override def restoreState() {
    System.arraycopy(backupTaken, 0, currentTaken, 0, currentTaken.length)
  }

  override def foundNewBest() {
    System.arraycopy(currentTaken, 0, bestTaken, 0, currentTaken.length)
  }

  override def moveToNeighbor() {
    val holdingEverythingAlready: Boolean = currentTaken.forall( v => v )

    if (!holdingEverythingAlready) {
      var pt: Int = rnd.nextInt(currentTaken.length)
      while (currentTaken(pt)) {
        pt = rnd.nextInt(currentTaken.length)
      }
      currentTaken(pt) = true
      balance()
    }
  }

  override def evaluate: Double = {
    if (calculateTotalWeight > KNAPSACK_MAX_WEIGHT) {
      return 0
    }
    var result: Int = 0
    (0 until currentTaken.length) foreach { i =>
      if (currentTaken(i)) {
        result += profit(i)
      }
    }
    result
  }

  /**
   * @return The total weight.
   */
  private def calculateTotalWeight: Int = {
    var result: Int = 0
    (0 until currentTaken.length) foreach { i=>
      if (currentTaken(i)) {
        result += weight(i)
      }
    }
    result
  }

  /**
   * Balance and keep below max weight.
   */
  private def balance() {
    while (calculateTotalWeight > KNAPSACK_MAX_WEIGHT) {
      val remove: Int = rnd.nextInt(currentTaken.length)
      currentTaken(remove) = false
    }
  }
}