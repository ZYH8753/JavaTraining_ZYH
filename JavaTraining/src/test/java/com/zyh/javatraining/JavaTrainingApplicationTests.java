package com.zyh.javatraining;

import com.zyh.javatraining.weekfive.musttwo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaTrainingApplicationTests {

	@Autowired
	public Student student;

	@Test
	void contextLoads() {
		System.out.println(student.toString());
	}

}
