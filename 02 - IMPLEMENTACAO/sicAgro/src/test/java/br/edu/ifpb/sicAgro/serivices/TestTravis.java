package br.edu.ifpb.sicAgro.serivices;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Before;
import org.junit.Test;


public class TestTravis {
	
	@Before
	public void setUp(){
		
	}
	

	@Test
	public void ciTravisTest(){
		assertThat(10, equalTo(10));
	}
}
