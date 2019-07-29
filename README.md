Project looking at java.io.BufferedReader.

`make` will run the benchmarks, but it's not a quick thing. It should show that
the patched version of BufferedReader is faster than the JDK BufferedReader,
and using `String.lines` is faster than both.

See https://lemire.me/blog/2019/07/26/how-fast-can-a-bufferedreader-read-lines-in-java/

The TunedBufferedReader class is a copy of the BufferedReader class from the
JDK, with the StringBuffer replaced with a StringBuilder.

```diff
@@ -310,7 +310,7 @@ public class BufferedReader extends Reader {
      * @exception  IOException  If an I/O error occurs
      */
     String readLine(boolean ignoreLF) throws IOException {
-        StringBuffer s = null;
+        StringBuilder s = null;
         int startChar;

         synchronized (lock) {
@@ -366,7 +366,7 @@ public class BufferedReader extends Reader {
                 }

                 if (s == null)
-                    s = new StringBuffer(defaultExpectedLineLength);
+                    s = new StringBuilder(defaultExpectedLineLength);
                 s.append(cb, startChar, i - startChar);
             }
         }
```

Running the benchmark on a Java11 JDK gives me this summary:

```
Benchmark Mode Cnt Score Error Units
MyBenchmark.stdLibBufferedReader thrpt 25 29.542 ± 0.599 ops/s
MyBenchmark.patchedStdLibBufferedReader thrpt 25 33.426 ± 0.108 ops/s
MyBenchmark.stringLines thrpt 25 87.141 ± 1.155 ops/s
```

This shows a 13% improvement in this benchmark.
