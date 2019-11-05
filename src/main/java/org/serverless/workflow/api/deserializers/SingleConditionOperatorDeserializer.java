/*
 *
 *   Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.serverless.workflow.api.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.serverless.workflow.api.WorkflowPropertySource;
import org.serverless.workflow.api.choices.SingleCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleConditionOperatorDeserializer extends StdDeserializer<SingleCondition.Operator> {

    private WorkflowPropertySource context;
    private static Logger logger = LoggerFactory.getLogger(SingleConditionOperatorDeserializer.class);

    public SingleConditionOperatorDeserializer() {
        this(SingleCondition.Operator.class);
    }

    public SingleConditionOperatorDeserializer(WorkflowPropertySource context) {
        this(SingleCondition.Operator.class);
        this.context = context;
    }

    public SingleConditionOperatorDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SingleCondition.Operator deserialize(JsonParser jp,
                                                DeserializationContext ctxt) throws IOException {

        String value = jp.getText();
        if (context != null) {
            try {
                String result = context.getPropertySource().getProperty(value);

                if (result != null) {
                    return SingleCondition.Operator.fromValue(result);
                } else {
                    return SingleCondition.Operator.fromValue(jp.getText());
                }
            } catch (Exception e) {
                logger.info("Exception trying to evaluate property: " + e.getMessage());
                return SingleCondition.Operator.fromValue(jp.getText());
            }
        } else {
            return SingleCondition.Operator.fromValue(jp.getText());
        }
    }
}