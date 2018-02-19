/*
 * main.cpp
 *
 *  Created on: Dec 31, 2014
 *      Author: mc
 */
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <cv.hpp>
#include <highgui.h>

using namespace cv;
using namespace std;

int main() {
	std::vector<cv::Vec3f> circles;
	cv::Mat img;
	img = imread("1.png");
	cvtColor(img,img,COLOR_BGR2GRAY);
	HoughCircles(img, circles, HOUGH_GRADIENT, 1, 1, 100, 10, 0, 0);

	int n = circles.size();
	printf("%d",n);
	return 0;
}
