package com.github.alphahinex.checkstyle.checkers;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.AnnotationUtil;

import java.util.BitSet;

public class SwaggerAnnotationCheck extends AbstractCheck {

    private static final String ANNOTATION_SWAGGER_API = "Api";
    private static final String ANNOTATION_SWAGGER_OPERATION = "ApiOperation";

    private static final String ANNOTATION_CONTROLLER = "Controller";
    private static final String ANNOTATION_REST_CONTROLLER = "RestController";

    private static final String REQUEST_MAPPING_SUFFIX = "Mapping";

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.CLASS_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        if (!isController(ast)) {
            return;
        }

        if (hasOnClass(ast)) {
            checkMethods(ast);
        }
    }

    private boolean isController(DetailAST ast) {
        return AnnotationUtil.containsAnnotation(ast, ANNOTATION_REST_CONTROLLER) || AnnotationUtil.containsAnnotation(ast, ANNOTATION_CONTROLLER);
    }

    private boolean hasOnClass(DetailAST ast) {
        if (AnnotationUtil.containsAnnotation(ast, ANNOTATION_SWAGGER_API)) {
            return true;
        } else {
            String message = "Failed！There must be swagger annotation '@Api' on the class!";
            log(ast.getLineNo(), message);
            return false;
        }
    }

    /**
     * `--OBJBLOCK -> OBJBLOCK [14:34]
     *     |--LCURLY -> { [14:34]
     *     |--METHOD_DEF -> METHOD_DEF [16:4]
     *     |   |--MODIFIERS -> MODIFIERS [16:4]
     *     |   |   |--ANNOTATION -> ANNOTATION [16:4]
     *     |   |   |   |--AT -> @ [16:4]
     *     |   |   |   |--IDENT -> GetMapping [16:5]
     *     |   |   |   |--LPAREN -> ( [16:15]
     *     |   |   |   |--EXPR -> EXPR [16:16]
     *     |   |   |   |   `--STRING_LITERAL -> "/latest" [16:16]
     *     |   |   |   `--RPAREN -> ) [16:25]
     *     |   |   |--ANNOTATION -> ANNOTATION [17:4]
     *     |   |   |   |--AT -> @ [17:4]
     *     |   |   |   |--IDENT -> ApiOperation [17:5]
     *     |   |   |   |--LPAREN -> ( [17:17]
     *     |   |   |   |--EXPR -> EXPR [17:18]
     *     |   |   |   |   `--STRING_LITERAL ->
     * @param  ast DetailAST
     */
    private void checkMethods(DetailAST ast) {
        // 通过 CLASS_DEF 的树节点获取每一个 METHOD_DEF
        DetailAST firstMethod = ast.findFirstToken(TokenTypes.OBJBLOCK).findFirstToken(TokenTypes.METHOD_DEF);
        for (DetailAST method = firstMethod; method != null && method.getType() == TokenTypes.METHOD_DEF; method = method.getNextSibling()) {
            // 校验带 @RestquestMapping 的 METHOD_DEF 是否存在 swagger 注解
            checkSingleMethod(method);
        }
    }

    private void checkSingleMethod(DetailAST method) {
        if (!method.branchContains(TokenTypes.ANNOTATION)) {
            return;
        }
        BitSet bitSet = new BitSet(2);
        DetailAST firstAnnotation = AnnotationUtil.getAnnotationHolder(method).getFirstChild();
        for (DetailAST anno = firstAnnotation; anno.getType() == TokenTypes.ANNOTATION; anno = anno.getNextSibling()) {
            String annotationName = FullIdent.createFullIdent(anno.getFirstChild().getNextSibling()).getText();
            if (annotationName.endsWith(REQUEST_MAPPING_SUFFIX)) {
                bitSet.set(0);
            } else if (ANNOTATION_SWAGGER_OPERATION.equals(annotationName)) {
                bitSet.set(1);
            }
        }
        boolean isRequestMapping = bitSet.get(0);
        boolean hasSwaggerAnnotation = bitSet.get(1);
        if (isRequestMapping && !hasSwaggerAnnotation) {
            String message = "There must be swagger annotation '@ApiOperation'on the method!";
            log(method.getLineNo(), message);
        }
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

}
