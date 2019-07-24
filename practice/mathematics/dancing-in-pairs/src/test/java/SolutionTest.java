import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {
    private static Stream<Arguments> evenNumberOfStudent() {
        return Stream.of(
                Arguments.of(1, false),
                Arguments.of(2, false),
                Arguments.of(5, true),
                Arguments.of(7660142319573120L, true),
                Arguments.of(3272592566470082L, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void evenNumberOfStudent(long day, boolean expected) {
        boolean actual = Solution.evenNumberOfStudentsAttending(day);
        assertThat(actual).isEqualTo(expected);
    }

    @Property
    public void sqrt(@ForAll @Positive int i) {
        long l = (long)i * (long)i;
        assertThat(i).isEqualTo(Solution.sqrt(l));
    }
}