# Contributing

## Before you code
 * Setup Android Studio / IntelliJ to use the project's code style:
   1. Open the Code Style settings:<br/>
Windows/Linux: **File | Settings | Editor | Code Style**<br/>
OS X: **IntelliJ IDEA | Preferences | Editor | Code Style** 
    2. In the `Scheme` dropdown, make sure the `Project` is selected.
    
## While you code
 * Be sure to format any source files that you have edited.</br>
Windows/Linux: **Code | Reformat Code or press Ctrl+Alt+L**<br>
OS X: **Code | Reformat Code or press ⌥⌘L**

## Before you submit a PR
Before submitting a pull request you should execute the Gradle `check` task. </br>
Windows:
```
gradle.bat check
```
Linux/OS X:
```
./gradlew check
```
This will perform static analysis on the code base using tools such as Checkstyle/Lint/FindBugs/PMD, 
as well as executing all of the unit tests.  If an issue is identified the build will fail and there 
will be a report generated (location of report depends on tools that failed).  An issues found should 
be corrected before submitting your pull request.
