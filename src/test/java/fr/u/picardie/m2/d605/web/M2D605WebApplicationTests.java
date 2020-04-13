package fr.u.picardie.m2.d605.web;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class M2D605WebApplicationTests {

	@Test
	void contextLoads() {
		String examplePath = "D:\\sim";
		
		File inputWar = new File(examplePath);
		System.out.println(inputWar.isFile());
		System.out.println(inputWar.exists());
	}
	

}
