package pkg1;

public class Article {
	int num ,prix;
	public Article (int num) {
		this.num=num;
	}
	public double getPrix() {return this.prix;}
	public int getNum() {return this.num;}
	public void setPrix(int prix) {this.prix=prix;}
	public void setNul(int num) {this.num=num;}
	
	public String toString() {
		String s="";
		if(this.prix!=0) {s=" ("+this.prix+"$)";}
		return "Article_"+this.num+s;
	}
}
