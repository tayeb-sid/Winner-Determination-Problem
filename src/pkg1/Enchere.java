package pkg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Enchere {
    int num;
    List<Article> chosenArticles;
    int  prixTotal;
    Random random=new Random();
    public Enchere(int num,List<Article> chosenArticles) {
    	this.chosenArticles=(ArrayList<Article>) chosenArticles;
    }
  
    //generates a random bid
    public Enchere(int num,ArrayList<Article> AllArticles) {
        this.num = num;
        //the probability that the bidder bids on a bundle
        double bundleProbability=0.6;
        int nbArticles=1 ;
        if(random.nextDouble(1)>bundleProbability)nbArticles = random.nextInt(AllArticles.size())+1;
        chosenArticles=new ArrayList<Article>();
        chosenArticles=chooseRandomArticles(AllArticles,nbArticles);
        int randomPrice= random.nextInt(200)+1;
        this.prixTotal+=randomPrice;
        
        
        
    }
    public static List<Article> chooseRandomArticles(ArrayList<Article> list, int n) {
        ArrayList<Article> shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);
        return shuffledList.subList(0, n);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Article> getListeArticles() {
        return chosenArticles;
    }

    public void setListeArticles(ArrayList<Article> chosenArticles) {
        this.chosenArticles = chosenArticles;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String toString() {
      return "Enchere_" + this.num +" : "+this.chosenArticles.toString()+" Prix Total = " + getPrixTotal()+"$ ("+chosenArticles.size()+" article(s))\n";
    }
}
