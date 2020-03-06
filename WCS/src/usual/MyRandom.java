package usual;

public class MyRandom {

	
	public static Object oneOrTwo(Object obj1, Object obj2) {
		int value = (int) Math.random();
		if(value==1) 
			return obj1;
		return obj2;
	}
	
	public static float getFloatIntoMinMax(float min, float max) {
		return (float) min+ (int)(Math.random() * ((max - min) + 1));
	}
	
	
	public static int getIntIntoMinMax(int min, int max) {
		return min+ (int)(Math.random() * ((max - min) + 1));
	}
		
}