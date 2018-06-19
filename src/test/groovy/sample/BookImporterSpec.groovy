package sample

import org.grails.plugins.excelimport.ExcelImportService
import org.grails.plugins.excelimport.ImportSeverityLevelEnum
import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class BookImporterSpec extends Specification implements GrailsUnitTest {
    Closure doWithSpring() {
        return {
            excelImportService ExcelImportService
            bookImporter BookImporter
        }
    }

    BookImporter bookImporterInstance

    def setup() {
        bookImporterInstance = applicationContext.bookImporter
        InputStream bookDataInputStream = BookImporter.getResourceAsStream('/test-data/books.xls')
        bookImporterInstance.readFromStream(bookDataInputStream)
    }

    void 'test load data from columns'() {
        when:
        def booksMapList = bookImporterInstance.getBooks()

        then:
        booksMapList.size() == 3
        booksMapList[0].title == 'How to Win Friends and Influence People'
        booksMapList[1].title == 'Oliver Twist'
        booksMapList[2].title == 'War and Peace'
    }

    void 'test load data from cells'() {
        when:
        def singleBook = bookImporterInstance.getOneMoreBookParams()

        then:
        singleBook.title == 'Romeo & Juliet'
        singleBook.author == 'Shakespeare'
        singleBook.dateIssued
        singleBook.dateIssuedError
    }

    void "test error reporter"() {
        when:
        bookImporterInstance.getBooks()

        then:
        bookImporterInstance.cellReporter.messagesBySeverityLevel?.size()==3
        bookImporterInstance.cellReporter.messagesBySeverityLevel[ImportSeverityLevelEnum.Error].isEmpty() == false
    }
}
