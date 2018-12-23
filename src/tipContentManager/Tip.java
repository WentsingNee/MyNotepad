package tipContentManager;

import java.time.LocalDate;

public class Tip {

	public final int ID;
	String usr;
	String title;
	String content;
	LocalDate createDate;

	public Tip(int ID, String usr, String title, String content) {
		this.ID = ID;
		this.usr = usr;
		this.title = title;
		this.content = content;
		createDate = LocalDate.now();
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

}
