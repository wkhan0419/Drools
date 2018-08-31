 package com.sample;

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

import com.beans.Department;
import com.beans.Person;


public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();
            
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            // go !
           Person p1 = new Person();
           p1.setAge(22);
           p1.setName("akash");
           p1.setSalary(1266.1);
           p1.setCitizenship("usa");
           
           Person p2 = new Person();
           p2.setAge(30);
           p2.setName("vijay");
           p2.setSalary(2666.00);
           p2.setCitizenship("india");
           
           Department d1 = new Department();
           d1.setDepname("IT");
            ksession.insert(p1);
            ksession.insert(p2);
            ksession.insert(d1);
            ksession.fireAllRules();
            logger.close();
        } catch(Throwable t) 
        {
            t.printStackTrace();
        }
    }
    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        
        kbuilder.add(ResourceFactory.newClassPathResource("Sample.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) 
        {
            for (KnowledgeBuilderError error: errors)
            {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
       
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
    }
