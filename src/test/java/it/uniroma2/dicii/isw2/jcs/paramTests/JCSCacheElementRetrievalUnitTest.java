package it.uniroma2.dicii.isw2.jcs.paramTests;

import org.apache.jcs.JCS;
import org.apache.jcs.engine.behavior.ICacheElement;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import junit.framework.TestCase;
import java.util.Arrays;
import java.util.Collection;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

@RunWith(Parameterized.class)
public class JCSCacheElementRetrievalUnitTest extends TestCase {

   private JCS jcs;
   private String name;
   private String obj;

    @Parameters
    public static Collection<Object[]> configure() throws Exception{
        return Arrays.asList(new Object[][]{
                //proviamo anche stringhe vuote per vedere se il test passa
                {JCS.getInstance( "testCache1" ), "test_key" , "test_data"},{JCS.getInstance( "testCache1" ), "" , ""}
        });
    }

    public JCSCacheElementRetrievalUnitTest(JCS jcs, String name, String obj){
         this.jcs = jcs;
         this.name = name;
         this.obj = obj;
    }

    @Test
    public void testSimpleElementRetrieval()throws Exception
    {
        jcs.put(name, obj);
        long now = System.currentTimeMillis();
        ICacheElement elem = jcs.getCacheElement(name);
        assertEquals( "Name wasn't right", "testCache1", elem.getCacheName() );
        long diff = now - elem.getElementAttributes().getCreateTime();
        assertTrue( "Create time should have been at or after the call", diff >= 0 );
    }


}
