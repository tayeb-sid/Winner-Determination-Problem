package pkg1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//parametres
		int nbArticles=4;
		int nbEncheres=5;
		int crossoverPoint=2;
		double mutationRate=0.5;
		int taillePopulationInitiale=10;
		int maxIterations=10;
		//instance aleatoire
		Wdp wdp=new Wdp(nbArticles,nbEncheres);
		System.out.println(wdp);
		System.out.println("representation matricielle");
		wdp.printMatrix();
		System.out.println("*****");
		
		
		/*System.out.println("***population intitiale***");
		ArrayList<int[]>population=wdp.initialPopulation(taillePopulationInitiale);
		wdp.printPopulation(population);
		System.out.println("***crossover***");
		int[]p1=population.get(0);
		int[]p2=population.get(1);
		System.out.println("parent 1"+Arrays.toString(p1)+" fitness: "+wdp.fitness(p1));
		System.out.println("parent 2"+Arrays.toString(p2)+" fitness: "+wdp.fitness(p2));
		ArrayList<int[]>children=wdp.crossover(p1, p2, crossoverPoint);
		int[]c1=children.get(0);
		int[]c2=children.get(1);
		System.out.println("child1 "+Arrays.toString(c1)+wdp.isValidSolution(c1)+" fitness: "+wdp.fitness(c1));
		System.out.println("child2 "+Arrays.toString(c2)+wdp.isValidSolution(c2)+" fitness: "+wdp.fitness(c2));
		System.out.println("***mutation***");
		int[] mutatedChild1=wdp.mutation(c1, mutationRate);
		int[] mutatedChild2=wdp.mutation(c2, mutationRate);
		System.out.println("mutatedChild1 "+Arrays.toString(mutatedChild1)+wdp.isValidSolution(c1)+" fitness: "+wdp.fitness(mutatedChild1));
		System.out.println("mutatedChild2 "+Arrays.toString(mutatedChild2)+wdp.isValidSolution(c2)+" fitness: "+wdp.fitness(mutatedChild2));
		*/
		//genetic algorithm complete execution
		int[]bestSol=wdp.GeneticAlgorithm(maxIterations, taillePopulationInitiale, crossoverPoint, mutationRate);
		System.out.println(wdp.describeSolution(bestSol));
	}

}
