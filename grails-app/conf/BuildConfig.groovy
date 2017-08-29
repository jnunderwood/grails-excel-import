grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
	// inherit Grails' default dependencies
	inherits("global") {
		// uncomment to disable ehcache
		// excludes 'ehcache'
	}

	log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	repositories {
        mavenRepo name: "erst-repository", root: "https://dev.erst.dk/artifactory/erst-repository"
        mavenLocal()
	}

	dependencies {
		// specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
		compile(group: 'org.apache.poi', name: 'poi', version: '3.16');
		//xlxs file support
		compile(group: 'org.apache.poi', name: 'poi-ooxml', version: '3.16') {
			excludes 'xmlbeans'
		}
		compile 'org.apache.poi:ooxml-schemas:1.3'
		//compile group:'org.apache.poi', name:'poi-contrib', version:'3.7'
		//compile group:'org.apache.poi', name:'poi-scratchpad', version:'3.7' //ppt, word, visio, outlook support
	}

	plugins {
		build ":release:3.0.1"
		build ":tomcat:7.0.53"

		compile(":hibernate:3.6.10.15") {
			export = false
		}
	}
}

grails.project.repos.default = "erstcomponents"
