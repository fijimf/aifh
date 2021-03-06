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
package com.heatonresearch.aifh.error

/**
 * An abstract error calculation class that provides some basic functionality.
 */
abstract class AbstractErrorCalculation extends ErrorCalculation {

  /**
   * The overall error.
   */
  protected var globalError: Double = .0
  /**
   * The size of a set.
   */
  protected var setSize: Int = 0

  override def updateError(actual: Vector[Double], ideal: Vector[Double], significance: Double) {
    for(i <- 0 until actual.length) {
      val delta: Double = (ideal(i) - actual(i)) * significance
      globalError += delta * delta
    }
    setSize += ideal.length
  }

  override def updateError(actual: Double, ideal: Double) {
    val delta: Double = ideal - actual
    globalError += delta * delta
    setSize += 1
  }

  override def clear() {
    globalError = 0
    setSize = 0
  }

  override def getSetSize: Int = setSize
}