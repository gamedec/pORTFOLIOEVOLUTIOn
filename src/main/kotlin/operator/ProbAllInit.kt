
package operator

import org.ejml.data.DMatrixRMaj
import kotlin.math.pow

/**
 * Created by DEDZTBH on 2020/09/15.
 * Project CMU_Coin-flipping_Experience
 */

fun allStatesDouble(n: Int) =
    Array(2.0.pow(n).toInt()) {