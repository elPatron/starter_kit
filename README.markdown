#s4
* to run sbt - console ./sbt
* to use intellij tell intellij you want to use this local copy of sbt
in your project settings

Play application:

Play commands embedded in sbt:
* play -  lists all play commands (via play console prompt)
* run -  runs in interactive "hot reload mode"
* start - runs independently and code changes are not reflected until restart


To test

* test - is unit
* int  - is integration tests
* sys  - end to end system tests
* all  - unit :: int :: sys
