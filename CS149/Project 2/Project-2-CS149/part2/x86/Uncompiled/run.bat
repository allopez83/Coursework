"C:\Program Files (x86)\Java\jdk1.8.0_91\bin\javac" p2.java
"C:\Program Files (x86)\Java\jdk1.8.0_91\bin\javah" p2
cl -I"C:\Program Files (x86)\Java\jdk1.8.0_91\include" -I"C:\Program Files (x86)\Java\jdk1.8.0_91\include\win32" -LD p2.cpp -Fep2.dll
java p2
