package com.example.mvvm_rx2.extension

/**
 * @author burkd
 * @since 2019-11-05
 */

private val REGEX_NUMBER: String = "-?\\d+(\\.\\d+)?"

fun String?.toNonNullString(): String {
    return this ?: ""
}

fun String?.isNotNullOrEmpty(): Boolean {
    return !(this.isNullOrEmpty())
}

fun String?.isNotNullOrBlank(): Boolean {
    return !(this.isNullOrBlank())
}

fun String?.isNumber(): Boolean {
    return if (this.isNullOrEmpty()) {
        false
    } else {
        REGEX_NUMBER.toRegex() matches this!!
    }
}

fun String?.isNotNumber(): Boolean {
    return !(this.isNumber())
}

inline fun String?.convertToInteger(integersFunc: (Int) -> Int): Int {
    this?.toIntOrNull()?.let {
        return integersFunc(it)
    }
    return 0
}

inline fun String?.ifItIntegerString(func: (Int) -> Unit) {
    this?.toIntOrNull()?.let {
        func(it)
    }
}

fun String?.notEqual(op: String?): Boolean {
    return if (this.isNullOrEmpty() || op.isNullOrEmpty()) {
        false
    } else {
        !(this.equals(op))
    }
}

fun String?.length(): Int {
    return if (this.isNullOrEmpty()) {
        0
    } else {
        this.length
    }
}

fun String?.appendOf(text: String?): String {
    return if (this == null && text == null) {
        ""
    } else if (this != null && text == null) {
        this
    } else if (this == null && text != null) {
        text
    } else {
        "$this$text"
    }
}