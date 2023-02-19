
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
 */
abstract class ProbFinder(val N: Int) : Operator {
    fun getOneVec(): DMatrixRMaj = DMatrixRMaj(arrayOf(DoubleArray(N) { 1.0 }))
    fun getZeroVec(): DMatrixRMaj = DMatrixRMaj(1, N)
    fun saveMatrix() {
        if (matrixDirty) {
            operations.add(MatrixOp(opVec, opBias))
            opVec = getOneVec()
            opBias = getZeroVec()
            matrixDirty = false
        }
    }

    var opVec = getOneVec()
    var opBias = getZeroVec()
    var matrixDirty = false

    val operations = mutableListOf<Operation>()

    override fun runCmd(cmd: String): Int {
        val i = readInt()
        when (cmd) {
            "Flip" -> {
                opVec[i] = 0.0
                opBias[i] = 0.5
                matrixDirty = true
            }
            "Not" -> {
                opVec[i] *= -1.0
                opBias[i] = 1.0 - opBias[i]
                matrixDirty = true
            }
            "CNot" -> { // cannot represent in matrix op
                val j = readInt()
                saveMatrix()
                operations.add(CNot(i, j))
            }
            "CSwap" -> { // cannot represent in matrix op