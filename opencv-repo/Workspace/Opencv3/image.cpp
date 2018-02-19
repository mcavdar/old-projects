#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <iostream>

using namespace cv;
using namespace std;


int main()
{

	Mat image;
	image = imread("gsu.jpg",CV_LOAD_IMAGE_COLOR);

	namedWindow("Image",WINDOW_AUTOSIZE);
	imshow("Image",image);
	waitKey(0);
	return 0;

}
