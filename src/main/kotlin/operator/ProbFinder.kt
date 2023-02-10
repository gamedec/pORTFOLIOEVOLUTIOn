
package operator

import org.ejml.data.DMatrixRMaj
import org.ejml.dense.row.CommonOps_DDRM
import org.ejml.kotlin.minus
import org.ejml.kotlin.plus
import org.ejml.kotlin.plusAssign
import org.ejml.kotlin.times
import util.*

sealed class Operation