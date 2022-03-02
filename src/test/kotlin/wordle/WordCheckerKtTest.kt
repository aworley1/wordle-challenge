package wordle

import assertk.assertThat
import assertk.assertions.containsExactly
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class WordCheckerKtTest {
    @Test
    fun `should return grey for incorrect letters`() {
        val result = checkWord("abcde", "fghij")

        assertThat(result).containsExactly("GREY", "GREY", "GREY", "GREY", "GREY")
    }

    @Test
    fun `should return green for correct letters in correct spaces`() {
        val result = checkWord("abcde", "abcde")

        assertThat(result).containsExactly("GREEN", "GREEN", "GREEN", "GREEN", "GREEN")
    }

    @Test
    fun `should return yellow for correct letters in incorrect spaces`() {
        val result = checkWord("bcdea", "abcde")

        assertThat(result).containsExactly("YELLOW", "YELLOW", "YELLOW", "YELLOW", "YELLOW")
    }

    @Test
    fun `should make repeated guess letter grey for any guesses that are greater than the number of original letters`() {
        val result = checkWord("aabbb", "cccca")

        assertThat(result).containsExactly("YELLOW", "GREY", "GREY", "GREY", "GREY")
    }

    @Test
    fun `should make repeated guess letter yellow for any guesses that are up to the number of original letters`() {
        val result = checkWord("aabbb", "cccaa")

        assertThat(result).containsExactly("YELLOW", "YELLOW", "GREY", "GREY", "GREY")
    }

    @Test
    fun `should meet examples`() {
        assertThat(checkWord("eerie", "piece")).containsExactly("YELLOW", "GREY", "GREY", "YELLOW", "GREEN")
        assertThat(checkWord("about", "piece")).containsExactly("GREY", "GREY", "GREY", "GREY", "GREY")
        assertThat(checkWord("siege", "piece")).containsExactly("GREY", "GREEN", "GREEN", "GREY", "GREEN")
        assertThat(checkWord("niece", "piece")).containsExactly("GREY", "GREEN", "GREEN", "GREEN", "GREEN")
        assertThat(checkWord("piece", "piece")).containsExactly("GREEN", "GREEN", "GREEN", "GREEN", "GREEN")
    }
}