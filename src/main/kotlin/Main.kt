
import operator.Operator
import util.read
import util.reader
import java.io.File

/**
 * Created by DEDZTBH on 2020/09/09.
 * Project CMU_Coin-flipping_Experience
 */

const val DEFAULT_N = 5


fun main(args: Array<String>) {
    if (args.size < 2) throw IllegalArgumentException("Please provide input file and operator name")

    reader = File(args[0]).inputStream().bufferedReader()
