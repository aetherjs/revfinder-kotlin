package model

import java.lang.Integer.max
import java.lang.Integer.min

/*
A collection of functions computing file path similarity scores in different ways.
Reimplemented in Kotlin following: https://github.com/patanamon/revfinder/blob/master/stringCompare.py
 */

fun String.tokenizePath(): List<String> {
    return this.split('/')
}



fun computeLCPostfixScore(newReviewFile: String, oldReviewFile: String): Float {
    return (computeLCPostfixLength(newReviewFile, oldReviewFile).toFloat()/max(newReviewFile.length, oldReviewFile.length))
}

fun computeLCPostfixLength(newReviewFile: String, oldReviewFile: String): Int {
    val newRevTokens = newReviewFile.tokenizePath()
    val oldRevTokens = oldReviewFile.tokenizePath()
    var commonPathLength = 0
    val minLength = min(newRevTokens.size, oldRevTokens.size)
    for (i in 0 until minLength) {
        if (newRevTokens[i] == oldRevTokens[i]) {
            commonPathLength += 1
        } else {
            break
        }
    }
    return commonPathLength
}

fun computeLCSuffixScore(newReviewFile: String, oldReviewFile: String): Float {
    return (computeLCSuffixLength(newReviewFile, oldReviewFile).toFloat()/max(newReviewFile.length, oldReviewFile.length))
}

fun computeLCSuffixLength(newReviewFile: String, oldReviewFile: String): Int {
    val newRevTokens = newReviewFile.tokenizePath()
    val oldRevTokens = oldReviewFile.tokenizePath()
    var commonPathLength = 0
    val minLength = min(newRevTokens.size, oldRevTokens.size)
    for (i in (minLength - 1) downTo 0) {
        if (newRevTokens[i] == oldRevTokens[i]) {
            commonPathLength += 1
        } else {
            break
        }
    }
    return commonPathLength
}

fun computeLCSubstringScore(newReviewFile: String, oldReviewFile: String): Float {
    return (computeLCSubstringLength(newReviewFile, oldReviewFile).toFloat()/max(newReviewFile.length, oldReviewFile.length))
}

fun computeLCSubstringLength(newReviewFile: String, oldReviewFile: String): Int {
    val newRevTokens = newReviewFile.tokenizePath()
    val oldRevTokens = oldReviewFile.tokenizePath()
    var commonPathLength = 0
    if ((newRevTokens.toSet() intersect oldRevTokens.toSet()).isNotEmpty()) {
        val matrix = Array(oldRevTokens.size) {Array(newRevTokens.size) {0}}
        for (i in oldRevTokens.indices) {
            for (j in newRevTokens.indices) {
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

fun computeLCSubsequenceScore(newReviewFile: String, oldReviewFile: String): Float {
    return (computeLCSubsequenceLength(newReviewFile, oldReviewFile).toFloat()/max(newReviewFile.length, oldReviewFile.length))
}

fun computeLCSubsequenceLength(newReviewFile: String, oldReviewFile: String): Int {
    val newRevTokens = newReviewFile.tokenizePath()
    val oldRevTokens = oldReviewFile.tokenizePath()
    var commonPathLength = 0
    if ((newRevTokens.toSet() intersect oldRevTokens.toSet()).isNotEmpty()) {
        val matrix = Array(oldRevTokens.size) {Array(newRevTokens.size) {0}}
        for (i in oldRevTokens.indices) {
            for (j in newRevTokens.indices) {
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
        commonPathLength = matrix[oldRevTokens.size - 1][newRevTokens.size - 1]
    }
    return  commonPathLength
}