package sample

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.grails.plugins.excelimport.ExcelImportService
import org.grails.plugins.excelimport.ImportSeverityLevelEnum
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class BookImporterSpec extends Specification {
    static doWithSpring = {
        excelImportService ExcelImportService
        bookImporter BookImporter
    }

    BookImporter bookImporter

    def setup() {
        bookImporter = applicationContext.bookImporter
        def bookDataInputStream = BookImporter.getResourceAsStream('/test-data/books.xls')
        bookImporter.read(bookDataInputStream)
    }

    void 'test load data from columns'() {
        when:
        def booksMapList = bookImporter.getBooks()

        then:
        booksMapList.size() == 3
        booksMapList[0].title == 'How to Win Friends and Influence People'
        booksMapList[1].title == 'Oliver Twist'
        booksMapList[2].title == 'War and Peace'
    }

    void 'test load data from cells'() {
        when:
        def singleBook = bookImporter.getOneMoreBookParams()

        then:
        singleBook.title == 'Romeo & Juliet'
        singleBook.author == 'Shakespeare'
        singleBook.dateIssued
        singleBook.dateIssuedError
    }

    void "test error reporter"() {
        when:
        bookImporter.getBooks()

        then:
        bookImporter.cellReporter.messagesBySeverityLevel?.size()==3
        bookImporter.cellReporter.messagesBySeverityLevel[ImportSeverityLevelEnum.Error].isEmpty() == false
    }
}
