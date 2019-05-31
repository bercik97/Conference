package pl.robert.app.shared;

import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public final class SendEmailService implements Notification {

    @Override
    public void send(String email, String name, String msg, Long conferenceId) {
        log.info("Email notifications file is placed in 'resources' folder");

        try {
            @Cleanup PrintStream out =
                    new PrintStream(
                            new FileOutputStream(
                                    new File("src/main/resources/powiadomienia.txt").getAbsolutePath()));
            out.printf(
                    "Temat: Konferencja IT | Do: %s | Data wysłania: " +
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                            "\nWitaj %s!\n" +
                            "%s prelekcji o identyfikatorze:  %d", email, name, msg, conferenceId);

        } catch (FileNotFoundException e) {
            log.warn(e.getClass() + " appeared ...");
        }
    }
}
