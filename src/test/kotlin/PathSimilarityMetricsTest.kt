import model.computeLCPostfixLength
import model.computeLCSubstringLength
import model.computeLCSubsequenceLength
import model.computeLCSuffixLength
import org.junit.Assert
import org.junit.Test

class PathSimilarityMetricsTest {
    @Test
    fun `Compute Longest Common Prefix Test`() {
        val testPath1 = "src/com/android/settings/LocationSettings.java"
        val testPath2 = "src/com/android/settings/Utils.java"
        val LCPLength = computeLCPostfixLength(testPath1, testPath2)
        Assert.assertEquals("Longest Common Prefix's length of two path strings must equal 4", 4, LCPLength)
    }

    @Test
    fun `Compute Longest Common Suffix Test`() {
        val testPath1 = "src/imports/undo/undo.pro"
        val testPath2 = "tests/auto/undo/undo.pro"
        val LCSLength = computeLCSuffixLength(testPath1, testPath2)
        Assert.assertEquals("Longest Common Suffix's length of two path strings must equal 2", 2, LCSLength)
    }

    @Test
    fun `Compute Longest Common Substring Test`() {
        val testPath1 = "res/layout/bluetooth_pin_entry.xml"
        val testPath2 = "tests/res/layout/operator_main.xml"
        val LCSubString = computeLCSubstringLength(testPath1, testPath2)
        Assert.assertEquals("Longest Common Substring's length of two path strings must equal 2", 2, LCSubString)
    }

    @Test
    fun `Compute Longest Common Subsequence Test`() {
        val testPath1 = "apps/CtsVerifier/src/com/android/cts/verifier/sensors/MagnetometerTestActivity.java"
        val testPath2 = "tests/tests/hardware/src/android/hardware/cts/SensorTest.java"
        val LCSubSeq = computeLCSubsequenceLength(testPath1, testPath2)
        Assert.assertEquals("Longest Common Subsequence's length of two path strings must equal 3", 3, LCSubSeq)
    }
}