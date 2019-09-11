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

package org.serverless.workflow.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.serverless.workflow.api.mapper.WorkflowObjectMapper;

public class ObjectMapperTest {

    @Test
    public void testReadTreeToJson() throws Exception {
        WorkflowObjectMapper mapper = new WorkflowObjectMapper();

        JsonNode node = mapper.readTree("{\n" +
                                                "  \"name\": \"test-wf\",\n" +
                                                "  \"states\": [\n" +
                                                "    {\n" +
                                                "      \"status\": \"SUCCESS\",\n" +
                                                "      \"name\": \"test-state\",\n" +
                                                "      \"type\": \"END\",\n" +
                                                "      \"start\": false\n" +
                                                "    }\n" +
                                                "  ]\n" +
                                                "}");
        Assertions.assertNotNull(node);
        Assertions.assertEquals("test-wf",
                                node.get("name").textValue());
    }

    @Test
    public void testReadTreeToWorkflow() throws Exception {
        WorkflowObjectMapper mapper = new WorkflowObjectMapper();

        Workflow workflow = mapper.readValue("{\n" +
                                                     "  \"name\": \"test-wf\",\n" +
                                                     "  \"states\": [\n" +
                                                     "    {\n" +
                                                     "      \"status\": \"SUCCESS\",\n" +
                                                     "      \"name\": \"test-state\",\n" +
                                                     "      \"type\": \"END\",\n" +
                                                     "      \"start\": false\n" +
                                                     "    }\n" +
                                                     "  ]\n" +
                                                     "}",
                                             Workflow.class);

        Assertions.assertNotNull(workflow);
        Assertions.assertEquals("test-wf",
                                workflow.getName());
    }
}