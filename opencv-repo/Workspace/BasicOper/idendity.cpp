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

	/// Load an image
	src = imread("2.jpg");

	//dst = src;

	dst.create(src.size(), src.type());

	for (int j = 0; j < 640; ++j) {
		for (int i = 0; i < 480; ++i) {
			dst.at<cv::Vec3b>(i, j)[0] = src.at<cv::Vec3b>(i, j)[0];
			dst.at<cv::Vec3b>(i, j)[1] = src.at<cv::Vec3b>(i, j)[1];
			dst.at<cv::Vec3b>(i, j)[2] = src.at<cv::Vec3b>(i, j)[2];
		}
	}

	//std::cout << "M = " << std::endl << " " << dst << std::endl << std::endl;
	namedWindow("mc", CV_WINDOW_AUTOSIZE);
	imshow("mc", dst);

	waitKey(0);

	return 0;
}

