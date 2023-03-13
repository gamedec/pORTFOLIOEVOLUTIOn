
package operator

import util.readDouble
import util.readInt
import kotlin.random.Random

/**
 * Created by DEDZTBH on 2020/09/15.
 * Project CMU_Coin-flipping_Experience
 */

infix fun IntArray.flip(i: Int) = set(i, get(i) xor 1)
fun IntArray.swap(i: Int, j: Int) = set(i, get(j).also { set(j, get(i)) })

class Tester(N: Int) : Operator {

    private val coins = IntArray(N)

    override fun runCmd(cmd: String): Int {
        val i = readInt()
        when (cmd) {
            "Flip" -> coins[i] = Random.nextBits(1)