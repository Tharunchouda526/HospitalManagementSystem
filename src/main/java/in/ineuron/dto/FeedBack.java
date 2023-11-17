package in.ineuron.dto;

public class FeedBack 
{
   private Integer number;
   private  String name;
   private  String email;
   private String message;
public Integer getNumber() {
	return number;
}
public void setNumber(Integer number) {
	this.number = number;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
@Override
public String toString() {
	return "FeedBack [number=" + number + ", name=" + name + ", email=" + email + ", message=" + message + "]";
}


}
  