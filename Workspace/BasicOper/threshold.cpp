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
	Mat src, dst;

	src = imread("2.jpg");

	cvtColor(src, src, CV_BGR2GRAY);
	threshold(src, dst, 100, 255, 0); // 100den büyük değerleri 255 yapar kalanını 0 yapar. Binary
	threshold(src, dst, 100, 255, 1); // 100den büyük değerleri 0 yapar diğerlerini 255 yapar. Binary Inverted
	threshold(src, dst, 100, 0, 2); // 100den büyük değerleri 100'e yuvarlar. Truncate
	threshold(src, dst, 70, 0, 3); // 100den küçük değerleri 0'a yuvarlar. Threshold to zero
	threshold(src, dst, 100, 0, 4); // 100den büyük değerleri 0'a yuvarlar. Threshold to zero inverted
	threshold(src, dst, 0, 255 , THRESH_OTSU); // 3. parametre önemsiz çünkü otsu algoritması onu hesaplıyor.

	//std::cout << "M = " << std::endl << " " << dst << std::endl << std::endl;
	namedWindow("mc", CV_WINDOW_AUTOSIZE);
	imshow("mc", dst);

	waitKey(0);

	return 0;
}

