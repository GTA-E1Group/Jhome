package com.daxu.common.Vo;

import javax.persistence.*;


@Entity 
public  class  StudentEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "id")
	private int id; 
	@Column(name="nameString")
	private String nameString;//����
	@Column(name="age")
	private int age;//����  
	
//	@Column(name="both")//�Ա�
//	private Date both;//��������
	
	public StudentEntity()
	{
		
	} 
	public StudentEntity(int id,String nameString, int age)
	{
		this.id=id;
		this.nameString=nameString;
		this.age=age; 
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
 
	
	//private List<StudentAward> studentAwards;
//	public List<StudentAward> getStudentAwards() {
//		return studentAwards;
//	}
//
//	public void setStudentAwards(List<StudentAward> studentAwards) {
//		this.studentAwards = studentAwards;
//	}
	 
}
