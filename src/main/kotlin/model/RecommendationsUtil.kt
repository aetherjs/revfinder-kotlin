package model


/**
 * Function that returns a list of recommended reviewers using the specified metric type for reviewers score computation
 *
 * @param reviewHistory a list of [Review] objects comprising information about past code reviews
 * @param newReview a code review object representing the new review for which recommending reviewers is required
 */
fun getReviewersWith(reviewHistory: List<Review>, newReview: Review, metricType: String): Collection<Reviewer> {
    val reviewers = mutableListOf<Reviewer>()
    for (pastReview in reviewHistory) {
        val pastReviewFiles = pastReview.files
        val newReviewFiles = newReview.files
        var pastReviewScore = 0f
        for (newReviewFile in newReviewFiles) {
            for (pastReviewFile in pastReviewFiles) {
                when (metricType) {
                    "LCSuffix" -> pastReviewScore += computeLCSuffixScore(newReviewFile, pastReviewFile)
                    "LCPostfix" -> pastReviewScore += computeLCPostfixScore(newReviewFile, pastReviewFile)
                    "LCSubstring" -> pastReviewScore += computeLCSubstringScore(newReviewFile, pastReviewFile)
                    "LCSubsequence" -> pastReviewScore += computeLCSubsequenceScore(newReviewFile, pastReviewFile)
                }
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