# isis-wicket-svg #

[![Build Status](https://travis-ci.org/isisaddons/isis-wicket-svg.png?branch=master)](https://travis-ci.org/isisaddons/isis-wicket-svg)

This component, intended for use with [Apache Isis](http://isis.apache.org)'s Wicket viewer, renders SVG documents as interactive maps.  

## How to run the Demo App ##

The prerequisite software is:

* Java JDK 7 (nb: Isis currently does not support JDK 8)
* [maven 3](http://maven.apache.org) (3.2.x is recommended).

To build the demo app:

    git clone https://github.com/isisaddons/isis-wicket-svg.git
    mvn clean install

To run the demo app:

    mvn antrun:run -P self-host
    
Then log on using user: `sven`, password: `pass`


## API & Usage ##

### Interactive Map ###


## How to configure/use ##

You can either use this component "out-of-the-box", or you can fork this repo and extend to your own requirements.

To use "out-of-the-box", add the component to your project's `dom` module's `pom.xml`:

    <dependency>
        <groupId>org.isisaddons.wicket.svg</groupId>
        <artifactId>isis-wicket-svg-cpt</artifactId>
        <version>1.7.0</version>
    </dependency>

Check for later releases by searching [Maven Central Repo](http://search.maven.org/#search|ga|1|isis-wicket-svg-cpt).

If instead you want to extend these components' functionality, then we recommend that you fork this repo.  The repo is 
structured as follows:

* `pom.xml    ` - parent pom
* `cpt        ` - the component implementation
* `fixture    ` - fixtures, holding a sample domain objects and fixture scripts
* `webapp     ` - demo webapp (see above screenshots)

Only the `cpt` project (and its submodules) is released to Maven central.  The versions of the other modules 
are purposely left at `0.0.1-SNAPSHOT` because they are not intended to be released.


## Change Log ##


## Legal Stuff ##


#### License ####

    Copyright 2014 Jeroen van der Wal, Dan Haywood, Martin Grigorov

    Licensed under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

#### Dependencies ####

In addition to Apache Isis, this component depends on:

* `Apache Batik'
* ...

##  Maven deploy notes ##

Only the `cpt` module is deployed, and is done so using Sonatype's OSS support (see 
[user guide](http://central.sonatype.org/pages/apache-maven.html)).

#### Release to Sonatype's Snapshot Repo ####

To deploy a snapshot, use:

    pushd cpt
    mvn clean deploy
    popd

The artifacts should be available in Sonatype's 
[Snapshot Repo](https://oss.sonatype.org/content/repositories/snapshots).

#### Release to Maven Central ####

The `release.sh` script automates the release process.  It performs the following:

* performs a sanity check (`mvn clean install -o`) that everything builds ok
* bumps the `pom.xml` to a specified release version, and tag
* performs a double check (`mvn clean install -o`) that everything still builds ok
* releases the code using `mvn clean deploy`
* bumps the `pom.xml` to a specified release version

For example:

    sh release.sh 1.7.0 \
                  1.7.1-SNAPSHOT \
                  dan@haywood-associates.co.uk \
                  "this is not really my passphrase"
    
where
* `$1` is the release version
* `$2` is the snapshot version
* `$3` is the email of the secret key (`~/.gnupg/secring.gpg`) to use for signing
* `$4` is the corresponding passphrase for that secret key.

Other ways of specifying the key and passphrase are available, see the `pgp-maven-plugin`'s 
[documentation](http://kohsuke.org/pgp-maven-plugin/secretkey.html)).

If the script completes successfully, then push changes:

    git push

If the script fails to complete, then identify the cause, perform a `git reset --hard` to start over and fix the issue
before trying again.  Note that in the `dom`'s `pom.xml` the `nexus-staging-maven-plugin` has the 
`autoReleaseAfterClose` setting set to `true` (to automatically stage, close and the release the repo).  You may want
to set this to `false` if debugging an issue.
 
According to Sonatype's guide, it takes about 10 minutes to sync, but up to 2 hours to update [search](http://search.maven.org).

