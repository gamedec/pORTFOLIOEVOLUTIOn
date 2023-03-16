
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
            "Not" -> coins flip i
            "CNot" -> readInt().let { j -> if (coins[i] != 0) coins[j] = coins[j] xor 1 }
            "CSwap" -> {
                val j = readInt()
                val k = readInt()
                if (coins[i] != 0) coins.swap(j, k)
            }
            "CCNOT" -> {
                val j = readInt()
                val k = readInt()
                if (coins[i] or coins[j] != 0) coins flip k
            }
            "GenFlip" -> coins[i] = if (Random.nextDouble() < readDouble()) 1 else 0
            "Gen1Bit" -> {
                val p = readDouble()