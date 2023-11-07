package lotto.model;

import static lotto.util.message.ErrorMessages.WINNING_NUMBERS_DUPLICATION_EXCEPTION;
import static lotto.util.message.ErrorMessages.WINNING_NUMBERS_RANGE_EXCEPTION;

import java.util.List;

public class WinningNumbers {
    private static final Integer LOTTO_MIN_NUMBER = 1;
    private static final Integer LOTTO_MAX_NUMBER = 45;

    private final List<Integer> numbers;
    private final Integer bonusNumber;

    public WinningNumbers(List<Integer> numbers, Integer bonusNumber) {
        validate(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> numbers, Integer bonusNumber) {
        validateNumbersRange(numbers);
        validateNumberRange(bonusNumber);

        validateDuplication(numbers);
    }

    private void validateNumbersRange(List<Integer> numbers) {
        numbers.forEach(this::validateNumberRange);
    }

    private void validateNumberRange(Integer number) {
        if (!isValidRange(number)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_RANGE_EXCEPTION);
        }
    }

    private boolean isValidRange(Integer number) {
        return LOTTO_MIN_NUMBER <= number && number <= LOTTO_MAX_NUMBER;
    }

    private void validateDuplication(List<Integer> numbers) {
        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_DUPLICATION_EXCEPTION);
        }
    }

    private boolean isDuplicated(List<Integer> numbers) {
        return numbers.size() != findDistinctCount(numbers);
    }

    private long findDistinctCount(List<Integer> numbers) {
        return numbers.stream().distinct().count();
    }
}
