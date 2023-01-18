
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