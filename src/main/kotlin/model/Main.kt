package model

const val outputFile = "output"

fun main() {
    println("Starting main.kt")
    val reviewsData = getReviewsData()
    val reviewers = recommendReviewers(reviewsData)
}

fun recommendReviewers(data: Collection<Review>): Collection<Reviewer> {
    val reviewers = mutableListOf<Reviewer>()

    return reviewers
}

fun getReviewsData(): Collection<Review> {
    val reviews = mutableListOf<Review>()
    return reviews
}
