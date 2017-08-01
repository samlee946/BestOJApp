目录结构  
java			存放java代码  
random			存放用于随机生成被测学生列表的code::blocks工程  
recommandation		存放推荐算法的code::blocks工程  
recommandation_real	存放实际使用的推荐算法的code::blocks工程  
stu_num			存放每个级的学号列表  
transform		存放用于转换输入格式的code::blocks工程  
  
使用说明  
首先需要把recommandation.rar解压放进mysql的数据目录下，然后修改各个java代码第29行的数据库链接字符串Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/recommandation", "root", "123456");，把root和123456改成mysql的账户密码。
以grade13.bat为例，点开批处理文件后，批处理文件要求输入3个数字，分别是：被测试学生的数量、用于投票算法的人数、每个被测学生选择的测试点数量。其中，用于投票算法的人数不能大于200，每个被测学生选择的测试点数量不建议太大，否则统计出的数据有可能是错误的。

批处理文件执行完毕后，会在控制台窗口打印如下信息：
pplcnt:***
test_cnt_per_person:***
total:***
recommandation algorithm #1: ***  **.**%
recommandation algorithm #2: ***  **.**%
recommandation algorithm #3: ***  **.**%
recommandation algorithm #4: ***  **.**%
recommandation algorithm #5: ***  **.**%
recommandation algorithm #6: ***  **.**%
其中，pplcnt表示用于投票算法的人数，
test_cnt_per_person表示每个被测学生选择的测试点数量，
total表示被测试学生的数量，
recommandation algorithm #i: *** **.**%表示第i个算法的正确个数是***，正确率是**.**%。、

运行过程中，批处理文件会在当前目录生成3个临时文件stu_num.txt、stu_problem.txt和tmp.txt。
4个批处理文件不能同时运行。

批处理文件的逻辑
（1）读取用户的输入
（2）读取学号列表并将学号列表作为输入，执行random.exe随机生成被测学生的学号列表
（3）java代码从mysql中读取被测学生的通过题目列表
（4）将被测学生的通过题目列表作为输入，执行transform.exe转换成需要的格式
（5）将转换格式后的被测学生的通过题目列表作为输入，执行recommandation.exe进行推荐和统计