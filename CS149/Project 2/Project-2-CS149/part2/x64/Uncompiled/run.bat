
"C:\Program Files\Java\jdk1.8.0_66\bin\javac" p2.java
"C:\Program Files\Java\jdk1.8.0_66\bin\javah" p2
cl -I"C:\Program Files\Java\jdk1.8.0_66\include" -I"C:\Program Files\Java\jdk1.8.0_66\include\win32" -LD p2.cpp -Fep2.dll
java p2
