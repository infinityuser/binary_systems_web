import calls.*
import vars.*

import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {
	var ops_per_sec = args[0].toDouble()
	var m1_ = args[1].toDouble()
	var m2_ = args[2].toDouble()
	var a_ = args[3].toDouble() 
	var e_ = args[4].toDouble()
	var given_time = args[5].toDouble()

	environmentSet(ops_per_sec, given_time, m1_, m2_, a_, e_)
	while (old_a > 0) {
		globalTimeUpdate()
		environmentRecalculate()
		environmentUpdate()
		periodCalculate()
	}
	reset()
}
