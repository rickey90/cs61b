public class exercise1a {
	public static void main(String[] args){
		String stars = "*";
		int numStars = 0;
		while (numStars < 5){
			System.out.println(stars);
			stars = stars + "*";
			numStars++;
		}

	}
}