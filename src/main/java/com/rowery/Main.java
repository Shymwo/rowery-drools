package com.rowery;

import java.util.ArrayList;
import java.util.List;


import org.drools.KnowledgeBase;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.helper.KnowledgeBaseUtil;

/**
 * This is a sample class to launch a rule.
 */
public class Main {

    public static final void main(String[] args) {

        try {
            // load up the knowledge base
        	List<String> rules = new ArrayList<String>();
        	rules.add("Main.drl");
            KnowledgeBase kbase = KnowledgeBaseUtil.readKnowledgeBase(rules);
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            // go !
          //  ksession.insert(new Fact());
            Engine e = new Engine("yaml/questions.yaml",ksession);
            ksession.fireAllRules();
            logger.close();
            e.ShowAnswer();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}