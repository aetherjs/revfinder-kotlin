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

fun Review.getReviewers(): List<String> {
    val reviewers = mutableListOf<String>()
    for (approval in this.approveHistory) {
        reviewers.add(approval.userID)
    }
    return reviewers
}

data class Reviewer(val userID: String, var score: Float)
