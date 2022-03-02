package wordle

fun checkWord(guess: String, answer: String): List<String> {
    val remainingToGuess = answer.toMutableList()

    return guess.zip(answer)
        .map { (guessChar, answerChar) ->
            if (guessChar == answerChar) {
                remainingToGuess.remove(guessChar)
                Pair(guessChar, true)
            } else {
                Pair(guessChar, false)
            }
        }.map { (guessChar, isCorrectCharInCorrectPosition) ->
            val isCorrectCharInWrongPosition = remainingToGuess.remove(guessChar)
            when {
                isCorrectCharInCorrectPosition -> "GREEN"
                isCorrectCharInWrongPosition -> "YELLOW"
                else -> "GREY"
            }
        }
}