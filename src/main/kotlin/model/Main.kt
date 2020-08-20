package model

const val outputFile = "output"

fun main() {
    println("Starting main.kt")
    val reviewsData = getReviewsData()
    val newReview = reviewsData.last()
    val reviewers = recommendReviewers(reviewsData.toMutableList(), newReview)
}

fun recommendReviewers(reviewHistory: MutableList<Review>, newReview: Review): Collection<Reviewer> {
    val reviewers = mutableListOf<Reviewer>()
    reviewHistory.sortBy { it.submitDate }
    val reviewersLCP = getReviewersWithLCP(reviewHistory, newReview)
    return reviewers
}

fun getReviewersWithLCP(reviewHistory: MutableList<Review>, newReview: Review): Collection<Reviewer> {
    val reviewers = mutableListOf<Reviewer>()
    for (pastReview in reviewHistory) {
        val pastReviewFiles = pastReview.files
        val newReviewFiles = newReview.files
        var pastReviewScore = 0f
        for (newReviewFile in newReviewFiles) {
            for (pastReviewFile in pastReviewFiles) {
                pastReviewScore += computeLCPostfixScore(newReviewFile, pastReviewFile)
            }
        }
        pastReviewScore /= (pastReviewFiles.size * newReviewFiles.size)
        for (pastReviewer in pastReview.getReviewers()) {
            if (reviewers.any { it.userID == pastReviewer }) {
                reviewers.find { it.userID == pastReviewer }!!.score += pastReviewScore
            } else {
                reviewers.add(Reviewer(pastReviewer, pastReviewScore))
            }
        }
    }
    return reviewers
}

fun getReviewsData(): Collection<Review> {
    val reviews = mutableListOf<Review>()
    return reviews
}

