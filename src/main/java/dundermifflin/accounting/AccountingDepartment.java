package dundermifflin.accounting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class AccountingDepartment {

    final static String
            LEDGER_PATH = "/Users/baskuis/ledger.txt";

    public void addToLedger(String entry) {
        try {
            String message = String.format("ENTRY Date:[%s] -- %s\n", new Date(), entry);
            Files.write(
                    Paths.get(LEDGER_PATH),
                    message.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
