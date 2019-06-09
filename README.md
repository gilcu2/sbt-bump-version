# sbt-bump-version
[ ![Download](https://api.bintray.com/packages/reug/maven/sbt-bump-version/images/download.svg) ](https://bintray.com/reug/maven/sbt-bump-version/_latestVersion)

This plugin provides a task to update the project version.
The versioning approach follows the [SemVer](https://semver.org/) rules.

>Given a version number MAJOR.MINOR.PATCH, increment the:
>
>   - MAJOR version when you make incompatible API changes,
>   - MINOR version when you add functionality in a backward-compatible manner, and
>   - PATCH version when you make backward-compatible bug fixes.
>
>Additional labels for pre-release and build metadata are available as extensions to the MAJOR.MINOR.PATCH format.

## Getting started
Add the following to ./project/plugins.sbt:
```scala
resolvers += Resolver.bintrayRepo("reug", "maven")
addSbtPlugin("com.github.reugn" % "sbt-bump-version" % "<version>")
```
Enable plugin:
```scala
enablePlugins(VersionBumpPlugin)
```

## Usage
- sbt "vbump major"  
- sbt "vbump minor"
- sbt "vbump patch"
- sbt "vbump build"

Optional [identifiers](https://semver.org/#spec-item-9) (SNAPSHOT etc') should be appended manually with further support by a version bump task.

## License
Licensed under the [Apache 2.0 License](./LICENSE.txt).
