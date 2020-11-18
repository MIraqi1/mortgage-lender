import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


final class LenderTest {

    @Test
    void check_balance() {
        Lender lender = new Lender(500);
        Assertions.assertEquals(500,lender.getBalance());
    }
}
