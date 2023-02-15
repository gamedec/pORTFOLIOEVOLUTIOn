
package operator

import org.ejml.data.DMatrixRMaj
import org.ejml.dense.row.CommonOps_DDRM
import org.ejml.kotlin.minus
import org.ejml.kotlin.plus
import org.ejml.kotlin.plusAssign
import org.ejml.kotlin.times
import util.*

sealed class Operation
data class MatrixOp(val opVec: DMatrixRMaj, val opBias: DMatrixRMaj) : Operation()
data class CNot(val i: Int, val j: Int) : Operation()
data class CSwap(val i: Int, val j: Int, val k: Int) : Operation()
data class CCNot(val i: Int, val j: Int, val k: Int) : Operation()
data class Gen1Bit(val i: Int, val p: Double, val q: Double) : Operation()

/**
 * Created by DEDZTBH on 2020/09/15.
 * Project CMU_Coin-flipping_Experience