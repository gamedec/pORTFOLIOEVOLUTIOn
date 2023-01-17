package operator

/**
 * Created by DEDZTBH on 2020/09/15.
 * Project CMU_Coin-flipping_Experience
 */

/**
 * Children class must have a constructor that takes an Int
 */
interface Operator {
    fun runCmd(cmd: String): Int
    fun printResult()
    fun done() {}

    companion object {
        fun get(name: String, N: Int) =
            Class.forName("operator.$name")
                .getDeclaredConstructor(Int::class.java)
                .newInstance(N) as Operator
    }
}