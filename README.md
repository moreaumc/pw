The goal of the Pipeline Wizard project is to create a DSL that effectively captures the essense
of the CI/CD pipeline domain without exposing too many internals about the actual execution engine.

Initial goal will be to support Jenkins 1.6.x and Jenkins 2.0 CI servers.

###Design ideas:
- Convention over configuration
- Code generator
- Fully pluggable
- Can mix with lower level DSLs such as Job DSL

###Implementation stack

Use gradle 3.0 scripted by Kotlin
Write DSL in Kotlin
JUnit or TestNG (TBD)

