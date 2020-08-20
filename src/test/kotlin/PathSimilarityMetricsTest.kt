import model.computeLCP
import model.computeLCSuff
import org.junit.Assert
import org.junit.Test

class PathSimilarityMetricsTest {
    @Test
    fun `Compute Longest Common Prefix Test`() {
        val testPath1 = "src/com/android/settings/LocationSettings.java"
        val testPath2 = "src/com/android/settings/Utils.java"
        val LCPLength = computeLCP(testPath1, testPath2)
        Assert.assertEquals("Longest Common Prefix of two path strings must equal 4", 4, LCPLength)
    }

    @Test
    fun `Compute Longest Common Suffix Test`() {
        val testPath1 = "src/imports/undo/undo.pro"
        val testPath2 = "tests/auto/undo/undo.pro"
        val LCSLength = computeLCSuff(testPath1, testPath2)
        Assert.assertEquals("Longest Common Suffix of two path strings must equal 2", 2, LCSLength)
    }
}