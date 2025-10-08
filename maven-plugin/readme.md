## Sundrio: Maven Plugin.

The sundrio maven plugin can be used to generate BOMs for multi-module projects and provides experimental continuous testing capabilities.

### Features
* Generate BOMs for multi-module projects
* Experimental continuous testing capabilities

### Generating a BOM.

With the strict use of the term a BOM is a pom, which contains references to the project artifacts (bill of material).
In many cases a may BOM provide references to additional artifacts, like important dependencies, overrides etc. Also its
not rare that a project may provide multiple BOMs with variable granularity.

The base configuration options are the following:

| Option                | Description                                                                       |
|-----------------------|------------------------------------------------------------------------------------
| **bomTemplateUrl**    | The URL to the velocity template that should used for BOM generation              |
| **boms**              | A list of bom configuration                                                       |


Using custom template for my BOMs
---

Each project has different needs and different requirements. In the unlikely case that the configuration options are not enough,
the plugin does allow you to bring your own velocity template. Here is a link to the [original velocity template](maven-plugin/src/main/resources/templates/bom.xml.vm)

Generating multiple BOMs
---

Since there is no "one size fits all" approach to BOMs, this plugin accepts configurations for multiple BOMs and tries
to be as configurable as possible.

The configuration parameters are the following:

| Option                | Description                                                                       |
|-----------------------|------------------------------------------------------------------------------------
| **artifactId**        |The artifactId of the BOM to be generated                                          |
| **name**              |The name of the BOM to be generated                                                |
| **description**       |The description of the BOM to be generated                                         |
| **modules**           |An artifact set that defines the modules to include/exclude.                       |
| **dependencies**      |An artifact set that defines the dependencies to include/exclude.                  |
| **goals**             |An goal set that defines the goals to execute to the generated project.            |
| **ignoreScope**       |Flag to ignore the artifact scope (in which case compile scope will be assumed).   |
| **excludeOptional**   |Flag to exclude optional artifacts from the BOM.                                   |

Here's an example:

    <plugin>
        <groupId>io.sundr</groupId>
        <artifactId>sundr-maven-plugin</artifactId>
        <version>${sundrio.version}</version>
        <configuration>
            <bomTemplateUrl>file://${project.basedir}/custom.xml.vm</bomTemplateUrl>
            <boms>
                <bom>
                    <artifactId>bom-test-1</artifactId>
                    <name>Test Project :: Bom 1</name>
                    <modules>
                        <includes>
                            <include>*:module1</include>
                        </includes>
                    </modules>
                    <properties>
                        <key1>value1</key1>
                        <key2>value2</key2>
                    </properties>
                </bom>
            </boms>
        </configuration>
    </plugin>

### Continuous Testing (EXPERIMENTAL)

The `continuous-test` goal provides intelligent test execution based on file changes. This is an **experimental feature** that was added primarily as a way to test the limits of the Sundrio model itself and to further push the barriers of what's possible with code analysis.

#### Features

- **Smart Test Execution**: Only runs tests affected by source code changes
- **Real-time File Watching**: Monitors source and test directories for changes
- **Dependency Analysis**: Uses Sundrio's TypeDef model to map test dependencies

#### Basic Usage

```bash
mvn io.sundr:sundr-maven-plugin:continuous-test
```

#### Configuration

```xml
<plugin>
    <groupId>io.sundr</groupId>
    <artifactId>sundr-maven-plugin</artifactId>
    <version>${sundrio.version}</version>
    <configuration>
        <testIncludes>**/*Test.java,**/Test*.java,**/*Tests.java</testIncludes>
        <testGoal>test</testGoal>
    </configuration>
</plugin>
```

**Note**: This feature is experimental and primarily serves as a demonstration of Sundrio's advanced code analysis capabilities. See the [continuous-testing-example](../examples/continuous-testing-example) for a complete working example.
