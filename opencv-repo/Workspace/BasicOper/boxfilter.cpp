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

	Mat M(4, 4, CV_8UC3, Scalar(0, 0, 255));
	cvtColor(M, M, CV_BGR2GRAY);
	M.at<cv::Vec3b>(1, 1)[0] = 12;
	M.at<cv::Vec3b>(1, 1)[1] = 13;
	M.at<cv::Vec3b>(1, 1)[2] = 17;

	M.at<cv::Vec3b>(0, 1)[0] = 2;
	M.at<cv::Vec3b>(0, 1)[1] = 23;
	M.at<cv::Vec3b>(0, 1)[2] = 70;

	M.at<cv::Vec3b>(0, 0)[0] = 12;
	M.at<cv::Vec3b>(0, 0)[1] = 32;
	M.at<cv::Vec3b>(0, 0)[2] = 7;

	std::cout << "M = " << std::endl << " " << M << std::endl << std::endl;

	boxFilter(M,M,src.type(),Size(2,2),Point(-1,-1),true,BORDER_REPLICATE);

	std::cout << "M = " << std::endl << " " << M << std::endl << std::endl;


	waitKey(0);

	return 0;
}

