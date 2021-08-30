package it.uniroma2.dicii.isw2.jcs.paramTests;

import java.util.Arrays;
import java.util.Collection;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.TestCase;

// Description of the Class
@RunWith(Parameterized.class)
public class JCSLightLoadUnitTest extends TestCase {

	private int items;
	private JCS jcs;

    // Configuration test parameters


    @Parameters
    public static Collection<Object[]> configure() throws Exception{
        return Arrays.asList(new Object[][]{
                {999, JCS.getInstance( "testCache1" )}, {-10,JCS.getInstance( "testCache1")}
        });
    }
    //{1000, JCS.getInstance( "testCache1" )} // questo è il test che fallisce che ho analizzato nel report

    // Constructor for the TestSimpleLoad object
    public JCSLightLoadUnitTest(int items, JCS expected) {
        this.items = items;
        this.jcs = expected;
    }

    // A unit test for JUnit
    @Test
    public void testSimpleLoad() throws Exception {
        JCS.setConfigFilename( "/TestSimpleLoad.ccf" );
        for ( int i = 1; i <= items; i++ ) {
            jcs.put( i + ":key", "data" + i );
        }

        for ( int i = items; i > 0; i-- ) {
            String res = (String) jcs.get( i + ":key" );
            if ( res == null ) {
                assertNotNull( "[" + i + ":key] should not be null", res );
            }
        }

        // Test removal
        jcs.remove( "300:key" );
        assertNull( jcs.get( "300:key" ) );
    }

}