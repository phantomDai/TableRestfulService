package cn.edu.ustb.tr.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Column {
	private String name ;
	private String id ;
	private String text ;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Column(){}
	public Column(String name,String id,String text){
		setId(id);
		setName(name);
		setText(text);
	}
}
