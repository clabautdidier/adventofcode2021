@echo off
xcopy src\main\java\day0 src\main\java\day%1\
xcopy src\test\java\day0 src\test\java\day%1\
xcopy src\test\resources\day0 src\test\resources\day%1\

call substitute.bat day0 day%1 src\main\java\day%1\Solver.java > src\main\java\day%1\tmp
copy src\main\java\day%1\tmp src\main\java\day%1\Solver.java
del src\main\java\day%1\tmp

call substitute.bat day0 day%1 src\test\java\day%1\SolverTest.java > src\test\java\day%1\tmp
copy src\test\java\day%1\tmp src\test\java\day%1\SolverTest.java
del src\test\java\day%1\tmp