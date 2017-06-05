#include <math.h>

float heron_area(float a, float c) {
float s = (a+a+c)/2.0f;
return (s-a)*sqrtf(s*(s-c));
}

float baseht_area(float a, float c) {
float d = c/(2.0f*a);
return sqrtf(1.0f - d*d)*a*c*0.5f;
}

int main(void) {
	for (float c = 1.0f; c < 10.0E19f; c++) {
		if(heron_area((float)c, 1.0f) == 0.0){
			printf(c);
			break;
		}
	}
	return 0;
}