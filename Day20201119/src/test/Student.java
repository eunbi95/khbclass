package test;

import java.io.Serializable;

public class Student extends Object implements Serializable {
	private String irum;
	private String nai;

	
	public Student() { //持失切 切疑持失
		super();
	}
	public Student(String irum, String nai) {
		super();
		this.irum = irum;
		this.nai = nai;
	}
	@Override
	public String toString() {
		return irum+nai;
	}
	public String getIrum() {
		return irum;
	}
	public void setIrum(String irum) {
		this.irum = irum;
	}
	public String getNai() {
		return nai;
	}
	public void setNai(String nai) {
		this.nai = nai;
	}
}
