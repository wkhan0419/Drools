package com.junitTesting;

import static org.junit.Assert.*;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Before;
import org.junit.Test;

import com.beans.Person;

public class TestCases 
{
	 private KnowledgeBase kbase;
	private Object Person;
	
	    
	
	@SuppressWarnings("restriction")
	@Before
	 
	  public void setup() {
	 
	   KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
	 
	   kbuilder.add(ResourceFactory.newClassPathResource("Sample.drl"), ResourceType.DRL);
	 
	   KnowledgeBuilderErrors errors = kbuilder.getErrors();
	 
	   if (errors.size() > 0) {
	 
	    for (KnowledgeBuilderError error: errors) {
	 
	     System.err.println(error);
	 
	    }
	 
	    throw new IllegalArgumentException("Could not parse knowledge.");
	 
	   }
	 
	   kbase = KnowledgeBaseFactory.newKnowledgeBase();
	 
	   kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
	 
	        
	 
	     
	 
	  }
	 
	    
	 
	  @SuppressWarnings("restriction")
	  @Test
	 
	  public void testBasic() {
	 
	   
	 
	   StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
	 
	   KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
	 
	   
	 
	   Person p1 = new Person();
	 
	   p1.setAge(22);
       p1.setName("akash");
       p1.setSalary(1266.1);
       p1.setCitizenship("usa");
	   ksession.insert(p1);
	 
	   int x= ksession.fireAllRules();
	 
	   for (Object o: ksession.getObjects()) {
	 
	    if(o instanceof Person) 
	    {
	    assertTrue(Person.equals(((Person) p1).getAge()));
	    assertTrue(Person.equals(((Person)p1).getName()));
	  
	    assertTrue(Person.equals(((Person) p1).getSalary()));
	    assertTrue(Person.equals(((Person) p1).getCitizenship()));
	    }
	 
	   }
	 
	   logger.close();
	 
	     
	 
	  }
	 
	  
	 
	 

}
