#include <opencv/cv.h>
#include <opencv/highgui.h>
#include <stdlib.h>
#include <time.h>

using namespace cv;


int main(int argc, char** argv) {


	/// Create Trackbar
	Mat frame;

	CvCapture* capture;
	capture = cvCreateCameraCapture(0);
	cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_WIDTH, 320);
	cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_HEIGHT, 240);

	while (cvGrabFrame(capture)) {
		frame = cvRetrieveFrame(capture);



		if (waitKey(10) == 27)
			exit(0);
	}

	waitKey(0);
	return 0;
}
