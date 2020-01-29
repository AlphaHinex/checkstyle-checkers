package io.github.alphahinex.checkstyle.checkers

import com.puppycrawl.tools.checkstyle.Checker
import com.puppycrawl.tools.checkstyle.api.CheckstyleException
import org.junit.Before
import org.junit.Test

class MethodLimitCheckTest extends BaseCheckTest {

    private Checker checker

    private List<File> files

    @Before
    void setUp() throws CheckstyleException {
        checker = prepareCheckStyleChecker(MethodLimitCheck.class, Collections.singletonMap("max", "10"))
        files = prepareFilesToBeChecked()
    }

    @Test
    void test() throws CheckstyleException {
        assert checker.process(files) == 1

        def check = new MethodLimitCheck()
        assert check.getAcceptableTokens() == check.getDefaultTokens()
    }

}
