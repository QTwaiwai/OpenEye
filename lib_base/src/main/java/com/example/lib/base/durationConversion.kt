package com.example.lib.base

/**
 * author : zeq
 * email : 1301731619@qq.com
 * date : 2024/7/19 20:17
 */

fun Int.timeConversion(): String {
    val minute = this/ 60
    val second = this % 60
    return if (second > 10) {
        "$minute:$second"
    } else {
        "$minute:0$second"
    }
}