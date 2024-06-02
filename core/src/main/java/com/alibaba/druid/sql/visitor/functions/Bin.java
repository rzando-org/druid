/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.visitor.functions;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.alibaba.druid.sql.visitor.SQLEvalVisitor;

import static com.alibaba.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Bin implements Function {
    public static final Bin instance = new Bin();

    public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
        if (x.getArguments().size() != 1) {
            return SQLEvalVisitor.EVAL_ERROR;
        }

        SQLExpr param0 = x.getArguments().get(0);
        param0.accept(visitor);

        Object param0Value = param0.getAttributes().get(EVAL_VALUE);
        if (param0Value == null) {
            return SQLEvalVisitor.EVAL_ERROR;
        }

        if (param0Value instanceof Number) {
            long longValue = ((Number) param0Value).longValue();
            String result = Long.toString(longValue, 2);
            return result;
        }
        return SQLEvalVisitor.EVAL_ERROR;
    }
}
