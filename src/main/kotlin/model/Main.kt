package model

const val outputFile = "output"

fun main() {
    println("Starting main.kt")
    val reviewsData = getReviewsData()
    val newReview = reviewsData.last()
    val reviewers = combinedReviewersScores(reviewsData.toMutableList(), newReview)
}

fun combinedReviewersScores(reviewHistory: MutableList<Review>, newReview: Review): Collection<Reviewer> {
    val reviewers = mutableListOf<Reviewer>()
    reviewHistory.sortBy { it.submitDate }
    val reviewersLCP = getReviewersWith(reviewHistory, newReview, "LCP")
    return reviewers
}

fun getReviewsData(): Collection<Review> {
    val reviews = mutableListOf<Review>()
    return reviews
}

