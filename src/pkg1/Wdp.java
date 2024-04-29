package pkg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Wdp {
	ArrayList<Article>listeArticles;
	ArrayList<Enchere>listeEncheres;
	int matrix[][];
	Random random=new Random();
	Comparator<int[]> fitnessComparator = (s1, s2) -> Integer.compare(this.fitness(s2), this.fitness(s1));

	//random instance
	Wdp(int nbArticles,int nbEncheres){
		listeArticles=new ArrayList<Article>();
		 for (int i = 1; i <= nbArticles; i++)this.listeArticles.add(new Article(i));
		 listeEncheres=new ArrayList<Enchere>();
		 for (int i = 1; i <= nbEncheres; i++) listeEncheres.add(new Enchere(i,listeArticles));
		 this.matrix=this.matrixFormat();
	}
	public String toString() {
		return "Articles: \n "+this.listeArticles.toString()+"\n Encheres: \n"+this.listeEncheres.toString();
	}
	public int[][] matrixFormat(){
		int[][] matrix=new int[listeEncheres.size()][listeArticles.size()];
		for(Article article:this.listeArticles) {
			for(Enchere enchere:this.listeEncheres) {
				for(Article a:enchere.getListeArticles()) {
					if(a.getNum()==article.getNum()) {
						matrix[enchere.getNum()-1][article.getNum()-1]=1;
					}
				}
			}
		}
		return matrix;
	}
	public void printMatrix() {
		for(int[] line:this.matrix) {
			System.out.println(Arrays.toString(line));
		}
	}
	public boolean isValidSolution(int[] solution) {
		int[][]m=new int[solution.length][listeArticles.size()];
		for(int i=0;i<solution.length;i++) {
			if(solution[i]==1) {
				m[i]=this.matrix[i];
			}
		}

	
		for(int i=0;i<listeArticles.size();i++) {
			int sum=0;
			for(int j=0;j<solution.length;j++) {
				sum+=m[j][i];
				if(sum>1)return false;
			}		
		}
		
		return true;
	}
	public int sumArray(int[]arr) {
		int sum=0;
		for(int i=0;i<arr.length;i++)sum+=arr[i];
		return sum;
	}
	public int[] generateRandomSolution() {
		int [] sol=new int[listeEncheres.size()];
		do {
			for (int i = 0; i < listeEncheres.size(); i++)sol[i] = random.nextInt(2);
		}while(!isValidSolution(sol)||sumArray(sol)==0);
		return sol;
	}
	public int fitness(int[]solution) {
		int revenu=0;
		for(int i=0;i<solution.length;i++) {
			if(solution[i]==1) {
				revenu+=this.listeEncheres.get(i).getPrixTotal();
				
			}
		}
		return revenu;
	}
	
	public ArrayList<int[]>initialPopulation(int n){
		ArrayList<int[]>population=new ArrayList<int[]>();
		for(int i=0;i<n;i++)population.add(generateRandomSolution());
		Collections.sort(population,fitnessComparator);
		return population;
	}
	
  public  ArrayList<int[]> crossover(int[] p1, int[] p2, int crossoverPoint) {
	        ArrayList<int[]> children = new ArrayList<int[]>();
	        int[] child1 = new int[p1.length];
	        int[] child2 = new int[p1.length];

	        for (int i = 0; i < crossoverPoint; i++) {
	            child1[i] = p1[i];
	            child2[i] = p2[i];
	        }

	        for (int i = crossoverPoint; i < p1.length; i++) {
	            int[] temp1 = child1.clone();
	            int[] temp2 = child2.clone();
	            temp1[i] = p2[i];
	            temp2[i] = p1[i];
	            if (isValidSolution(temp1)) {
	                child1 = temp1;
	            }
	            if (isValidSolution(temp2)) {
	                child2 = temp2;
	            }
	        }

	        children.add(child1);
	        children.add(child2);
	        return children;
	    }
	
  public int[]mutation(int[] solution,double mutationRate){
	  int [] mutatedSolution=new int[solution.length];
	  int nbMutations=(int) Math.round(mutationRate*solution.length);
		 for(int i=0;i<nbMutations;i++) {	 
			 int randomIndex=random.nextInt(solution.length);
			 mutatedSolution[randomIndex] = 1 - mutatedSolution[randomIndex];
			 if(!isValidSolution(mutatedSolution)||sumArray(mutatedSolution)==0)mutatedSolution[randomIndex] = 1 - mutatedSolution[randomIndex];

		 }
	  return mutatedSolution;
  }
	
	
  public int[] GeneticAlgorithm(int maxIter,int taillePopulation,int crossoverPoint,double mutationRate) {
	  ArrayList<int[]>population=initialPopulation(taillePopulation);
	  System.out.println("**initial population**");
	  this.printPopulation(population);
	  for(int i=0;i<maxIter;i++) {
			//selection elitiste
		  	int[]p1=population.get(0);
			int[]p2=population.get(1);
			//crossover
			ArrayList<int[]>children=crossover(p1, p2, crossoverPoint);
			int[]c1=children.get(0);
			int[]c2=children.get(1);
			//mutation
			ArrayList<int[]>mutatedChildren=new ArrayList<int[]>();
			int[] mutatedChild1=mutation(c1, mutationRate);
			int[] mutatedChild2=mutation(c2, mutationRate);
			mutatedChildren.add(mutatedChild1);
			mutatedChildren.add(mutatedChild2);
			population.clear();
			population.addAll(children);
			population.addAll(mutatedChildren);
			Collections.sort(population,fitnessComparator);
	  }
	  System.out.println("**final population**");
	  this.printPopulation(population);
	  System.out.println("**Best Solution**");
	  System.out.println(Arrays.toString(population.get(0)));
	  return population.get(0);
  }
	public String describeSolution(int[]solution) {
		String description="les gagnants sont : \n";
		for(int i=0;i<solution.length;i++) {
			if(solution[i]==1)description+="*"+this.listeEncheres.get(i).toString()+"\n";
		}
		return description;
	}
	
	
	public void printPopulation(ArrayList<int[]>population) {
		for(int[] line:population) {
			System.out.println(Arrays.toString(line)+" fitness: "+fitness(line));
		}
	}
}
