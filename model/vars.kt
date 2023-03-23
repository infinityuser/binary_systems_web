package vars

val PI: Double = 3.1416
val INF: Double = 1e10
val G: Double = 6.67e-11
val c: Double = 299792458.0
val eps: Double = 1e-10
val scale_time_space: Double = 0.03
val scale_time: Double = 86400.0

var dt: Double = 1.0
var e: Double = 0.0
var W: Double = 0.0
var old_e: Double = 0.0
var old_a: Double = 0.0
var time_elapsed: Double = 0.0
var T: Double = 0.0
var dT: Double = 0.0
var counter: Double = 0.0
var step: Double = 0.0
var da: Double = 0.0
var old_T: Double = 0.0
var angular_velocity: Double = 1.0

data class Body(var m: Double, var a: Double)
val one = Body(0.0, 0.0)
val two = Body(0.0, 0.0)
