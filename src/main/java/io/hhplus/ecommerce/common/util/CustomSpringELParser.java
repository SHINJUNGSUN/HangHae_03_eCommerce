package io.hhplus.ecommerce.common.util;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class CustomSpringELParser {

    public static Object getDynamicValue(String[] parameterNames, Object[] args, String key) {

        ExpressionParser expressionParser = new SpelExpressionParser();
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            standardEvaluationContext.setVariable(parameterNames[i], args[i]);
        }

        return expressionParser.parseExpression(key).getValue(standardEvaluationContext, Object.class);
    }
}
