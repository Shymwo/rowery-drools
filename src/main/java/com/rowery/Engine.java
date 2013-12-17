package com.rowery;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import org.drools.runtime.StatefulKnowledgeSession;
import org.yaml.snakeyaml.Yaml;

public class Engine {
	private String name;
	private String fact;
	private String text;
	private String type;
	private HashMap<String, String> answers;
	private static StatefulKnowledgeSession ks;
	private static Map<String, Map<String, Object>> map;
	private static ArrayList<String> ANSWERS = new ArrayList<String>();
	
	public Engine(String filename,StatefulKnowledgeSession ksession){
		ks = ksession;
		Yaml yaml = new Yaml();
		 try {
		 map = (Map<String, Map<String, Object>>)yaml.load((InputStream)(new FileInputStream(new File(filename))));

		  } catch (FileNotFoundException e) {
	          e.printStackTrace();
	        }
	}
	
	private String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	private String getFact() {
		return fact;
	}
	private void setFact(String fact) {
		this.fact = fact;
	}
	private String getText() {
		return text;
	}
	private void setText(String text) {
		this.text = text;
	}
	private String getType() {
		return type;
	}
	private void setType(String type) {
		this.type = type;
	}
	private HashMap<String, String> getAnswers() {
		return answers;
	}
	private void setAnswers(HashMap<String, String> answers) {
		this.answers = answers;
	}
	
	public static void Question(String name){
	      
		          for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
		        	  if(entry.getKey().equals(name)){
		        		  ShowQuestion( (String)(entry.getValue().get("fact_name")),
        		                  (String)(entry.getValue().get("type")),
        		                  (String)(entry.getValue().get("text")),
        		                  (HashMap<String, String>)(entry.getValue().get("answers")));
		        	 
		        	  }
		 
		          }
		          
		    
		
	}
	private static void ShowQuestion(String fact, String type, String text,HashMap<String, String> ans){
		
		ArrayList<String> answer = new ArrayList<String>();
		ArrayList<String> labels = new ArrayList<String>();
	
		for (String key : ans.keySet()) {
			answer.add(ans.get(key));
	
			labels.add(key);
		}


		 JList list = new JList(answer.toArray());
		 if(type.equals("single"))  
			 list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		 
			 
		    Object[] options = {text, " ", list};
		    while (list.getSelectedIndices().length < 1) {
		    	JOptionPane.showMessageDialog(null, options, "Zaznacz", JOptionPane.PLAIN_MESSAGE);
		    	options = new Object[] {text, " ", "Zaznacz przynajmniej jedną!", " ", list };
		    }
		    
		    for(int x : list.getSelectedIndices()){
		    	ks.insert(new Fact(fact, x));

		    }
		

		
		
	}
	
	

	
	public void ShowAnswer(){
		String text ="";
		  for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
        	  if(entry.getKey().equals("answer")){
   
		           text=       (String)(entry.getValue().get("text"));
		       
        	  }
 
          }

		 JList list = new JList(ANSWERS.toArray());

		    Object[] options = {text, " ", list};
	
		    	JOptionPane.showMessageDialog(null, options, "Zaznacz", JOptionPane.PLAIN_MESSAGE);
		    

		
	}
	
	
	public static void Answer(String name){
		ANSWERS.add(name);
		
	}

	
	
	
	
}


