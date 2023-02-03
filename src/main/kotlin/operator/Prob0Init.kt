
package operator

import kotlin.math.pow

/**
 * Created by DEDZTBH on 2020/09/15.
 * Project CMU_Coin-flipping_Experience
 */

fun allStates(n: Int) =
    Array(2.0.pow(n).toInt()) {
        IntArray(n) { i -> (it shr i) and 1 }.apply { reverse() }
    }

class Prob0Init(N: Int) : ProbFinder(N) {
    override fun printResult() {
        val probs = eval(getZeroVec())
        allStates(N).forEach { endState ->
            var prob = 1.0
            endState.forEach { i ->
                val x = probs[i]
                prob *= if (i > 0) x else 1.0 - x
            }