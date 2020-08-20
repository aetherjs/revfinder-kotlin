import model.computeLCP
import org.junit.Assert
import org.junit.Test

class PathSimilarityMetricsTest {
    @Test
    fun `Compute Longest Common Prefix Test`() {
        val testPath1 = "src/com/android/settings/LocationSettings.java"
        val testPath2 = "src/com/android/settings/Utils.java"
        val LCP = computeLCP(testPath1, testPath2)
        Assert.assertEquals("LCP of two strings must equal 4", 4, LCP)
    }


}