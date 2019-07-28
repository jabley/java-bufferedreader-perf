Project looking at java.io.BufferedReader.

`make` will run the benchmarks, but it's not a quick thing. It should show that
the patched version of BufferedReader is faster than the JDK BufferedReader,
and using `String.lines` is faster than both.

See https://lemire.me/blog/2019/07/26/how-fast-can-a-bufferedreader-read-lines-in-java/
