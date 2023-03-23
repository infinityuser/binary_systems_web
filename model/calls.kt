package calls
import vars.*

import kotlin.math.pow
import kotlin.math.sqrt

fun globalTimeUpdate() {
	time_elapsed += dt
}

fun periodCalculate() {
	old_T = T
	T = 2 * PI / angular_velocity
}

fun environmentSet(ops_per_sec: Double = 2e6, given_time: Double = 1.0, m1_: Double = 1.441, m2_: Double = 1.387, a_: Double = 1.95, e_: Double = 0.617) {
	e = e_
	one.m = m1_ * 2e30
	two.m = m2_ * 2e30
	one.a = if (m1_ != 0.0 && m2_ != 0.0) a_ * 1e9 else 0.0
	two.a = one.m * one.a / two.m
	old_a = one.a
	old_e = e_

	val tau: Double = (5 * c.pow(5) * old_a.pow(4)) / (256 * G.pow(3) * one.m * two.m * (one.m + two.m)) * (1 - old_e.pow(2)).pow(3.5)
	dt = tau / (given_time * ops_per_sec)
	counter = 0.0
	step = given_time * ops_per_sec / 200
}

fun environmentUpdate() {
	old_a = one.a
	old_e = e
}

fun environmentRecalculate() {
	var remnant: Double = (1 + 73/24 * old_e.pow(2) + 37/96 * old_e.pow(4))

	W = 32/5 * (G.pow(4) * one.m.pow(2) * two.m.pow(2) * (one.m + two.m)) / (c.pow(5) * old_a.pow(5) * (1 - old_e.pow(2)).pow(3.5)) * remnant

	e += dt * (-304 * G.pow(3) * one.m * two.m * (one.m + two.m) * old_e) / (15 * c.pow(5) * old_a.pow(4) * (1 - old_e.pow(2)).pow(2.5)) * (1 + 121/304 * old_e.pow(2))

	one.a += dt * (-64 * G.pow(3) * one.m * two.m * (one.m + two.m)) / (5 * c.pow(5) * old_a.pow(3) * (1 - old_e.pow(2)).pow(3.5)) * remnant

	dT = 96/5 * (2 * PI).pow(2.666) * one.m / c.pow(5) * two.m / (one.m + two.m).pow(0.333) * G.pow(1.666) * 3.16 * 1e13 / old_T.pow(1.666) / (1 - old_e.pow(2)).pow(3.5) * remnant

	da = (64 * G.pow(3) * one.m * two.m * (one.m + two.m)) / ((5 * c.pow(5) * old_a.pow(3)) * (1 - old_e.pow(2)).pow(3.5)) * remnant * 3.16 * 1e7

	two.a = one.m * one.a / two.m

    angular_velocity = sqrt(G * (one.m + two.m) / one.a.pow(3))

	if (counter < -2) {
		println("A " + (one.a + two.a).toString())
		println("E " + e.toString())
		println("W " + W.toString())
		println("T " + (T / 3600).toString())
		println("dT " + dT.toString())
		println("Time " + (time_elapsed / 31536000).toString())
		println("_")
		counter += step
	}
	counter--
}

fun reset() {
	old_a = 0.0
	old_e = 0.0
	T = 0.0
}
