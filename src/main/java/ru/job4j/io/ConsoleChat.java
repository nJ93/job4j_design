package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {
  private static final String OUT = "закончить";
  private static final String STOP = "стоп";
  private static final String CONTINUE = "продолжить";
  private final String path;
  private final String botAnswers;

  public ConsoleChat(String path, String botAnswers) {
    this.path = path;
    this.botAnswers = botAnswers;
  }

  public void run() {

    boolean isStopped = false;
    List<String> logPhrases = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      String userPhrase = reader.readLine();
      while (!OUT.equals(userPhrase)) {
        if (STOP.equals(userPhrase) && !isStopped) {
          logPhrases.add(STOP);
          isStopped = true;
          continue;
        } else if (CONTINUE.equals(userPhrase) && isStopped) {
          logPhrases.add(CONTINUE);
          isStopped = false;
          continue;
        }
        logPhrases.add(userPhrase);
        if (!isStopped) {
          List<String> botPhrases = readPhrases();
          if (botPhrases.size() != 0) {
            String botPhrase = botPhrases.get(new Random().nextInt(botPhrases.size()));
            logPhrases.add(botPhrase);
            System.out.println(botPhrase);
          } else {
            System.out.println("У бота отсутствуют фразы");
          }
        }
        userPhrase = reader.readLine();
      }
      saveLog(logPhrases);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private List<String> readPhrases() {
    List<String> phrases = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
      phrases = reader.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return phrases;
  }

  private void saveLog(List<String> log) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
      log.forEach(line -> writer.write(line + System.lineSeparator()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws Exception {
    ConsoleChat cc = new ConsoleChat("log_phrases.txt", "bot_phrases.txt");
    cc.run();
  }
}
