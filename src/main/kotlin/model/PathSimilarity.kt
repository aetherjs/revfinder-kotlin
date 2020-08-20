package model

import java.lang.Integer.min
import java.lang.Math.max

fun String.tokenizePath(): List<String> {
    return this.split('/')
}

fun computeLCP(newReviewFile: String, oldReviewFile: String): Int {
    val newRevTokens = newReviewFile.tokenizePath()
    val oldRevTokens = oldReviewFile.tokenizePath()
    var commonPathLength = 0
    val minLength = min(newRevTokens.size, oldRevTokens.size)
    for (i in 0..minLength) {
        if (newRevTokens[i] == oldRevTokens[i]) {
            commonPathLength += 1
        } else {
            break
        }
    }
    return commonPathLength
}

fun computeLCSuff(newReviewFile: String, oldReviewFile: String): Int {
    val newRevTokens = newReviewFile.tokenizePath()
    val oldRevTokens = oldReviewFile.tokenizePath()
    var commonPathLength = 0
    val minLength = min(newRevTokens.size, oldRevTokens.size)
    for (i in minLength..0) {
        if (newRevTokens[i] == oldRevTokens[i]) {
            commonPathLength += 1
        } else {
            break
        }
    }
    return commonPathLength
}

fun computeLCSub(newReviewFile: String, oldReviewFile: String): Int {
    val newRevTokens = newReviewFile.tokenizePath()
    val oldRevTokens = oldReviewFile.tokenizePath()
    var commonPathLength = 0
    if ((newRevTokens.toSet() intersect oldRevTokens.toSet()).isNotEmpty()) {
        val matrix = Array(newRevTokens.size + 1) {IntArray(oldRevTokens.size + 1)}
        for (i in 0..oldRevTokens.size + 1) {
            for (j in 0..newRevTokens.size + 1) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 0
                } else if (oldRevTokens[i-1] == newRevTokens[j-1]) {
                    matrix[i][j] = matrix[i-1][j-1] + 1
                    commonPathLength = kotlin.math.max(commonPathLength, matrix[i][j])

                } else {
                    matrix[i][j] = 0
                }
            }
        }
    }
    return commonPathLength
}

fun computeLCSubSeq(newReviewFile: String, oldReviewFile: String): Int {
    val newRevTokens = newReviewFile.tokenizePath()
    val oldRevTokens = oldReviewFile.tokenizePath()
    var commonPathLength = 0
    if ((newRevTokens.toSet() intersect oldRevTokens.toSet()).isNotEmpty()) {
        val matrix = Array(newRevTokens.size + 1) {IntArray(oldRevTokens.size + 1)}
        for (i in 0..oldRevTokens.size + 1) {
            for (j in 0..newRevTokens.size + 1) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 0
                }
                else if (oldRevTokens[i-1] == newRevTokens[j-1]) {
                    matrix[i][j] = matrix[i-1][j-1] + 1
                } else {
                    matrix[i][j] = kotlin.math.max(matrix[i - 1][j], matrix[i][j - 1])
                }
            }
        }
        commonPathLength = matrix[oldRevTokens.size][newRevTokens.size]
    }
    return  commonPathLength
}