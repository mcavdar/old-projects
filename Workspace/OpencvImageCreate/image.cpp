#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"
#include <stdlib.h>
#include <stdio.h>

using namespace cv;

/// Global variables

int threshold_value = 0;
int threshold_type = 3;
;
int const max_value = 255;
int const max_type = 4;
int const max_BINARY_value = 255;

Mat src, src_gray, dst;
char* window_name = "Threshold Demo";

char* trackbar_type =
		"Type: \n 0: Binary \n 1: Binary Inverted \n 2: Truncate \n 3: To Zero \n 4: To Zero Inverted";
char* trackbar_value = "Value";

/// Function headers
void Threshold_Demo(int, void*);

/**
 * @function main
 */
int main(int argc, char** argv) {
	Mat M(100, 100, CV_8UC3, Scalar(0, 0, 0));
	/// Load an image



	/// Create a window to display results
	namedWindow(window_name, CV_WINDOW_AUTOSIZE);

	imshow(window_name,M);

	/// Wait until user finishes program
	while (true) {
		int c;
		c = waitKey(20);
		if ((char) c == 27) {
			break;
		}
	}

}

/**
 * @function Threshold_Demo
 */
void Threshold_Demo(int, void*) {
	/* 0: Binary
	 1: Binary Inverted
	 2: Threshold Truncated
	 3: Threshold to Zero
	 4: Threshold to Zero Inverted
	 */

	threshold(src_gray, dst, threshold_value, max_BINARY_value, threshold_type);

	imshow(window_name, dst);
}
