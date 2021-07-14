package it.uniroma2.dicii.isw2.jcs.paramTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.engine.behavior.ICacheElement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import junit.framework.TestCase;

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



/**
 * @author Aaron Smuts
 *
 */
public class JCSCacheElementRetrievalUnitTest
{
	private JCS jcs;
	private String name;
	private String value;

    /**
     *
     * @throws Exception
     */
    	
	
	
    @Before
    public void setUp() throws Exception {
        configure();
    }
    
    
        // Configuration test parameters
    private void configure() throws CacheException {
        jcs = JCS.getInstance( "testCache1" );
        name = "test1";
        value = "val1";
        data(name, value, jcs);
    }
    
    public JCSCacheElementRetrievalUnitTest() {
    }
    
    @Parameters
    public static JCS data(String name, String value, JCS jcs) throws CacheException {
    	jcs.put(name, value);
    	return jcs;
    }


	// A unit test for JUnit
    @Test
    public void testSimpleLoad() throws Exception { 

    	long now = System.currentTimeMillis();
        ICacheElement elem = jcs.getCacheElement( "test1" );
        assertEquals( "Name wasn't right", "val1", elem.getVal() );
        long diff = now - elem.getElementAttributes().getCreateTime();
        assertTrue( "Create time should have been at or after the call", diff >= 0 );
    }    
    
    	
    	

    

}
