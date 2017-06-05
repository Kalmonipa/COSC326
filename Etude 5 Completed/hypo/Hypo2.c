#include <stdio.h>
#include <math.h>

float hyp(float x, float y){
    return sqrtf((x*x) + (y*y));
}


float hyp2(float x, float y) {
    float a = fabsf(x), b = fabsf(y);
    if(a > b){
        b = b/a;
        return sqrtf(1.0f + b*b) * a;
    }
    else if(a < b){
        a = a/b;
        return sqrt(1.0f + a*a) * b;
    }
    else {
        return a * sqrtf(2.0f);
    }
}


int main(void) {
	float x = 3.0f, y = 4.0f, z = 5.0f;
       
	for (int i = 0; i < 20; i++) {
		float e = fabsf(hyp2(x, y) - z)/z;
		printf("%2d. %e\n", i, e);
		x *= 10.0f, y *= 10.0f, z *= 10.0f;
	}
        return 0;
}
