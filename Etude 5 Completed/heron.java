public class heron{
	
	public static float heron_area(float a, float c) {
		float s = (a+a+c)/2.0f;
		return (float)Math.sqrt(s*(s-c)) * (s-a);
	}

	public static float baseht_area(float a, float c) {
		float d = c/(2.0f*a);
		return (float)Math.sqrt(1.0f - d*d)*a*c*0.5f;
	}

	public static void main(String[] args) {
		for (float c = 1.0f; c < 10.0E19f; c++) {
			if(heron_area(c, 1.0f) == 0.0){
				System.out.println(c);
				break;
			}
		}
		System.out.println("Printing 10^28 for baseht_area: " + baseht_area(10.0E28f, 1.0f));
		System.out.println("HERON_AREA: " + heron_area(10.0E19f, 1.0f));
		System.out.println("BASEHT_AREA: " + baseht_area(10.0E19f, 1.0f));
	}
}