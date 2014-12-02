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
	Mat src,dst;

	src = imread("2.jpg");

	cvtColor(src,src,CV_BGR2GRAY);
	threshold(src,dst,100,255,0);

	//std::cout << "M = " << std::endl << " " << dst << std::endl << std::endl;
	namedWindow("mc", CV_WINDOW_AUTOSIZE);
	imshow("mc", dst);

	waitKey(0);

	return 0;
}







