set /p stucnt=input the number of student you want to be tested:
set /p pplcnt=input the number of people you want to use it to test:
set /p testcnt=input the number of testcases for each student:
echo %stucnt% > .\java\tmp.txt
copy /b .\java\tmp.txt+.\stu_num\grade15_stu_num.txt .\java\tmp.txt
.\random\bin\Debug\random.exe < .\java\tmp.txt > stu_num.txt
javac -classpath ".;.\java\libs\mysql-connector-java-5.1.41-bin.jar;" .\java\GetDataFromDB15.java
java -classpath ".;.\java\libs\mysql-connector-java-5.1.41-bin.jar;.\java\;" GetDataFromDB15
echo %pplcnt% > tmp.txt
echo %testcnt% >> tmp.txt
.\transform\bin\Debug\transform.exe < stu_problem.txt >> tmp.txt
.\recommandation\bin\Debug\recommandation.exe < tmp.txt
PAUSE