package invoice;

public class User {
	private String username;
	private String userrole;
	private String billrate;
	public String getBillrate() {
		return billrate;
	}
	public void setBillrate(String billrate) {
		this.billrate = billrate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
}
