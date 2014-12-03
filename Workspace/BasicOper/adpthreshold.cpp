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

	src = imread("sudoku2.jpg",0);
	adaptiveThreshold(src, dst, 250, CV_ADAPTIVE_THRESH_MEAN_C,CV_THRESH_BINARY, 11,5); // 100den büyük değerleri 255 yapar kalanını 0 yapar. Binary
	threshold(src, dst2, 0, 255, THRESH_OTSU); // 3. parametrenin kaç olması gerektiğini kendisi bulur. O değerden büyüklere 150 koyduk.

	namedWindow("adaptive", CV_WINDOW_AUTOSIZE);
	imshow("adaptive", dst);
	namedWindow("otsu", CV_WINDOW_AUTOSIZE);
	imshow("otsu", dst2);


	waitKey(0);

	return 0;
}

