# Release to OSSRH
Important mvn commands to release new SDK version to OSSRH.

Run from root to update version in each pom.xml:
- `mvn versions:set -DnewVersion=XXX`

Run to publish release:
- `mvn clean deploy -P release`

Performing a Snapshot Deployment:
- `mvn clean deploy`

Manually Releasing the Deployment to the Central Repository:
- `mvn nexus-staging:release`


Source: https://central.sonatype.org/publish/publish-maven/