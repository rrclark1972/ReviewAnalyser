package com.simplilearn.ReviewAnalyser;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewAnalyserApplicationTests {

    private ReviewAnalyserApplication analyser = new ReviewAnalyserApplication();
    @Test	 
    public void testWordCount() {     	 
   	 assertEquals(7,analyser.getWordCount("Train to win in the digital economy"));	 
    }
}
Ï	Save the file and exit the text editor.

Ï	Open the pom.xml and add the following dependency.
<dependency>
   	   <groupId>junit</groupId>
   	   <artifactId>junit-dep</artifactId>
   	   <version>4.8.2</version>
   	   <scope>test</scope>
   	 </dependency>
Ï	Add the jacoco plugin to pom.xml with the following xml code:

 <plugin>
   			 <groupId>org.jacoco</groupId>
   			 <artifactId>jacoco-maven-plugin</artifactId>
   			 <version>0.8.3</version>
   			 <executions>
   			 	<execution>
   				 <id>default-prepare-agent</id>
   				 <goals>
   				 	<goal>prepare-agent</goal>
   				 </goals>
   			 	</execution>
   			 	<execution>
   				 <id>default-report</id>
   				 <phase>prepare-package</phase>
   				 <goals>
   				 	<goal>report</goal>
   				 </goals>
   			 	</execution>       	 
   			 </executions>
   		 </plugin>

Ï	Save the file and exit the text editor.

Step 4: Creating and committing a Jenkinsfile
Ï	Navigate to the ReviewAnalyser root directory where the pom.xml is.
Ï	Open a new text file vi Jenkinsfile and add the following script to it.

pipeline {	 
    agent any	 
   	 stages {     	 
   	 stage("Compile") {          	 
   			 steps {               	 
   				 sh "mvn compile"          	 
   			 }     	 
   		 }     	 
   	 stage("Unit test") {          	 
   		 steps {               	 
   				 sh "mvn test"          	 
   			 }     	 
   		 }	 
   	 }

    	post {
   	 always {
   	 step([$class: 'JacocoPublisher',
   	   	execPattern: 'target/*.exec',
   	   	classPattern: 'target/classes',
   	   	sourcePattern: 'src/main/java',
   	   	exclusionPattern: 'src/test*'
   	 ])
   	 }   
    	}
}

