
package util

import org.ejml.data.DMatrixRMaj
import org.ejml.dense.row.CommonOps_DDRM
import org.ejml.simple.ops.SimpleOperations_DDRM

/**
 * Created by DEDZTBH on 2020/09/18.
 * Project CMU_Coin-flipping_Experience
 */

/**
 * Retrieve column i
 */
infix fun DMatrixRMaj.getColumn(i: Int) = CommonOps_DDRM.extractColumn(this, i, null)

/**
 * Replace column i
 */
val simpleOps = SimpleOperations_DDRM()
fun DMatrixRMaj.putColumn(i: Int, col: DMatrixRMaj) = simpleOps.setColumn(this, i, 0, *col.data)

/**
 * Element-wise multiplication (pure)
 */
infix fun DMatrixRMaj.mul(other: DMatrixRMaj) = createLike().also { CommonOps_DDRM.elementMult(this, other, it) }

/**
 * Scalar Multiplication (pure)