package org.grails.plugins.excelimport

import grails.plugins.Plugin

class ExcelImportGrailsPlugin extends Plugin {

    def grailsVersion = "3.1.10 > *"

    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Excel Import"
    def author = "Jean Barmash, Oleksiy Symonenko, Jeff Scott Brown"
    def authorEmail = ""
    def description = '''\
Excel-Import plugin uses Apache POI [http://poi.apache.org/] library (v 3.6) to parse Excel files.
It's useful for either bootstrapping data, or when you want to allow your users to enter some data using Excel spreadsheets.
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/excel-import"

    def license = "APACHE"

    def organization = [ name: "EnergyScoreCards.com", url: "http://www.energyscorecards.com/" ]

    // Any additional developers beyond the author specified above.
    def developers = [
            [ name: 'Jeff Scott Brown', email: 'brownj@ociweb.com'],
            [ name: 'Jean Barmash', email: "Jean.Barmash@gmail.com"]
    ]

    def issueManagement = [ system: "GITHUB", url: "https://github.com/gpc/grails-excel-import/issues" ]

    def scm = [ url: "https://github.com/gpc/grails-excel-import" ]
}
