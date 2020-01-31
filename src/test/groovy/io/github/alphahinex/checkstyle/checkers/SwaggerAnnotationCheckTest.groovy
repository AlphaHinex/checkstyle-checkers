package io.github.alphahinex.checkstyle.checkers

import com.puppycrawl.tools.checkstyle.Checker
import com.puppycrawl.tools.checkstyle.api.CheckstyleException
import org.junit.Before
import org.junit.Test

class SwaggerAnnotationCheckTest extends BaseCheckTest {

    private Checker checker

    private List<File> files

    @Before
    void setUp() throws CheckstyleException {
        checker = prepareCheckStyleChecker(SwaggerAnnotationCheck.class, Collections.emptyMap())
        files = prepareFilesToBeChecked()
    }

    @Test
    void test() throws CheckstyleException {
        assert checker.process(files)  == 6

        def check = new SwaggerAnnotationCheck()
        assert check.getAcceptableTokens() == check.getDefaultTokens()
    }

}
