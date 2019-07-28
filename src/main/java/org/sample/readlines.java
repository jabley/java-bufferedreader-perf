package org.sample;

import java.io.*;

public class readlines {
  private long volume = 0;

  readlines() {
  }

  private void parseLine(String s) {
    volume += s.length();
  }

  void readStringViaStdLib(String data) throws IOException {
    StringReader fr = new StringReader(data);
    try (BufferedReader bf = new BufferedReader(fr)) {
      bf.lines().forEach(this::parseLine);
    }
  }

  void readStringViaPatchedStdLib(String data) throws IOException {
    StringReader fr = new StringReader(data);
    try (TunedBufferedReader bf = new TunedBufferedReader(fr)) {
      bf.lines().forEach(this::parseLine);
    }
  }

  void readStringViaStringLines(String data) throws IOException {
    data.lines().forEach(this::parseLine);
  }
}
