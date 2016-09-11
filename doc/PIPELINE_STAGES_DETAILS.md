This document examines the value proposition for each stage in the pipeline along with 
patterns and anti-patterns for effective software engineering.

### Stage: Build

The build stage is tipically triggered by a 'merge to trunk/master branch' event. It starts bycloning the source code repository
 (Git terminology). Next is one or more compilation steps (various code/doc generation) followed by the execution of the unit test suite.
The last job/step in this stage is typically to upload the tested artifact to an artifact store such as
Nexus, Artifactory, Docker registry, etc.

Patterns: good definition of what is a unit test in your application. Use mock objects to isolate your code and maintain good test speed.
Generate mock objects automatically during integration/functional/end-to-end test execution.

Anti-patterns: tests that reach out to resources on a different host. Using a local DB may also slow down tests too much.
Using mock objects inappropriately, thus creating weak integration tests vs good unit tests.

### Optional Stage: Pre-commit build

This is exactly the same as the build stage, except the work is done on a 'story' branch and not artifacts are archived.
The purpose of this stage is to give developers quick feedback from an entirely clean environment 
without using the resources of their development machine. The same effect may be achievable by using a Docker container locally 
if it does not slow down developer's work.
