#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <iostream>

using namespace cv;

using namespace std;

/** @function main */
int main(int argc, char** argv) {
	Mat src, dst,dst2;

	src = imread("2.jpg",0);


	threshold(src, dst, 100,0, THRESH_TOZERO); // src içerisinde 100den küçük olanlar 0 yapılır.
	threshold(dst, dst, 200,0, THRESH_TOZERO_INV); // dst içerisinde 200den büyük olanlar 0 yapılır.
	// 100 ile 200 değeri arasında olanlar kalır!


	namedWindow("adaptive", CV_WINDOW_AUTOSIZE);
	imshow("adaptive", dst);


	waitKey(0);

	return 0;
}

