#include <iostream>
#include <opencv/cv.h>
#include <opencv/highgui.h>
using namespace std;
char key;
int main() {
	int frameno = 0;
	cvNamedWindow("Camera_Output", 1); //Create window
	CvCapture* capture = cvCaptureFromCAM(-1); //Capture using any camera connected to your system
	while (1) { //Create infinte loop for live streaming
		IplImage* frame = cvQueryFrame(capture); //Create image frames from capture
		cvShowImage("Camera_Output", frame); //Show image frames on created window
		key = cvWaitKey(1); //Capture Keyboard stroke
		if (char(key) == 27) {
			break; //If you hit ESC key loop will break.
		}

		clock_t start = CLOCK();


		double dur = CLOCK() - start;
		printf("avg time per frame %f ms. fps %f. frameno = %d\n", avgdur(dur),
				avgfps(), frameno++);

	}

	cvReleaseCapture(&capture); //Release capture.
	cvDestroyWindow("Camera_Output"); //Destroy Window
	return 0;
}
