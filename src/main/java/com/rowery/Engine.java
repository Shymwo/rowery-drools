package com.rowery;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
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
	private static ArrayList<String> PROPS = new ArrayList<String>();
	private static ArrayList<String> ADDONS = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	public Engine(String filename,StatefulKnowledgeSession ksession){
		ks = ksession;
		Yaml yaml = new Yaml();
		 try {
		 map = (Map<String, Map<String, Object>>)yaml.load((InputStream)(new FileInputStream(new File(filename))));

		  } catch (FileNotFoundException e) {
	          e.printStackTrace();
	        }
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashMap<String, String> getAnswers() {
		return answers;
	}

	public void setAnswers(HashMap<String, String> answers) {
		this.answers = answers;
	}

	@SuppressWarnings("unchecked")
	public static void Question(String name){

		Map<String, Object> entry = map.get(name);
		ShowQuestion( (String)(entry.get("fact_name")),
				(String)(entry.get("type")),
				(String)(entry.get("text")),
				(HashMap<Integer, String>)(entry.get("answers")));

	}

	@SuppressWarnings("unchecked")
	private static void ShowQuestion(String fact, String type, String text,HashMap<Integer, String> ans) {

		ArrayList<String> answer = new ArrayList<String>();
		ArrayList<Integer> labels = new ArrayList<Integer>();

		for (Integer key : ans.keySet()) {
			answer.add(ans.get(key));

			labels.add(key);
		}

		@SuppressWarnings("rawtypes")
		JList list = new JList(answer.toArray());
		 if(type.equals("single"))
			 list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		 JButton button = new JButton();
		 button.setText("Zamknij program");
		 button.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e)
	            {
	                System.exit(0);
	            }
	        });

		 Object[] options = {text, " ", list, button};
		 JOptionPane.showMessageDialog(null, options, "Zaznacz", JOptionPane.PLAIN_MESSAGE);

		while (list.getSelectedIndices().length < 1 && type.equals("single")) {
			options = new Object[] {text, " ", "Zaznacz odpowiedź!", " ", list, button };
			JOptionPane.showMessageDialog(null, options, "Zaznacz", JOptionPane.PLAIN_MESSAGE);
		}

	    for(Integer x : list.getSelectedIndices()){
	    	ks.insert(new Fact(fact, ans.get(x)));

	    }

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void ShowAnswer() {
		Map<String, Object> entry = map.get("answer");
		String text = (String)(entry.get("text"));
		String text2 = (String)(entry.get("text2"));
		String text3 = (String)(entry.get("text3"));

		if (ADDONS.size()==0) text3 = "";
		if (PROPS.size()==0) text2 = "";

		JList list = new JList(ANSWERS.toArray());
		JList list2 = new JList(PROPS.toArray());
		JList list3 = new JList(ADDONS.toArray());
		list.setEnabled(false);
		list2.setEnabled(false);
		list3.setEnabled(false);
		Object[] options = {text, list, text2, list2, text3, list3};
		JOptionPane.showMessageDialog(null, options, "Odpowiedź", JOptionPane.PLAIN_MESSAGE);
	}

	public static void Answer(String name){
		ANSWERS.add(name);
	}

	public static void Propose(String name) {
		PROPS.add(name);
	}

	public static void Addons(String name) {
		ADDONS.add(name);
	}

}