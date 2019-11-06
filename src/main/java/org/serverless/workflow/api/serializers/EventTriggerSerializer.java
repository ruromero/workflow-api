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

package org.serverless.workflow.api.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.serverless.workflow.api.events.EventTrigger;

public class EventTriggerSerializer extends StdSerializer<EventTrigger> {

    public EventTriggerSerializer() {
        this(EventTrigger.class);
    }

    protected EventTriggerSerializer(Class<EventTrigger> t) {
        super(t);
    }

    @Override
    public void serialize(EventTrigger triggerEvent,
                          JsonGenerator gen,
                          SerializerProvider provider) throws IOException {

        // serialize after setting default bean values...
        BeanSerializerFactory.instance.createSerializer(provider,
                                                        TypeFactory.defaultInstance().constructType(EventTrigger.class)).serialize(triggerEvent,
                                                                                                                                   gen,
                                                                                                                                   provider);
    }
}