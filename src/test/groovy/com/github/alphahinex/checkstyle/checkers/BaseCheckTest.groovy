package com.github.alphahinex.checkstyle.checkers

import com.puppycrawl.tools.checkstyle.Checker
import com.puppycrawl.tools.checkstyle.DefaultConfiguration
import com.puppycrawl.tools.checkstyle.api.CheckstyleException

class BaseCheckTest {

    protected static Checker prepareCheckStyleChecker(Class clz, Map<String, String> attrs) throws CheckstyleException {
        Checker checker = new Checker()
        checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader())
        checker.configure(prepareConfiguration(clz, attrs))
        return checker
    }

    private static DefaultConfiguration prepareConfiguration(Class clz, Map<String, String> attrs) {
        DefaultConfiguration checks = new DefaultConfiguration("Checks")
        DefaultConfiguration treeWalker = new DefaultConfiguration("TreeWalker")
        DefaultConfiguration checker = new DefaultConfiguration(clz.getCanonicalName())
        for (Map.Entry<String, String> entry : attrs.entrySet()) {
            checker.addAttribute(entry.getKey(), entry.getValue())
        }
        checks.addChild(treeWalker)
        treeWalker.addChild(checker)
        return checks
    }

    protected static List<File> prepareFilesToBeChecked() {
        [
                getFile("PassController.java"),
                getFile("NotPassController.java"),
                getFile("BeFiltered.java"),
                getFile("NoSwaggerAnnotation.java"),
                getFile("WithNoMethod.java")
        ]
    }

    private static File getFile(String name) {
        URL testFileUrl = BaseCheckTest.class.getResource(name)
        return new File(testFileUrl.getFile())
    }
}
