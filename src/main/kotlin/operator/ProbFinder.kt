
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
                val j = readInt()
                val k = readInt()
                saveMatrix()
                operations.add(CSwap(i, j, k))
            }
            "CCNOT" -> { // cannot represent in matrix op
                val j = readInt()
                val k = readInt()
                saveMatrix()
                operations.add(CCNot(i, j, k))
            }
            "GenFlip" -> {
                val j = readDouble()
                opVec[i] = 0.0
                opBias[i] = j
                matrixDirty = true
            }
            "Gen1Bit" -> { // cannot represent in matrix op
                val p = readDouble()
                val q = readDouble()
                saveMatrix()
                operations.add(Gen1Bit(i, p, q))
            }
            else -> return -1
        }
        return 0
    }

    override fun done() = saveMatrix()

    fun eval(probs: DMatrixRMaj): DMatrixRMaj = probs.apply {
        val broadcastVec =
            DMatrixRMaj(probs.numRows, 1, true, *DoubleArray(probs.numRows) { 1.0 })
        operations.forEach {
            it.apply {
                when (this) {
                    is MatrixOp -> {
                        CommonOps_DDRM.elementMult(probs, broadcastVec * opVec)
                        probs += broadcastVec * opBias
                    }
                    is CNot -> {
                        val x = probs getColumn i
                        val y = probs getColumn j
                        //(1 - x) * y + x * (1 - y)
                        probs.putColumn(j, (-2.0 * x mul y) + x + y)
                    }
                    is CSwap -> {
                        val x = probs getColumn i
                        val y = probs getColumn j
                        val z = probs getColumn k
                        val xy = x mul y
                        val xz = x mul z
                        //(1 - x) * y + x * z
                        probs.putColumn(j, y - xy + xz)
                        //(1 - x) * z + x * y
                        probs.putColumn(k, z - xz + xy)
                    }
                    is CCNot -> {
                        val x = probs getColumn i
                        val y = probs getColumn j
                        val z = probs getColumn k
                        //(1 - x) * (1 - y)
                        val probBoth0 = (x mul y) - x - y + 1.0
                        //probBoth0 * z + (1 - probBoth0) * (1 - z)
                        probs.putColumn(k, (2.0 * probBoth0 mul z) - probBoth0 - z + 1.0)
                    }
                    is Gen1Bit -> {
                        val x = probs getColumn i
                        val xx = x mul x
                        val pComp = 1.0 - p
                        //x * (1 - q) * x + (1 - x) * (p + (1 - p) * x)
                        //= xx(1-q) + p + (1-p)x - xp - xx(1-p)
                        probs.putColumn(i, (xx * (1.0 - q)) + p + (x * pComp) - (x * p) - (xx * pComp))
                    }
                }
            }
        }
    }
}