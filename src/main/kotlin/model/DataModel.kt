package model

import java.util.*

enum class Status {
    M, // Merged
    R  //Rejected
}

data class Approval(val userID: String, val approveValue: Int, val grantDate: Date)

data class Review(
    val status: String,
    val approveHistory: List<Approval>,
    val submitDate: Date,
    val changeId: String,
    val project: String,
    val closeDate: Date,
    val files: List<String>
)

data class Reviewer(val userID: String, val score: Int)
